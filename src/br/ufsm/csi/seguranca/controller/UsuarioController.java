package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Aluguel;
import br.ufsm.csi.seguranca.model.Carro;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import br.ufsm.csi.seguranca.util.CaptchaUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by cpol on 29/05/2017.
 */
@Controller
public class UsuarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("cria-usuario.html")
    public String criaUsuario(Usuario usuario, String senha, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
        if(usuario.getId() == null) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            usuario.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
            hibernateDAO.criaObjeto(usuario);
            Date date = new Date();
            hibernateDAO.criaLog(usuario, usuario.getId(), "create", Usuario.class, date);
            return "../../index";
        } else {
            Usuario usuarioBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, usuario.getId());
            usuarioBanco.setId(usuario.getId());
            usuarioBanco.setNome(usuario.getNome());
            usuarioBanco.setTipo(usuario.isTipo());
            usuarioBanco.setLogin(usuario.getLogin());
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            usuarioBanco.setSenha(md.digest(senha.getBytes("ISO-8859-1")));

            Usuario user = (Usuario) session.getAttribute("usuario");
            Date date = new Date();
            hibernateDAO.criaLog(user, usuarioBanco.getId(), "update", Usuario.class, date);
            return "forward:lista-usuarios.adm";
        }

    }

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model, HttpServletRequest request) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);

        //--------------------------------------------------------------- Força bruta;
        String captcha = request.getParameter("g-recaptcha-response");
        Boolean retorno = CaptchaUtil.verify(captcha);
        //--------------------------------------------------------------- Força bruta;
        if (usuarios == null || usuarios.isEmpty() || retorno == false) {
            model.addAttribute("erro", "acesso-negado");
            return "../../index";
        } else {
            //------------------------------------------------------------Session fixation attack
            session.invalidate();
            HttpSession newSession = request.getSession();
            newSession.setAttribute("usuario", usuarios.toArray()[0]);
            //------------------------------------------------------------Session fixation attack
            Usuario user = (Usuario) newSession.getAttribute("usuario");
            Usuario usuarioBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, user.getId());
            Date date = new Date();
            hibernateDAO.criaLog(user, usuarioBanco.getId(), "login", Usuario.class, date);

            return "hello";
        }
    }

    @Transactional
    @RequestMapping("delete-usuario.adm")
    public String deletarUsuario(Long id, HttpSession session) throws ClassNotFoundException {
        Usuario usuarioBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, id);
        if(usuarioBanco.getAlugueis() != null && usuarioBanco.getLogs() != null){
            session.setAttribute("erro", "Usuario nao pode ser deletado");
            return "hello";
        } else {
            Usuario user = (Usuario) session.getAttribute("usuario");
            Date date = new Date();
            hibernateDAO.criaLog(user, id, "delete", Usuario.class, date);
            hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Usuario.class, id));
            return "forward:lista-usuarios.adm";
        }
    }

    //-------------------------------------ROTAS------------------------------------------------------------------------

    @Transactional
    @RequestMapping("create-usuario.html")
    public String createUsuario(Model model, String nome, String login) {
        return "cadastrar-usuario";
    }

    @Transactional
    @RequestMapping("lista-usuarios.adm")
    public String listaUsuarios(Model model, String nome, String login, HttpSession session) throws ClassNotFoundException {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
        Usuario user = (Usuario) session.getAttribute("usuario");
        Date date = new Date();
        hibernateDAO.criaLog(user, user.getId(), "read", Usuario.class, date);
        return "lista-usuarios";
    }

    @Transactional
    @RequestMapping("edit-usuario.adm")
    public String editarUsuario(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, id);
        model.addAttribute("usuario", usuario);
        return "editar-usuario";
    }

    @Transactional
    @RequestMapping("logout.priv")
    public String logout(HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        session.invalidate();
        return "../../index";
    }
}
