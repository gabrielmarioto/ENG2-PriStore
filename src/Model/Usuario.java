/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ProdutoBD;
import Persistencia.UsuarioBD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Usuario {
    private int codigo;
    private String login;
    private String senha;
    private int nivel;
    
     public Usuario() {
    }

    public Usuario(int codigo, String login, String senha,int nivel) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
        
    }

     
     
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
     
     public boolean insert()
    {
        UsuarioBD usu = new UsuarioBD();        
        return usu.insertUsuario(this);
    }
    public boolean update()
    {
        UsuarioBD usu = new UsuarioBD();        
        return usu.updateUsuario(this);
    }
    public boolean delete()
    {
        UsuarioBD usu = new UsuarioBD();        
        return usu.deleteUsuario(this);
    }
    public Usuario selectUsuario(int cod)
    {
        Usuario u;
        UsuarioBD usu = new UsuarioBD();      
        u = usu.get(cod);
        return u;
    }
    public List<Usuario> selectUsuario(String filtro)
    {
        List<Usuario> aux = new ArrayList();
        UsuarioBD usu = new UsuarioBD();      
        aux = usu.get(filtro);
        
        return aux;
    }
    public boolean isValido()
    {
        List<Usuario> aux = new UsuarioBD().get("usu_login='"+this.login+"' and usu_senha='"+this.senha+"'");
        
        if(!aux.isEmpty())
        {
            this.codigo=aux.get(0).getCodigo();
            this.nivel=aux.get(0).getNivel();
            return true; 
        }   
        return false;
    }
}
