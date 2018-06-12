package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ALUGUEL")
public class Aluguel {
    private int idAluguel;
    private Usuario usuario;
    private Carro carro;
    private Date data;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carro")
    @SequenceGenerator(name = "seq_carro", sequenceName = "seq_carro")
    @Column(name = "ID")
    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    @ManyToOne
    @JoinColumn(name = "usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "carro")
    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Column(name = "DATA")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
