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
public class FXMLCadastroProdutoController implements Initializable
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
    private JFXTextField tb_Nome;
    @FXML
    private JFXTextField tb_Descricao;
    @FXML
    private JFXTextField tb_Preco;
    @FXML
    private JFXComboBox<Colecao> cbb_Colecao;
    @FXML
    private JFXComboBox<Categoria> cbb_Categoria;
    @FXML
    private JFXTextField tb_Tamanho;
    @FXML
    private JFXComboBox<Marca> cbb_Marca;
    @FXML
    private VBox pnpesquisa;
    private JFXTextField txpesquisa;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colcod;
    @FXML
    private TableColumn<Produto, String> colnome;
    @FXML
    private TableColumn<Produto, Float> colpreco;
    @FXML
    private JFXTextField tb_Codigo;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private JFXComboBox<String> cbb_Filtro;

    private Usuario u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colpreco.setCellValueFactory(new PropertyValueFactory("preco"));
        MaskFieldUtil.monetaryField(tb_Preco);
        estadoOriginal();
    }

     public void RecebeDados(Usuario u){
       this.u=u;
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
        tb_Nome.requestFocus();
    }

    private void carregaTabela(String filtro)
    {
//        Produto p = new Produto();
//        List<Produto> res = p.selectProduto(filtro);
//        ObservableList<Produto> modelo;
//        modelo = FXCollections.observableArrayList(res);
//        tabela.setItems(modelo);
//        List<Categoria> categorias = new Categoria().selectCategoria("");
//        cbb_Categoria.setItems(FXCollections.observableArrayList(categorias));
//        cbb_Colecao.setItems(FXCollections.observableArrayList(new Colecao().selectColecao("")));
//        cbb_Marca.setItems(FXCollections.observableArrayList(new Marca().selectMarca("")));
        Produto p = new Produto();
        List<Produto> res = p.selectProduto(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        cbb_Categoria.setItems(FXCollections.observableArrayList(new Categoria().selectCategoria("")));
        cbb_Colecao.setItems(FXCollections.observableArrayList(new Colecao().selectColecao("")));
        cbb_Marca.setItems(FXCollections.observableArrayList(new Marca().selectMarca("")));
        
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Tamanho");
        Filtro.add("Preco");
        Filtro.add("Descricao");
        cbb_Filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_Filtro.getSelectionModel().select(0);

    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
        tb_Codigo.setDisable(true);
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Produto p = (Produto) tabela.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + p.getCod());
            tb_Nome.setText(p.getNome());
            tb_Descricao.setText(p.getDescricao());
            tb_Preco.setText(String.format("%10.2f", p.getPreco()));
            estadoEdicao();
            cbb_Categoria.getSelectionModel().select(0);// gambis
            cbb_Colecao.getSelectionModel().select(0);// gambis
            cbb_Marca.getSelectionModel().select(0);// gambis
            cbb_Categoria.getSelectionModel().select(p.getCodCategoria().getCod());
            cbb_Colecao.getSelectionModel().select(p.getCodColecao().getCod());
            cbb_Marca.getSelectionModel().select(p.getCodMarca().getCod());
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Produto p = new Produto();
            p = tabela.getSelectionModel().getSelectedItem();
            if (!p.delete())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            carregaTabela("");
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        Produto p = new Produto();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        try
        {
            cod = Integer.parseInt(tb_Codigo.getText());
        } catch (Exception e)
        {
            cod = 0;
        }
        if (tb_Nome.getText().length() > 0)
        {
            if (tb_Descricao.getText().length() > 0)
            {
                if (tb_Preco.getText().length() > 0)
                {
                    if (tb_Tamanho.getText().length() > 0)
                    {
                        if (cbb_Categoria.getSelectionModel().getSelectedIndex() != -1)
                        {
                            if (cbb_Colecao.getSelectionModel().getSelectedIndex() != -1)
                            {
                                if (cbb_Marca.getSelectionModel().getSelectedIndex() != -1)
                                {
                                    p = new Produto(cod, cbb_Categoria.getValue(), tb_Nome.getText(), tb_Tamanho.getText(), Float.parseFloat(tb_Preco.getText().replace(".", "").replace(",", ".")),
                                            tb_Descricao.getText(), cbb_Marca.getValue(), cbb_Colecao.getValue());
                                    if (p.getCod() == 0) // novo cadastro
                                    {
                                        if (!p.insert())
                                        {
                                            a.setContentText("Problemas ao Gravar");
                                            // a.showAndWait();
                                        }
                                    } else //alteração de cadastro
                                    if (!p.update())
                                    {
                                        a.setContentText("Problemas ao Alterar");
                                        a.showAndWait();
                                    }
                                    estadoOriginal();
                                } else
                                {
                                    a.setContentText("Informe a marca!");
                                }
                            } else
                            {
                                a.setContentText("Informe a colecao!");
                            }
                        } else
                        {
                            a.setContentText("Informe a categoria!");
                        }
                    }
                    else
                    {
                        a.setContentText("Informe o tamanho!");
                    }
                } else
                {
                    a.setContentText("Informe o preço!");
                }
            } else
            {
                a.setContentText("Informe a descrição!");
            }
        } else
        {
            a.setContentText("Informe o nome!");
            a.showAndWait();
        }
        carregaTabela("");
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
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
        String filtro = "upper("+ cbb_Filtro.getValue() + ") ";
        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
        
        System.out.println(filtro);
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = "upper("+ cbb_Filtro.getValue() + ") ";

        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");        
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            pndados.setDisable(true);
            if(u.getNivel()>1)
                btn_Alterar.setDisable(false);
            btn_Novo.setDisable(true);
            if(u.getNivel()>2)
                btn_Apagar.setDisable(false);

            tb_Codigo.setText("" + tabela.getSelectionModel().getSelectedItem().getCod());
            tb_Nome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            tb_Descricao.setText(tabela.getSelectionModel().getSelectedItem().getDescricao());
            tb_Preco.setText("" + tabela.getSelectionModel().getSelectedItem().getPreco());

            //FAZER COMBOBOX (GAMBIS COPIADA DO PROFESSOR)
            cbb_Categoria.getSelectionModel().select(0);// gambis
            cbb_Colecao.getSelectionModel().select(0);// gambis
            cbb_Marca.getSelectionModel().select(0);// gambis
            cbb_Categoria.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodCategoria().getCod());
            cbb_Colecao.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodColecao().getCod());
            cbb_Marca.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getCodMarca().getCod());
        }
    }

}
