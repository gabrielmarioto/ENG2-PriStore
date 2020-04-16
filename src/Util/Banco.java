/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Banco
{
     static private Conexao con = null;

    static public Conexao getCon() {
        return con;
    }

    private Banco() {
    } // construtor privado, ou seja, nao pode dar um new em banco, da uma segurança maior no projeto

    static public boolean conectar() {
        con = new Conexao();
        return con.conectar("jdbc:postgresql://localhost/", "botecodb", "postgres", "postgres123");

    }

    public static void realizaBackup(String arq) 
    {
        String reslinha = "";
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arq);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                String linha;
                while ((linha = reader.readLine()) != null)                 
                    reslinha += linha+"\n";                
            }
            JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!\n");
        } catch (IOException ex) 
        {
            JOptionPane.showMessageDialog(null, "Erro no backup!\n" + ex.getMessage());
        }
    }
    public static void realizaRestaure(String arqlote)
    {
         String linha = "";
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(arqlote);
            if (p != null) 
            {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);                
                while ((linha = reader.readLine()) != null)                 
                    linha += linha;                
            }
            JOptionPane.showMessageDialog(null, "Restore realizado com sucesso!\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro no Restore!\n" + ex.getMessage());
        }
    }
}
