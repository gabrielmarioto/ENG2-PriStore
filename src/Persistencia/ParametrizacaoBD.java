/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Model.Parametros;
import Util.Banco;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BRUNO
 */
public class ParametrizacaoBD {
    
    
    public boolean updateParametros(Parametros p)
    {
        String sql = "update parametrizacao set nome_fantasia = '#1', razao_social = '#2', endereco = '#3' "
                + ", site = '#4', email = '#5', telefone = '#6'";
        sql = sql.replaceAll("#1", "" + p.getNomeF());
        sql = sql.replaceAll("#2", "" + p.getRazaoSocial());
        sql = sql.replaceAll("#3", "" + p.getEndereco());
        sql = sql.replaceAll("#4", "" + p.getSite());
        sql = sql.replaceAll("#5", "" + p.getEmail());
        sql = sql.replaceAll("#6", "" + p.getTelefone());

         System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }
    

    public Parametros get()
    {
        Parametros p = null;
        ResultSet rs = Banco.getCon().consultar("select * from parametrizacao ");
        try
        {
            if (rs.next())
            {
                p = new Parametros(rs.getString("nome_fantasia"), rs.getString("razao_social")
                ,rs.getString("endereco"), rs.getString("site"), rs.getString("email")
                ,rs.getString("telefone"));
            }
        } catch (SQLException ex){}
        return p;
    }
    public InputStream getFoto()    
    {
        InputStream pic = null;
        try
        {
            PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("select foto from parametrizacao");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                byte[] imgBytes = rs.getBytes("foto");
                pic = new ByteArrayInputStream(imgBytes);
            }
            
            ps.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
        return pic;
    }
    public boolean insertParametros(Parametros p)
    {
        String sql = "insert into parametrizacao (nome_fantasia, razao_social, endereco, site, email, telefone)"
                + "values ('#1', '#2', '#3', '#4', '#5', '#6')";
        
        sql = sql.replaceAll("#1", "" + p.getNomeF());
        sql = sql.replaceAll("#2", "" + p.getRazaoSocial());
        sql = sql.replaceAll("#3", "" + p.getEndereco());
        sql = sql.replaceAll("#4", "" + p.getSite());
        sql = sql.replaceAll("#5", "" + p.getEmail());
        sql = sql.replaceAll("#6", "" + p.getTelefone());
        System.out.println(sql);
        return Banco.getCon().manipular(sql);
    }
    public boolean gravarFoto(InputStream pic, int length)    
    {
        try
        {
            PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("UPDATE parametrizacao set foto = ? ");
            ps.setBinaryStream(1, pic, length);
            ps.executeUpdate();
            ps.close();
            pic.close();
        }
        catch(Exception e)
        {
            return false;
        }
        
        return true;
    }
}
