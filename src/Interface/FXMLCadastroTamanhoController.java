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
import Model.Marca;
import Model.Produto;
import Model.Tamanho;
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCadastroTamanhoController implements Initializable
{

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btn_Novo;
    @FXML
    private JFXButton btn_Alterar;
    @FXML
    private JFXButton btn_Apagar;
    @FXML
    private JFXButton btn_Confirmar;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private AnchorPane pndados;
    @FXML
    private JFXComboBox<Produto> cbb_Produto;
    @FXML
    private JFXTextField tb_Tamanho;
    @FXML
    private JFXTextField tb_Quantidade;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXComboBox<String> cbb_Filtro;
    @FXML
    private JFXButton btn_Pesquisa;
    @FXML
    private TableView<Tamanho> tabela;
    @FXML
    private TableColumn<Produto, String> colproduto;
    @FXML
    private TableColumn<Tamanho, String> coltamanho;
    @FXML
    private TableColumn<Tamanho, Integer> colqtde;

    private Usuario u;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colproduto.setCellValueFactory(new PropertyValueFactory("nome"));
        coltamanho.setCellValueFactory(new PropertyValueFactory("tamanho"));
        colqtde.setCellValueFactory(new PropertyValueFactory("qtde"));
        estadoOriginal();
    }

    public void RecebeDados(Usuario u)
    {
        this.u = u;
    }

    private void estadoOriginal()
    {
        pnpesquisa.setDisable(false);
        pndados.setDisable(true);
        btn_Confirmar.setDisable(true);
        btn_Cancelar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        btn_Novo.setDisable(false);

        ObservableList<Node> componentes = pndados.getChildren(); //”limpa” os componentes
        for (Node n : componentes)
        {
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox)
            {
                ((ComboBox) n).getItems().clear();
            }
        }

        carregaTabela("");
    }

    private void estadoEdicao()
    {
        pnpesquisa.setDisable(true);
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        cbb_Produto.requestFocus();
    }

    private void carregaTabela(String filtro)
    {
        Tamanho t = new Tamanho();
        List<Tamanho> res = t.selectTamanho(filtro);
        ObservableList<Tamanho> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        cbb_Produto.setItems(FXCollections.observableArrayList(new Produto().selectProduto("")));

        List<String> Filtro = new ArrayList<>();
        Filtro.add("Produto");
        Filtro.add("Tamanho");
        Filtro.add("Qtde");
        cbb_Filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_Filtro.getSelectionModel().select(0);
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Tamanho t = (Tamanho) tabela.getSelectionModel().getSelectedItem();
            tb_Tamanho.setText(t.getTamanho());
            tb_Quantidade.setText("" + t.getQtde());
            estadoEdicao();
            cbb_Produto.getSelectionModel().select(0);// gambis           
            cbb_Produto.getSelectionModel().select(t.getCodProduto().getCod());
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Tamanho t = new Tamanho();
            t = tabela.getSelectionModel().getSelectedItem();
            if (!t.deleteTamanho())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            carregaTabela("");
        }
        estadoOriginal();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        Tamanho t;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(cbb_Produto.getSelectionModel().getSelectedIndex() != -1)
        {
            if(tb_Tamanho.getText().length() > 0 && tb_Tamanho.getText().length() <= 3)
            {
                if(tb_Quantidade.getText().length() > 0)
                {
                    t = new Tamanho(cbb_Produto.getValue(), tb_Tamanho.getText(), Integer.parseInt(tb_Quantidade.getText()));
                    if(!t.insertTamanho())
                        a.setContentText("Problemas ao Gravar");
                    estadoOriginal();
                }
                else
                {
                     a.setContentText("Informe a quantidade!");
                }
            }
            else
            {
                 a.setContentText("Informe o tamanho!");
            }
        }
        else
        {
             a.setContentText("Informe o produto!");
             a.showAndWait();
        }
        carregaTabela("");
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        if (!pndados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else
        {
            spnprincipal.setCenter(null);
        }
    }

    @FXML
    private void clkTxPesquisa(KeyEvent event)
    {
        String filtro = "upper(" + cbb_Filtro.getValue() + ") ";
        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = "upper(" + cbb_Filtro.getValue() + ") ";
        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            pndados.setDisable(true);
            if (u.getNivel() > 1)
            {
                btn_Alterar.setDisable(false);
            }
            btn_Novo.setDisable(true);
            if (u.getNivel() > 2)
            {
                btn_Apagar.setDisable(false);
            }

            
            tb_Tamanho.setText(tabela.getSelectionModel().getSelectedItem().getTamanho());
            tb_Quantidade.setText(""+tabela.getSelectionModel().getSelectedItem().getQtde());
            
            //FAZER COMBOBOX (GAMBIS COPIADA DO PROFESSOR)
            cbb_Produto.getSelectionModel().select(0);// gambis           
            cbb_Produto.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodProduto().getCod());            
        }
    }
}
