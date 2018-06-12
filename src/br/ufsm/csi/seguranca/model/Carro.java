package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "CARRO")
public class Carro {

    private int idCarro;
    private String marca;
    private String placa;
    private int ano;
    private boolean alugado;
    private Collection<Aluguel> alugueis;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carro")
    @SequenceGenerator(name = "seq_carro", sequenceName = "seq_carro")
    @Column(name = "ID")
    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    @OneToMany(mappedBy = "carro")
    public Collection<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(Collection<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    @Column(name = "MARCA")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "PLACA")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Column(name = "ANO")
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Column(name = "ALUGADO")
    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }
}
