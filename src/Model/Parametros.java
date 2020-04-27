/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ParametrizacaoBD;

/**
 *
 * @author BRUNO
 */
public class Parametros {
    
    private String nomeF;
    private String RazaoSocial;
    private String Endereco;
    private String Site;
    private String Email;
    private String Telefone;

    public Parametros() {
    }

    public Parametros(String nomeF, String RazaoSocial, String Endereco, String Site, String Email, String Telefone) {
        this.nomeF = nomeF;
        this.RazaoSocial = RazaoSocial;
        this.Endereco = Endereco;
        this.Site = Site;
        this.Email = Email;
        this.Telefone = Telefone;
    }

    public String getNomeF() {
        return nomeF;
    }

    public void setNomeF(String nomeF) {
        this.nomeF = nomeF;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String Site) {
        this.Site = Site;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }
    
    public boolean updateParametros()
    {
        ParametrizacaoBD par = new ParametrizacaoBD();
        return par.updateParametros(this);
    }

    public Parametros selectParametro()
    {
        Parametros p;
        ParametrizacaoBD par = new ParametrizacaoBD();
        p = par.get();

        return p;
    }
    
}
