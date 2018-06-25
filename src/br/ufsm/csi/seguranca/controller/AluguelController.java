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
    public String criaAluguel(Aluguel aluguel, Long id, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        if(aluguel.getId() == null){
//            hibernateDAO.criaObjeto(aluguel);
//        } else {
//            Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, aluguel.getId());
//            aluguelBanco.setId(aluguel.getId());
//            aluguelBanco.setCarro(aluguel.getCarro());
//            aluguelBanco.setUsuario(aluguel.getUsuario());
//        }
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

        return "forward:list-alugueis.priv";
    }

    @Transactional
    @RequestMapping("cadastrar-aluguel.priv")
    public String cadastraAluguel() {
        return "cadastrar-aluguel";
    }

    @Transactional
    @RequestMapping("list-alugueis.priv")
    public String listarAlugueis(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        return "listar-alugueis";
    }

    @Transactional
    @RequestMapping("delete-aluguel.priv")
    public String deletarAluguel(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Aluguel.class, id));
        return "forward:list-alugueis.priv";
    }

    @Transactional
    @RequestMapping("edit-aluguel.priv")
    public String editarAluguel(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguel = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        model.addAttribute("aluguel", aluguel);
        return "editar-aluguel";
    }
}
