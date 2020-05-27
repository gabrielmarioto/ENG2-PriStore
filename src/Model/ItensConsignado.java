/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author BRUNO
 */
public class ItensConsignado {
    
    private Produto codProduto;
    private Cliente codCliente;

    public ItensConsignado() {
    }

    public ItensConsignado(Produto codProduto, Cliente codCliente) {
        this.codProduto = codProduto;
        this.codCliente = codCliente;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
    }
    
    
}
