/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import Mask.MaskFieldUtil;
import Model.Cliente;

import Model.Consignado;
import Model.Funcionario;
import Model.Usuario;
import Model.Venda;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLProcurarVendaController implements Initializable
{

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btn_Selecionar;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private TableView<Venda> tabela;
    @FXML
    private TableColumn<Venda, Integer> colcod;
    @FXML
    private TableColumn<Venda, LocalDate> coldata;
    @FXML
    private TableColumn<Venda, String> colcliente;
    
    private Usuario u;
    private static Object venda;
    
    @FXML
    private TableColumn<Venda, String> colstatus;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        coldata.setCellValueFactory(new PropertyValueFactory("data"));
        colcliente.setCellValueFactory(new PropertyValueFactory("cliente")); 
        colstatus.setCellValueFactory(new PropertyValueFactory("tipopgto"));
        estadoOriginal();

    }

    public void RecebeDados(Usuario u)
    {
        this.u = u;
    }

    private void estadoOriginal()
    {
        pnpesquisa.setDisable(false);
        btn_Selecionar.setDisable(false);
        btn_Cancelar.setDisable(false);

        carregaTabela("");
    }

    private void carregaTabela(String filtro)
    {
        
        Venda v = new Venda();
        List<Venda> res = v.selectVendas(filtro);
        ObservableList<Venda> modelo;
        modelo = FXCollections.observableArrayList(res);
        System.out.println(modelo.isEmpty());
        tabela.setItems(modelo);
        
        
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Cliente");
        Filtro.add("Data");
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);
    }
    @FXML
    private void clkBtSelecionar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            venda = new Venda(
            tabela.getSelectionModel().getSelectedItem().getCod(),
            tabela.getSelectionModel().getSelectedItem().getData(),
            new Cliente().selectCliente(tabela.getSelectionModel().getSelectedItem().getCliente().getCod()),
            tabela.getSelectionModel().getSelectedItem().getTipopgto());
            Stage stage = (Stage) painel.getScene().getWindow();
                stage.close();
        }
    }

    public static Object getVenda()
    {
        return venda;
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        venda = null;
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
       
    }
    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = "";
        /*if (cbb_filtro.getValue() == "Cliente")
        {
            filtro = "valorTotal" + " = " + tb_Pesquisa.getText();
        }else 
        if (cbb_filtro.getValue().compareTo("Data") == 0)
        {
            MaskFieldUtil.dateField(tb_Pesquisa);
            filtro = "data = " + tb_Pesquisa.getText();
        }*/
        carregaTabela(filtro);
    }

    private void clkBtnFechar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String st = "A";
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tabela.getSelectionModel().getSelectedItem().getTipopgto().equals("P"))
                st = "Parcelado";
            else
                st =  "A Vista";
                       
            Venda v = new Venda(
            tabela.getSelectionModel().getSelectedItem().getCod(),
            tabela.getSelectionModel().getSelectedItem().getData(),
            new Cliente().selectCliente(tabela.getSelectionModel().getSelectedItem().getCliente().getCod()),
            tabela.getSelectionModel().getSelectedItem().getTipopgto());
            
            if(!v.updateVenda())
            {
               a.setContentText("Erro ao alterar!");
               a.show();
            }else
            {
                carregaTabela("");
            }
        }
    }

}
