package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Aluguel;
import br.ufsm.csi.seguranca.model.Carro;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AluguelController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("create-aluguel.priv")
    public String criaAluguel(Aluguel aluguel, Long id, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {

        Usuario user = (Usuario) session.getAttribute("usuario");
        Carro car = (Carro) hibernateDAO.carregaObjeto(Carro.class, id);
        hibernateDAO.criaObjeto(aluguel);
        aluguel.setUsuario(user);
        aluguel.setCarro(car);
        Date data = new Date();
        aluguel.setData(data);

        Collection aluguelUsuario = user.getAlugueis();
        Collection aluguelCarro = car.getAlugueis();

        aluguelUsuario.add(aluguel);
        aluguelCarro.add(aluguel);

        user.setAlugueis(aluguelUsuario);
        car.setAlugueis(aluguelCarro);
        car.setAlugado(true);

        Date date = new Date();
        hibernateDAO.criaLog(user, aluguel.getId(), "create", Aluguel.class, date);

        return "forward:list-alugueis-user.priv";
    }

    @Transactional
    @RequestMapping("editar-aluguel.priv")
    public String editaAluguel(Aluguel aluguel, Long id, HttpSession session, Model model, Long carroId) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
        Long idAluguel = (Long) session.getAttribute("idAluguel");

        if(id.equals(idAluguel)){
            Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
            Usuario usuarioSession = (Usuario) session.getAttribute("usuario");
            Usuario usuarioBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, usuarioSession.getId());
            if(aluguelBanco.getUsuario().getId().equals(usuarioBanco.getId())){

                //feito para mudar o boolean de alugado
                Long idCarroVelho = aluguelBanco.getCarro().getId();
                Carro carroVelho = (Carro) hibernateDAO.carregaObjeto(Carro.class, idCarroVelho);
                carroVelho.setAlugado(false);

                Carro carroNovo = (Carro) hibernateDAO.carregaObjeto(Carro.class, carroId);
                aluguelBanco.setCarro(carroNovo);
                Date data = new Date();
                aluguelBanco.setData(data);

                carroNovo.setAlugado(true);
                hibernateDAO.updateObjeto(aluguelBanco);
                Date date = new Date();
                hibernateDAO.criaLog(usuarioSession, aluguelBanco.getId(), "update", Aluguel.class, date);
            }
        }
        return "forward:list-alugueis-user.priv";
    }

    @Transactional
    @RequestMapping("delete-aluguel.priv")
    public String deletarAluguel(Long id, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
        Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        Long carroId = aluguelBanco.getCarro().getId();
        Carro carroBanco = (Carro) hibernateDAO.carregaObjeto(Carro.class, carroId);
        carroBanco.setAlugado(false);
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Aluguel.class, id));
        Usuario user = (Usuario) session.getAttribute("usuario");
        Date date = new Date();
        hibernateDAO.criaLog(user, aluguelBanco.getId(), "delete", Aluguel.class, date);
        return "forward:list-alugueis-user.priv";
    }

    //-------------------------------------ROTAS------------------------------------------------------------------------


    @Transactional
    @RequestMapping("list-alugueis.adm")
    public String listarAlugueis(Model model, String nome, String login, HttpSession session) throws ClassNotFoundException {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        Usuario user = (Usuario) session.getAttribute("usuario");
        Date date = new Date();
        hibernateDAO.criaLog(user, user.getId(), "read", Aluguel.class, date);
        return "listar-alugueis";
    }

    @Transactional
    @RequestMapping("list-alugueis-user.priv")
    public String listarAlugueisUsuario(Model model, String nome, String login, HttpSession session) throws ClassNotFoundException {
        Usuario userSessao = (Usuario) session.getAttribute("usuario");
        Long idUsuarioSessao = userSessao.getId();
        Usuario userBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuarioSessao);
//        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        model.addAttribute("usuario", userBanco);
        Usuario user = (Usuario) session.getAttribute("usuario");
        Date date = new Date();
        hibernateDAO.criaLog(user, user.getId(), "read", Aluguel.class, date);
        return "listar-alugueis-usuario";
    }

    @Transactional
    @RequestMapping("edit-aluguel.priv")
    public String editarAluguel(Long id, Model model, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguel = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        model.addAttribute("aluguel", aluguel);
        Map<String, String> m = new HashMap<>();
        model.addAttribute("carros", hibernateDAO.listaObjetos(Carro.class, m, null, null, false));
        session.setAttribute("idAluguel", id);
        return "editar-aluguel";
    }
}
