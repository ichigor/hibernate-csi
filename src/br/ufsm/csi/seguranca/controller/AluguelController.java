package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Aluguel;
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
public class AluguelController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("create-aluguel.html")
    public String criaAluguel(Aluguel aluguel) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(aluguel.getId() == null){
            hibernateDAO.criaObjeto(aluguel);
        } else {
            Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, aluguel.getId());
            aluguelBanco.setId(aluguel.getId());
            aluguelBanco.setCarro(aluguel.getCarro());
            aluguelBanco.setUsuario(aluguel.getUsuario());
        }
        return "forward:list-alugueis.html";
    }

    @Transactional
    @RequestMapping("cadastrar-aluguel.html")
    public String cadastraAluguel() {
        return "cadastrar-aluguel";
    }

    @Transactional
    @RequestMapping("list-alugueis.html")
    public String listarAlugueis(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        return "listar-alugueis";
    }

    @Transactional
    @RequestMapping("delete-aluguel.html")
    public String deletarAluguel(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Aluguel.class, id));
        return "forward:list-alugueis.html";
    }

    @Transactional
    @RequestMapping("edit-aluguel.html")
    public String editarAluguel(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguel = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        model.addAttribute("aluguel", aluguel);
        return "editar-aluguel";
    }
}
