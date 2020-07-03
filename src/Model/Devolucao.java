/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.DevolucaoBD;
import Util.Banco;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Devolucao {
    private int codigo;
    private int codigoProdutoant;
    private int codigoProdutonovo;
    private int codigoVenda;
    private Date datadevolucao;

    public Devolucao() {
    }
    
    
    
    public Devolucao(int codigo, int codigoProdutoant,int codigoProdutonovo ,int codigoVenda, Date datadevolucao) {
        this.codigo = codigo;
        this.codigoProdutoant = codigoProdutoant;
        this.codigoVenda = codigoVenda;
        this.datadevolucao = datadevolucao;
        this.codigoProdutonovo = codigoProdutonovo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(int codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public int getCodigoProdutonovo() {
        return codigoProdutonovo;
    }

    public void setCodigoProdutonovo(int codigoProdutonovo) {
        this.codigoProdutonovo = codigoProdutonovo;
    }

    public int getCodigoProdutoant() {
        return codigoProdutoant;
    }

    public void setCodigoProdutoant(int codigoProdutoant) {
        this.codigoProdutoant = codigoProdutoant;
    }
    
    public boolean insert(String tamanho) throws SQLException
    {
        codigo= Banco.getCon().getMaxPK("Devolucao", "cod")+1;
        
        if(codigo==0)
        {
         codigo=1;   
        }
        DevolucaoBD d = new DevolucaoBD();
        
        return d.insertDevolucao(this, tamanho);
    }
    
    public boolean insertList(List<TableDevolucao> lista) throws SQLException
    {
        DevolucaoBD bd = new DevolucaoBD();
        Devolucao d;
        codigo= Banco.getCon().getMaxPK("Devolucao", "cod")+1;
        if(codigo==0)
        {
         codigo=1;   
        }
        boolean flag=true;
        try {
            Banco.getCon().getConnect().setAutoCommit(false);
            int i=0; 
            while(i<lista.size() && flag)
            {
                
                int j=0;
                while(j<lista.get(i).getQtde())
                {
                    d = new Devolucao(codigo, lista.get(i).getCodigo(), 0, lista.get(i).getCodVenda(), Date.valueOf(LocalDate.now()));
                    flag=bd.insertDevolucoes(d,lista.get(i).getTamanho());
                    codigo++;
                    j++;
                }
                i++;
            }
            if(flag)
            {
                double valor=0;
                double valorRe=0;
                List<ParcelasAPagar> parcelasPagas =new ParcelasAPagar().selectParcelas("status='P' and codvenda = "+ lista.get(0).getCodVenda());
                List<ParcelasAPagar> parcelas = new ParcelasAPagar().selectParcelas("status='A' and codvenda = "+ lista.get(0).getCodVenda());
                for(ParcelasAPagar p : parcelasPagas)
                {
                    valor+=p.getValor();
                }
                for(TableDevolucao td : lista)
                {
                    valorRe+=td.getPreco()*td.getQtde();
                }
                valorRe/=parcelas.size();
                for(ParcelasAPagar p: parcelas)
                {
                    p.setValor(valorRe);                                
                }
                flag=parcelas.get(0).updateParcelas(parcelas);
            }
        } catch (SQLException ex) {
            flag=false;
        }
        Banco.getCon().getConnect().setAutoCommit(true);
        return flag;
    }
}
