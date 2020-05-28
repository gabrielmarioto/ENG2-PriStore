/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistencia.ClienteBD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNO
 */
public class Cliente {
    
    private int cod;
    private String nome;
    private String cpf;
    private String end;
    private String email;
    private String telefone;
    private char sexo;
    private double saldo;
    private LocalDate dtNascimento;

    public Cliente() {
    }

    public Cliente(int cod, String nome, String cpf, String end, String email,
            String telefone, char sexo, double saldo, LocalDate dtNascimento) {
        this.cod = cod;
        this.nome = nome;
        this.cpf = cpf;
        this.end = end;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.saldo = saldo;
        this.dtNascimento = dtNascimento;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
    public List<Cliente> selectCliente(String filtro)
    {
        List<Cliente> c = new ArrayList();
        ClienteBD cli = new ClienteBD();
        c = cli.get(filtro);
        return c;
    }
    public List<Cliente> selectClienteSemConsignadoAberto(String filtro)
    {
        List<Cliente> c = new ArrayList();
        ClienteBD cli = new ClienteBD();
        c = cli.getF(filtro);
        return c;
    }
    public String toString() {
        return nome;
    }    
    public boolean insertCliente()
    {
        ClienteBD cliente = new ClienteBD();
        return cliente.insertCliente(this);
    }

    public boolean updateCliente()
    {
        ClienteBD cliente = new ClienteBD();
        return cliente.updateCliente(this);
    }

    public boolean deleteCliente()
    {
        ClienteBD cliente = new ClienteBD();
        return cliente.deleteCliente(this);
    }

    public Cliente selectCliente(int codigo)
    {
        Cliente c;
        ClienteBD categoria = new ClienteBD();
        c = categoria.get(codigo);

        return c;
    }
}
