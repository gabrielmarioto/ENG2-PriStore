/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Model.Cliente;
import Model.Devolucao;
import Model.Fornecedor;
import Model.ItensVenda;
import Model.ParcelasAPagar;
import Model.ProdPromo;
import Model.Produto;
import Model.TableDevolucao;
import Model.Tamanho;
import Model.Venda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLEfetuarDevolucaoTrocaController implements Initializable {
    
    @FXML
    private JFXButton btn_confirma;
    @FXML
    private JFXButton btn_sair;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXButton btn_Pesquisar;
    
    @FXML
    private TableView<TableDevolucao> tabela;
    @FXML
    private TableColumn<TableDevolucao, String> colProdD;
    @FXML
    private TableColumn<TableDevolucao, Double> colPrecoD;
    @FXML
    private TableColumn<TableDevolucao, String> coltamD;
    @FXML
    private TableView<TableDevolucao> tabelaselec;
    @FXML
    private TableColumn<TableDevolucao, String> colProdS;
    @FXML
    private TableColumn<TableDevolucao, Double> colPrecoS;
    @FXML
    private TableColumn<TableDevolucao, Integer> coltamS;
    @FXML
    private TableColumn<TableDevolucao, Double> colqtdeS;
   
   
    @FXML
    private Label txt_total;
    @FXML
    private Label txt_Disponivel;
    
    private double TotalDev=0;
    private double TotalTroca=0;
    private List<TableDevolucao> listaDev;
    private List<TableDevolucao> listaTroca = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        colProdD.setCellValueFactory(new PropertyValueFactory("nome"));
        colPrecoD.setCellValueFactory(new PropertyValueFactory("preco"));
        coltamD.setCellValueFactory(new PropertyValueFactory("tamanho"));
        
        colProdS.setCellValueFactory(new PropertyValueFactory("nome"));
        colPrecoS.setCellValueFactory(new PropertyValueFactory("preco"));
        colqtdeS.setCellValueFactory(new PropertyValueFactory("qtde"));
        coltamS.setCellValueFactory(new PropertyValueFactory("tamanho"));
        
        
        List<String> lista = new ArrayList<>();
        lista.add("Nome");
        lista.add("tamanho");
        cbb_filtro.setItems(FXCollections.observableArrayList(lista));
        
        

    }    

    
    @SuppressWarnings("empty-statement")
     private void carregaTabela(String filtro)
    {
        List<TableDevolucao> lista = new ArrayList<>();
        List<Tamanho> listtam;
        if(cbb_filtro.getSelectionModel().getSelectedIndex()==1)
        {
             listtam = new Tamanho().selectTamanho(filtro);
        }
        else
            listtam = new Tamanho().selectTamanho("");
        for(Tamanho  t: listtam)
        {
            ProdPromo p = new ProdPromo().selectPorProduto(t.getCodProduto().getCod());
            double valor= t.getCodProduto().getPreco();
            if(p!=null)
                valor-=p.getDesconto();
            valor=Double.valueOf(double2string(valor).replace(",", "."));
            TableDevolucao td = new TableDevolucao(t.getCodProduto().getCod(), t.getCodProduto().getNome(), valor, 1, t.getTamanho(),
                    listaDev.get(0).getCodVenda());
            if(cbb_filtro.getSelectionModel().getSelectedIndex()==0)
            {
                if(td.getNome().contains(filtro))
                    lista.add(td);
            }
            else
                lista.add(td); 
        }
        
        tabela.setItems(FXCollections.observableArrayList(lista));
        //List<TableDevolucao> lista = new ItensVenda().selectItens("");
        
    }
    
    @FXML
    private void clkDevolucao(ActionEvent event) throws SQLException {
        
    }

    @FXML
    private void clkSair(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }


    @FXML
    private void clkBtPesquisar(ActionEvent event) {
        if(cbb_filtro.getSelectionModel().getSelectedIndex()>-1 && tb_Pesquisa.getText().length()>0)
        {
            String filtro="";
            if(cbb_filtro.getSelectionModel().getSelectedIndex()==0)
            {
                filtro=tb_Pesquisa.getText();
            }
            else
                filtro="tamanho ='"+tb_Pesquisa.getText()+"'";
            carregaTabela(filtro);
        }
    }

    public void RecebeDados(List<TableDevolucao> lista)
    {
        this.listaDev = lista;
        for(TableDevolucao td : lista)
        {
            TotalDev+=td.getPreco()*td.getQtde();
        }
        AtualizaValorDevolucao();
        txt_Disponivel.setText("Valor dos produtos em devolução:"+ double2string(TotalDev));
        carregaTabela("");
    }
    
    @FXML
    private void clkTabelaAdiciona(MouseEvent event) {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            boolean contem=false;
            for(TableDevolucao td : listaTroca)
            {
                if(td.getNome().equals(tabela.getSelectionModel().getSelectedItem().getNome()) && td.getTamanho().equals(tabela.getSelectionModel().getSelectedItem().getTamanho()))
                {
                    contem=true;
                    td.setQtde(td.getQtde()+1);  
                }
                    
            }
            if(!contem)
            {
                TableDevolucao td = tabela.getSelectionModel().getSelectedItem();
                TableDevolucao tdn = new TableDevolucao(td.getCodigo(),td.getNome(),td.getPreco(),1,td.getTamanho(),td.getCodVenda());
                listaTroca.add(tdn);
                
            }
            TotalTroca+=tabela.getSelectionModel().getSelectedItem().getPreco();
           tabelaselec.setItems(FXCollections.observableArrayList(listaTroca));
           tabelaselec.refresh();
           AtualizaValorDevolucao();
        }
    }

    @FXML
    private void clkTabelaRemove(MouseEvent event) {
        if (event.getClickCount() == 2 && tabelaselec.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tabelaselec.getSelectionModel().getSelectedItem().getQtde()>1)
            {
                listaTroca.get(tabelaselec.getSelectionModel().getSelectedIndex()).setQtde(tabelaselec.getSelectionModel().getSelectedItem().getQtde()-1);
            }
            else
                listaTroca.remove(tabelaselec.getSelectionModel().getSelectedIndex());
            TotalTroca=-tabelaselec.getSelectionModel().getSelectedItem().getPreco();
            tabelaselec.setItems(FXCollections.observableArrayList(listaTroca));
            tabelaselec.refresh();
            AtualizaValorDevolucao();
        }
    }
    
    public static String double2string(double v)
    {
        return String.format("%,.2f", v);
    }
    
    private void AtualizaValorDevolucao()
    {
        txt_total.setText("Total da Troca: "+double2string(TotalTroca));
    }

    @FXML
    private void clkPesqusa(KeyEvent event) {
        if(tb_Pesquisa.getText().isEmpty())
        {
            carregaTabela("");
        }
    }
  
}
