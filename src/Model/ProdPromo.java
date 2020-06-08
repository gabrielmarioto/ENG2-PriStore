/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ProdPromoBD;
import Persistencia.ProdutoBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class ProdPromo {
    private int codigoProduto;
    private int codigoPromocao;
    private double desconto;
    private boolean ativo;

    public ProdPromo() {
    }

    public ProdPromo(int codigoProduto, int codigoPromocao, double desconto,boolean ativo) {
        this.codigoProduto = codigoProduto;
        this.codigoPromocao = codigoPromocao;
        this.desconto = desconto;
        this.ativo=ativo;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoPromocao() {
        return codigoPromocao;
    }

    public void setCodigoPromocao(int codigoPromocao) {
        this.codigoPromocao = codigoPromocao;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public boolean insert()
    {
        ProdPromoBD prod = new ProdPromoBD();
        return prod.insert(this);
    }

    public boolean update()
    {
        ProdPromoBD prod = new ProdPromoBD();
        return prod.update(this);
    }
    
    public boolean delete()
    {
        ProdPromoBD prod = new ProdPromoBD();
        return prod.delete(this);
    }

    public ProdPromo selectPorProduto(int cod)
    {
        ProdPromo p;
        ProdPromoBD prod = new ProdPromoBD();
        p = prod.getPorProduto(cod);
        return p;
    }

    public List<ProdPromo> selectPorProduto(String filtro)
    {
        List<ProdPromo> aux = new ArrayList();
        ProdPromoBD prod = new ProdPromoBD();
        aux = prod.get(filtro);

        return aux;
    }
    
    public List<ProdutoPm> selectProdutos(String filtro)
    {
        List<ProdutoPm> p;
        p=new ProdPromoBD().getProdutos(filtro);
        return p;
    }
    
    public void updateAtivo(int cod,boolean ativo)
    {
        ProdPromoBD prod = new ProdPromoBD();
        prod.updateAtivo(cod,ativo);
    }
}
