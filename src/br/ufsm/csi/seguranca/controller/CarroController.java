package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Carro;

import br.ufsm.csi.seguranca.model.Usuario;
import br.ufsm.csi.seguranca.util.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarroController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("create-carro.adm")
    public String cadastraCarro(Carro carro, String token, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
        String tokenSessao = (String) session.getAttribute("token");
        if(carro.getId() == null) {
            if(token != null && token.equals(tokenSessao)){
                hibernateDAO.criaObjeto(carro);

                Usuario user = (Usuario) session.getAttribute("usuario");
                Date date = new Date();
                hibernateDAO.criaLog(user, carro.getId(), "create", Carro.class, date);
            }

        } else {
            Carro carroBanco = (Carro) hibernateDAO.carregaObjeto(Carro.class, carro.getId());
            carroBanco.setId(carro.getId());
            carroBanco.setAno(carro.getAno());
            carroBanco.setMarca(carro.getMarca());
            Usuario user = (Usuario) session.getAttribute("usuario");
            Date date = new Date();
            hibernateDAO.criaLog(user, carro.getId(), "update", Carro.class, date);
        }
        return "forward:list-carros.html";
    }

    @Transactional
    @RequestMapping("delete-carro.adm")
    public String deletarCarro(Long id, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Carro.class, id));
            Usuario user = (Usuario) session.getAttribute("usuario");
            Date date = new Date();
            hibernateDAO.criaLog(user, id, "delete", Carro.class, date);
        } catch (Exception e) {
            //adicionar algo que nao pode remover caso tenha algum relacionamento
        }
        return "forward:list-carros.html";
    }




    //-------------------------------------ROTAS------------------------------------------------------------------------

    @Transactional
    @RequestMapping("cadastrar-carro.adm")
    public String cadastraCarro(HttpSession session) {
        session.setAttribute("token", util.generateToken());
        return "cadastrar-carro";
    }

    @Transactional
    @RequestMapping("list-carros.html")
    public String listaCarros(Model model, String nome, String login, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("carros", hibernateDAO.listaObjetos(Carro.class, m, null, null, false));
        Usuario user = (Usuario) session.getAttribute("usuario");
        Date date = new Date();
        hibernateDAO.criaLog(user, user.getId(), "read", Carro.class, date);
        return "listar-carros";
    }


    @Transactional
    @RequestMapping("edit-carro.adm")
    public String editarCarro(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Carro carro = (Carro) hibernateDAO.carregaObjeto(Carro.class, id);
        model.addAttribute("carro", carro);
        return "editar-carro";
    }
}
