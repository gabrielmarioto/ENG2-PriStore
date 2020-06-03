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
public class FXMLProcurarConsignadoController implements Initializable
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
    private TableView<Consignado> tabela;
    @FXML
    private TableColumn<Consignado, Integer> colcod;
    @FXML
    private TableColumn<Consignado, LocalDate> coldata;
    @FXML
    private TableColumn<Consignado, LocalDate> coldatadev;
    @FXML
    private TableColumn<Consignado, String> colcliente;
    @FXML
    private TableColumn<Consignado, String> colfuncionario;
    
    private Usuario u;

    private static Object consignado;
    @FXML
    private TableColumn<Consignado, String> colstatus;
    @FXML
    private JFXButton btn_fechar;
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("codCompra"));
        coldata.setCellValueFactory(new PropertyValueFactory("dtEntrega"));
        coldatadev.setCellValueFactory(new PropertyValueFactory("dtRetorno"));
        colcliente.setCellValueFactory(new PropertyValueFactory("codCliente"));
        colfuncionario.setCellValueFactory(new PropertyValueFactory("codFuncionario"));
        colstatus.setCellValueFactory(new PropertyValueFactory("status"));
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
        
        Consignado c = new Consignado();
        List<Consignado> res = c.selectConsignado(filtro);
        ObservableList<Consignado> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        
        
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Em Aberto");
        Filtro.add("Fechada");
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);
    }
    @FXML
    private void clkBtSelecionar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Consignado c = new Consignado(tabela.getSelectionModel().getSelectedItem().getCod(),
            new Funcionario().selectFuncionario(tabela.getSelectionModel().getSelectedItem().getCodFuncionario().getCodigo()),
            tabela.getSelectionModel().getSelectedItem().getDtEntrega(),
            tabela.getSelectionModel().getSelectedItem().getDtRetorno(),
            new Cliente().selectCliente(tabela.getSelectionModel().getSelectedItem().getCodCliente().getCod()),       
            tabela.getSelectionModel().getSelectedItem().getStatus());
            if(c.getStatus().equals("F"))
            {
                consignado = c;
                Stage stage = (Stage) painel.getScene().getWindow();
                stage.close();
            }
            else
            {
              a.setContentText("Para alterar a consignação deve estar fechada!");
              a.show();
            }

            
        }
    }

    public static Object getConsignado()
    {
        return consignado;
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        consignado = null;
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
        /*if (cbb_filtro.getValue() == "Nome")
        {
            filtro = "valorTotal" + " = " + tb_Pesquisa.getText();
        }else 
        if (cbb_filtro.getValue() == "Em Aberto")
        {
            MaskFieldUtil.dateField(tb_Pesquisa);
            filtro = "status = 'A'";
        }else // fechado
        {
            filtro = "status = 'F'";
        }
*/
        carregaTabela(filtro);
    }

    @FXML
    private void clkBtnFechar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        String st = "A";
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tabela.getSelectionModel().getSelectedItem().getStatus().equals("A"))
                st = "F";
            else
                st =  "A";
                       
            Consignado c = new Consignado(tabela.getSelectionModel().getSelectedItem().getCod(),
            new Funcionario().selectFuncionario(tabela.getSelectionModel().getSelectedItem().getCodFuncionario().getCodigo()),
            tabela.getSelectionModel().getSelectedItem().getDtEntrega(),
            tabela.getSelectionModel().getSelectedItem().getDtRetorno(),
            new Cliente().selectCliente(tabela.getSelectionModel().getSelectedItem().getCodCliente().getCod()),       
            st);
            
            if(!c.updateConsignado())
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
