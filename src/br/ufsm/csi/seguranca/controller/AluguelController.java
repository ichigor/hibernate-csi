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
        car.setAlugado(true);

        return "forward:list-alugueis-user.priv";
    }

    @Transactional
    @RequestMapping("editar-aluguel.priv")
    public String editaAluguel(Aluguel aluguel, Long id, HttpSession session, Model model, Long carroId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);

        Long idCarro = aluguelBanco.getCarro().getId();
        Carro carroVelho = (Carro) hibernateDAO.carregaObjeto(Carro.class, idCarro);
        carroVelho.setAlugado(false);

        Carro carroBanco = (Carro) hibernateDAO.carregaObjeto(Carro.class, carroId);
        aluguelBanco.setCarro(carroBanco);

        Usuario user = (Usuario) session.getAttribute("usuario");
        aluguel.setUsuario(user);
        aluguel.setCarro(carroBanco);
        Date data = new Date();
        aluguel.setData(data);

        Collection aluguelUsuario = user.getAlugueis();
        Collection aluguelCarro = carroBanco.getAlugueis();

        aluguelUsuario.add(aluguel);
        aluguelCarro.add(aluguel);

        user.setAlugueis(aluguelUsuario);
        carroBanco.setAlugueis(aluguelCarro);
        carroBanco.setAlugado(true);

        return "forward:list-alugueis-user.priv";
    }

    @Transactional
    @RequestMapping("cadastrar-aluguel.priv")
    public String cadastraAluguel() {
        return "cadastrar-aluguel";
    }

    @Transactional
    @RequestMapping("list-alugueis.adm")
    public String listarAlugueis(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        return "listar-alugueis";
    }

    @Transactional
    @RequestMapping("list-alugueis-user.priv")
    public String listarAlugueisUsuario(Model model, String nome, String login, HttpSession session) {
        Usuario userSessao = (Usuario) session.getAttribute("usuario");
        Long idUsuarioSessao = userSessao.getId();
        Usuario userBanco = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuarioSessao);
//        model.addAttribute("alugueis", hibernateDAO.listaObjetos(Aluguel.class, m, null, null, false));
        model.addAttribute("usuario", userBanco);
        return "listar-alugueis-usuario";
    }

    @Transactional
    @RequestMapping("delete-aluguel.priv")
    public String deletarAluguel(Long id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguelBanco = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        Long carroId = aluguelBanco.getCarro().getId();
        Carro carroBanco = (Carro) hibernateDAO.carregaObjeto(Carro.class, carroId);
        carroBanco.setAlugado(false);
        hibernateDAO.removeObjeto(hibernateDAO.carregaObjeto(Aluguel.class, id));
        return "forward:list-alugueis-user.priv";
    }

    @Transactional
    @RequestMapping("edit-aluguel.priv")
    public String editarAluguel(Long id, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Aluguel aluguel = (Aluguel) hibernateDAO.carregaObjeto(Aluguel.class, id);
        model.addAttribute("aluguel", aluguel);
        Map<String, String> m = new HashMap<>();
        model.addAttribute("carros", hibernateDAO.listaObjetos(Carro.class, m, null, null, false));
        return "editar-aluguel";
    }
}
