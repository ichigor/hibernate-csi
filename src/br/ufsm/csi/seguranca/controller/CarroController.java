package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Carro;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarroController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("create-carro.html")
    public String cadastraCarro(Carro carro) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.criaObjeto(carro);
        return "forward:list-carros.html";
    }


    @Transactional
    @RequestMapping("list-carros.html")
    public String ListaCarros(Model model, String nome, String login) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("carros", hibernateDAO.listaObjetos(Carro.class, m, null, null, false));
        return "listar-carros";
    }

    @Transactional
    @RequestMapping("cadastrar-carro.html")
    public String cadastraCarro() {
        return "cadastrar-carro";
    }

    @Transactional
    @RequestMapping("delete-carro.html")
    public String ListaCarros(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Carro.class, id));
        return "forward:list-carros.html";
    }

    @Transactional
    @RequestMapping("edit-carro.html")
    public String EditarCarro(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        Carro carro = (Carro) hibernateDAO.carregaObjeto(Carro.class, id);
//        hibernateDAO.criaObjeto(carro);
        return "editar-carro";
    }
}
