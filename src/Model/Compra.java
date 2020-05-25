/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.CompraBD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Compra
{

    private int codCompra;
    private Fornecedor codForn;
    private double valorTotal, desconto;
    private LocalDate dataCompra;

    public Compra(int codCompra, Fornecedor codForn, double valorTotal, double desconto, LocalDate dataCompra)
    {
        this.codCompra = codCompra;
        this.codForn = codForn;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.dataCompra = dataCompra;
    }

    public Compra()
    {

    }

    public int getCodCompra()
    {
        return codCompra;
    }

    public void setCodCompra(int codCompra)
    {
        this.codCompra = codCompra;
    }

    public Fornecedor getCodForn()
    {
        return codForn;
    }

    public void setCodForn(Fornecedor codForn)
    {
        this.codForn = codForn;
    }

    public double getValorTotal()
    {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal)
    {
        this.valorTotal = valorTotal;
    }

    public double getDesconto()
    {
        return desconto;
    }

    public void setDesconto(double desconto)
    {
        this.desconto = desconto;
    }

    public LocalDate getDataCompra()
    {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra)
    {
        this.dataCompra = dataCompra;
    }

    public boolean insertCompra()
    {
        CompraBD c = new CompraBD();
        return c.insertCompra(this);
    }

    public boolean updateCompra()
    {
        CompraBD c = new CompraBD();
        return c.updateCompra(this);
    }

    public boolean deleteCompra()
    {
        CompraBD c = new CompraBD();
        return c.deleteCompra(this);
    }

    public Compra selectCompra(int cod)
    {
        Compra compra;
        CompraBD c = new CompraBD();
        compra = c.get(cod);
        return compra;
    }
    
    public List<Compra> selectCompra(String filtro)
    {
        List<Compra> aux = new ArrayList();
        CompraBD c = new CompraBD();
        aux = c.get(filtro);
        return aux;
    }
}
