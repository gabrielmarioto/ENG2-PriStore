/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import Mask.MaskFieldUtil;

import Model.Consignado;
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
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

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
    private JFXButton btn_Alterar;
    @FXML
    private JFXButton btn_Apagar;
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
    private TableColumn<Consignado, Double> colvalor;
    @FXML
    private TableColumn<Consignado, LocalDate> coldata;
    @FXML
    private TableColumn<Consignado, String> cloNome;
    
    private Usuario u;

    private static Object con;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("codCompra"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valorTotal"));
        coldata.setCellValueFactory(new PropertyValueFactory("dataCompra"));
        cloNome.setCellValueFactory(new PropertyValueFactory("dataCompra"));
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
        btn_Apagar.setDisable(false);
        btn_Alterar.setDisable(false);

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
    private void clkBtAlterar(ActionEvent event)
    {
        /*if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Compra c = new Compra(tabela.getSelectionModel().getSelectedItem().getCodCompra(),
                    new Fornecedor().selectFornecedor(tabela.getSelectionModel().getSelectedItem().getCodForn().getCod()),
                    tabela.getSelectionModel().getSelectedItem().getValorTotal(), tabela.getSelectionModel().getSelectedItem().getDesconto(),
                    tabela.getSelectionModel().getSelectedItem().getDataCompra());
            compra = c;

            Stage stage = (Stage) painel.getScene().getWindow();
            stage.close();
        }*/
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        /*Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Compra c = new Compra();
            c = tabela.getSelectionModel().getSelectedItem();
            List<ItensCompra> aux = new ArrayList();
            ItensCompra item = new ItensCompra();
            aux = item.selectItensCompra("codCompra=" + c.getCodCompra());
            Tamanho t;
            Tamanho temp = new Tamanho();
            for (int i = 0; i < aux.size(); i++)
            {
                item = aux.get(i);
                item.setCodCompra(c);
                item.deleteItensCompra();
                temp = temp.select(item.getTamanho().getTamanho(), item.getCodProduto().getCod());
                t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(),
                    item.getQntd() + temp.getQtde());
                t.updateTamanho();
            }
            if (!c.deleteCompra())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
        }
        compra = null;
        ((Button) event.getSource()).getScene().getWindow().hide();*/
    }

    @FXML
    private void clkBtSelecionar(ActionEvent event)
    {
       /* if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Compra c = new Compra(tabela.getSelectionModel().getSelectedItem().getCodCompra(),
        new Fornecedor().selectFornecedor(tabela.getSelectionModel().getSelectedItem().getCodForn().getCod()),
        tabela.getSelectionModel().getSelectedItem().getValorTotal(),
        tabela.getSelectionModel().getSelectedItem().getDesconto(),
        tabela.getSelectionModel().getSelectedItem().getDataCompra());
            compra = c;

            Stage stage = (Stage) painel.getScene().getWindow();
            stage.close();
        }*/
    }

    public static Object getCompra()
    {
        return con;
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        con = null;
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

}
