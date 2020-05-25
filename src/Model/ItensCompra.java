/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ItensCompraBD;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gabriel
 */
public class ItensCompra
{

    private Produto codProduto;
    private Compra codCompra;
    private Tamanho tamanho;
    private double valorProduto;
    private int qntd;

    public ItensCompra(Produto codProduto, Compra codCompra, Tamanho tamanho, double valorProduto, int qntd)
    {
        this.codProduto = codProduto;
        this.codCompra = codCompra;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
        this.qntd = qntd;
    }

     public ItensCompra(Produto codProduto, Tamanho tamanho, double valorProduto, int qntd)
    {
        this.codProduto = codProduto;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
        this.qntd = qntd;
    }
    public ItensCompra()
    {

    }

    public Tamanho getTamanho()
    {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho)
    {
        this.tamanho = tamanho;
    }

    public Produto getCodProduto()
    {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto)
    {
        this.codProduto = codProduto;
    }

    public Compra getCodCompra()
    {
        return codCompra;
    }

    public void setCodCompra(Compra codCompra)
    {
        this.codCompra = codCompra;
    }

    public double getValorProduto()
    {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto)
    {
        this.valorProduto = valorProduto;
    }

    public int getQntd()
    {
        return qntd;
    }

    public void setQntd(int qntd)
    {
        this.qntd = qntd;
    }

    public boolean insertItensCompra()
    {
        ItensCompraBD it = new ItensCompraBD();
        return it.insertItensCompra(this);
    }

    public boolean updateItensCompra()
    {
        ItensCompraBD it = new ItensCompraBD();
        return it.updateItensCompra(this);
    }

    public boolean deleteItensCompra()
    {
        ItensCompraBD it = new ItensCompraBD();
        return it.deleteItensCompra(this);
    }

    public ItensCompra selectItensCompra(int codProduto, int codCompra)
    {
        ItensCompra item;
        ItensCompraBD it = new ItensCompraBD();
        item = it.get(codCompra);

        return item;
    }

    public List<ItensCompra> selectItensCompra(String filtro)
    {
        List<ItensCompra> aux = new ArrayList();
        ItensCompraBD it = new ItensCompraBD();
        aux = it.get(filtro);

        return aux;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ItensCompra other = (ItensCompra) obj;
        if (!Objects.equals(this.codProduto.getCod(), other.codProduto.getCod()))
        {
            return false;
        }
        if (!Objects.equals(this.tamanho.getTamanho(), other.tamanho.getTamanho()))
        {
            return false;
        }
        return true;
    }

   
    
    
}
