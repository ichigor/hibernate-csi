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
    @RequestMapping("create-carro.adm")
    public String cadastraCarro(Carro carro) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(carro.getId() == null) {
            hibernateDAO.criaObjeto(carro);
        } else {
            Carro carroBanco = (Carro) hibernateDAO.carregaObjeto(Carro.class, carro.getId());
            carroBanco.setId(carro.getId());
            carroBanco.setAno(carro.getAno());
            carroBanco.setMarca(carro.getMarca());
        }

        return "forward:list-carros.html";
    }


    @Transactional
    @RequestMapping("list-carros.html")
    public String listaCarros(Model model, String nome, String login) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("carros", hibernateDAO.listaObjetos(Carro.class, m, null, null, false));
        return "listar-carros";
    }

    @Transactional
    @RequestMapping("cadastrar-carro.adm")
    public String cadastraCarro() {
        return "cadastrar-carro";
    }

    @Transactional
    @RequestMapping("delete-carro.adm")
    public String deletarCarro(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Carro.class, id));
        } catch (Exception e) {
            //adicionar algo que nao pode remover caso tenha algum relacionamento
        }
        return "forward:list-carros.html";
    }

    @Transactional
    @RequestMapping("edit-carro.adm")
    public String editarCarro(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Carro carro = (Carro) hibernateDAO.carregaObjeto(Carro.class, id);
        model.addAttribute("carro", carro);
        return "editar-carro";
    }
}
