package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Aluguel;
import br.ufsm.csi.seguranca.model.Carro;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
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
    public String criaUsuario(Usuario usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(usuario.getId() == null) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            usuario.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
            hibernateDAO.criaObjeto(usuario);
        } else {
            Usuario usuarioBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, usuario.getId());
            usuarioBanco.setId(usuario.getId());
            usuarioBanco.setNome(usuario.getNome());
            usuarioBanco.setTipo(usuario.isTipo());
            usuarioBanco.setLogin(usuario.getLogin());
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            usuarioBanco.setSenha(md.digest(senha.getBytes("ISO-8859-1")));
        }
        return "forward:hello.html";
    }

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);
        if (usuarios == null || usuarios.isEmpty()) {
            model.addAttribute("logServidor", "acesso-negado");
            return "../../index";
        } else {
            session.setAttribute("usuario", usuarios.toArray()[0]);
            return "hello";
        }
//        Usuario usuario = hibernateDAO.findUsuarioHQL(login, senha);
//        if (usuario == null) {
//            return "acesso-negado";
//        } else {
//            return "ok";
//        }
    }

    @Transactional
    @RequestMapping("cria-log.priv")
    public String criaLog(Long idUsuario,
                          Long idObjeto,
                          String classe,
                          @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuario);
        Log log = new Log();
        log.setClasse(Class.forName(classe));
        log.setIdObjeto(idObjeto);
        log.setDataHora(dataHora);
        log.setUsuario(usuario);
        hibernateDAO.criaObjeto(log);
        return "log";
    }

    @Transactional
    @RequestMapping("lista-usuarios.adm")
    public String listaUsuarios(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        //verificar talvez precise remover
//        if (nome != null && !nome.isEmpty()) {
//            m.put("nome", nome);
//        }
//        if (login != null && !login.isEmpty()) {
//            m.put("login", login);
//        }
        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
        return "lista-usuarios";
    }

    @Transactional
    @RequestMapping("create-usuario.html")
    public String createUsuario(Model model, String nome, String login) {
        return "cadastrar-usuario";
    }

    @Transactional
    @RequestMapping("delete-usuario.adm")
    public String deletarUsuario(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Usuario.class, id));
        return "forward:lista-usuarios.adm";
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
