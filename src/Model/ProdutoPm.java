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
public class ProdutoPm extends Produto{
    
    private double preco2;

    public ProdutoPm() {
    }

    public ProdutoPm(int cod, Categoria codCategoria, String nome, float preco, String descricao, Marca codMarca, Colecao codColecao) {
        super(cod, codCategoria, nome, preco, descricao, codMarca, codColecao);
    }
    
    
    
    public ProdutoPm(double preco2, int cod, Categoria codCategoria, String nome, float preco, String descricao, Marca codMarca, Colecao codColecao) {
        super(cod, codCategoria, nome, preco, descricao, codMarca, codColecao);
        this.preco2 = preco2;
    }

    public double getPreco2() {
        return preco2;
    }

    public void setPreco2(double preco2) {
        this.preco2 = preco2;
    }
}
