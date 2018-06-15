package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Aluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
public class AluguelController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("create-aluguel.html")
    public String cadastraCarro(Aluguel aluguel) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.criaObjeto(aluguel);
        return "cadastrar-aluguel";
    }

    @Transactional
    @RequestMapping("cadastrar-aluguel.html")
    public String cadastraCarro() {
        return "cadastrar-aluguel";
    }
}
