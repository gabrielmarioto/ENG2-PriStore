/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.PromocaoBD;
import Util.Banco;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur
 */
public class Promocao {
    private int codigo;
    private String nome;
    private Date inicio;
    private Date fim;
    private String tipo;
    private double valor;

    public Promocao() {
    }

    public Promocao(int codigo, String nome, Date inicio, Date fim, String tipo, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public boolean insertPromocao()
    {
        codigo = Banco.getCon().getMaxPK("promocao", "cod")+1;
        if(codigo==0)
            codigo=1;
        PromocaoBD promo = new PromocaoBD();
        return promo.insertPromocao(this);
    }

    public boolean updatePromocao()
    {
        PromocaoBD promo = new PromocaoBD();
        return promo.updatePromocao(this);
    }

    public boolean deletePromocao()
    {
        PromocaoBD promo = new PromocaoBD();
        try {
            return promo.deletePromocao(this);
        } catch (SQLException ex) {
           return false;
        }
    }

    public Promocao selectPromocao(int codigo)
    {
        Promocao p;
        PromocaoBD marca = new PromocaoBD();
        p = marca.get(codigo);

        return p;
    }

    public List<Promocao> selectPromocao(String filtro)
    {
        List<Promocao> aux = new ArrayList();
        PromocaoBD promo = new PromocaoBD();
        aux = promo.get(filtro);

        return aux;
    }
}
