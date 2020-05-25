/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Categoria;
import Model.Colecao;
import Model.Compra;
import Model.Fornecedor;
import Model.ItensCompra;
import Model.Marca;
import Model.Produto;
import Model.Tamanho;
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLProcurarCompraController implements Initializable
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
    private TableView<Compra> tabela;
    @FXML
    private TableColumn<Compra, Integer> colcod;
    @FXML
    private TableColumn<Compra, Integer> colfornecedor;
    @FXML
    private TableColumn<Compra, Double> colvalor;
    @FXML
    private TableColumn<Compra, LocalDate> coldata;

    private Usuario u;

    private static Object compra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("codCompra"));
        colfornecedor.setCellValueFactory(new PropertyValueFactory("codForn"));
        colvalor.setCellValueFactory(new PropertyValueFactory("valorTotal"));
        coldata.setCellValueFactory(new PropertyValueFactory("dataCompra"));
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
        Compra c = new Compra();
        List<Compra> res = c.selectCompra(filtro);
        ObservableList<Compra> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);

        List<String> Filtro = new ArrayList<>();
        Filtro.add("Valor");
        Filtro.add("Data");
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Compra c = new Compra(tabela.getSelectionModel().getSelectedItem().getCodCompra(), new Fornecedor().selectFornecedor(tabela.getSelectionModel().getSelectedItem().getCodForn().getCod()), tabela.getSelectionModel().getSelectedItem().getValorTotal(), tabela.getSelectionModel().getSelectedItem().getDesconto(), tabela.getSelectionModel().getSelectedItem().getDataCompra());
            compra = c;

            Stage stage = (Stage) painel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
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
                t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd() + temp.getQtde());
                t.updateTamanho();
            }
            if (!c.deleteCompra())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
        }
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void clkBtSelecionar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            Compra c = new Compra(tabela.getSelectionModel().getSelectedItem().getCodCompra(), new Fornecedor().selectFornecedor(tabela.getSelectionModel().getSelectedItem().getCodForn().getCod()), tabela.getSelectionModel().getSelectedItem().getValorTotal(), tabela.getSelectionModel().getSelectedItem().getDesconto(), tabela.getSelectionModel().getSelectedItem().getDataCompra());
            compra = c;

            Stage stage = (Stage) painel.getScene().getWindow();
            stage.close();
        }
    }

    public static Object getCompra()
    {
        return compra;
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
        carregaTabela("select * from Compra where codCompra = " + tb_Pesquisa.getText());
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        carregaTabela("select * from Compra where codCompra = " + tb_Pesquisa.getText());
    }

}
