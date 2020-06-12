/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Cliente;
import Model.Produto;
import Model.TableDevolucao;
import Model.Tamanho;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLEfetuarDevolucaoController implements Initializable {
    @FXML
    private JFXComboBox<Cliente> cbb_cli;
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
    private TableColumn<TableDevolucao, Integer> colCod;
    @FXML
    private TableColumn<TableDevolucao, Double> colProd;
    @FXML
    private TableColumn<TableDevolucao, String> colPreco;
    @FXML
    private TableColumn<TableDevolucao, Date> colData;
    @FXML
    private TableView<TableDevolucao> tabela;
    @FXML
    private TableColumn<TableDevolucao, Integer> coltam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colProd.setCellValueFactory(new PropertyValueFactory("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colData.setCellValueFactory(new PropertyValueFactory("data"));
        coltam.setCellValueFactory(new PropertyValueFactory("tamanho"));
    }    

    
     private void carregaTabela(String filtro)
    {
//        List<Tamanho> res = t.selectTamanho(filtro);
//        ObservableList<Tamanho> modelo;
//        modelo = FXCollections.observableArrayList(res);
//        tabela.setItems(modelo);
//        cbb_Produto.setItems(FXCollections.observableArrayList(new Produto().selectProduto("")));
//
//        List<String> Filtro = new ArrayList<>();
        
    }
    
    @FXML
    private void clkDevolucao(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
    }

    @FXML
    private void clkTxPesquisaPromo(KeyEvent event) {
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event) {
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }
    
}
