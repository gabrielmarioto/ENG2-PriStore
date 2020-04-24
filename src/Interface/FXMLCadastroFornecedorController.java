/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Fornecedor;
import Model.Funcionario;
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
public class FXMLCadastroFornecedorController implements Initializable
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
    private JFXTextField tb_Codigo;
    @FXML
    private JFXTextField tb_Nome;
    @FXML
    private JFXTextField tb_InscricaoEstadual;
    @FXML
    private JFXTextField tb_Endereco;
    @FXML
    private JFXTextField tb_Cnpj;
    @FXML
    private JFXTextField tb_Email;
    @FXML
    private JFXTextField tb_Bairro;
    @FXML
    private JFXTextField tb_Cidade;
    @FXML
    private JFXTextField tb_Telefone;
    @FXML
    private JFXTextField tb_cep;
    @FXML
    private JFXTextField tb_Numero;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private TableView<Fornecedor> tabela;
    @FXML
    private TableColumn<Fornecedor, Integer> colcod;
    @FXML
    private TableColumn<Fornecedor, String> colnome;
    @FXML
    private TableColumn<Fornecedor, String> coltelefone;
    @FXML
    private TableColumn<Fornecedor, Integer> colcid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colcid.setCellValueFactory(new PropertyValueFactory("cidade"));
        MaskFieldUtil.cnpjField(tb_Cnpj);
        MaskFieldUtil.foneField(tb_Telefone);
        MaskFieldUtil.cepField(tb_cep);
        MaskFieldUtil.numericField(tb_InscricaoEstadual);
        MaskFieldUtil.numericField(tb_Numero);
        estadoOriginal();
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
        Fornecedor f = new Fornecedor();
        List<Fornecedor> res = f.selectFornecedor(filtro);
        ObservableList<Fornecedor> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Sexo");
        Filtro.add("Cidade");
        Filtro.add("CEP");
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);

    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        tb_Codigo.setDisable(true);
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Fornecedor f = (Fornecedor) tabela.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + f.getCod());
            tb_Nome.setText(f.getNome());
            tb_Email.setText(f.getEmail());
            tb_Endereco.setText(f.getEndereco());
            tb_Telefone.setText(f.getTelefone());
            tb_Cnpj.setText(f.getCnpj());
            tb_Numero.setText(Character.toString((char) f.getNumRua()));
            tb_cep.setText(f.getCep());
            estadoEdicao();
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Fornecedor f = new Fornecedor();
            f = tabela.getSelectionModel().getSelectedItem();
            if (!f.deleteFornecedor())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            estadoOriginal();
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        Fornecedor f = new Fornecedor();
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
            if (tb_Numero.getText().length() > 0)
            {
                if (tb_Telefone.getText().length() > 0)
                {
                    if (tb_Cnpj.getText().length() >= 14)
                    {
                        if (tb_InscricaoEstadual.getText().length() == 9)
                        {

                            if (tb_Email.getText().length() > 0 && tb_Email.getText().contains("@"))
                            {
                                if (tb_Endereco.getText().length() > 0)
                                {
                                    if (tb_Bairro.getText().length() > 0)
                                    {
                                        if (tb_Cidade.getText().length() > 0)
                                        {
                                            if (tb_cep.getText().length() > 0)
                                            {
//                                           
                                                f = new Fornecedor(cod, tb_Nome.getText(), tb_Cnpj.getText(), tb_InscricaoEstadual.getText(), tb_Endereco.getText(), tb_Email.getText(), tb_Telefone.getText(), tb_Cidade.getText(), Integer.parseInt(tb_Numero.getText()), tb_Bairro.getText(), tb_cep.getText());

                                                if (f.getCod() == 0) // novo cadastro
                                                {
                                                    if (!f.insertFornecedor())
                                                    {
                                                        a.setContentText("Problemas ao Gravar");
                                                        a.showAndWait();
                                                    } else
                                                    {
                                                        a.setContentText("Gravado com Sucesso");
                                                        a.showAndWait();
                                                        estadoOriginal();
                                                    }

                                                } else //alteração de cadastro
                                                if (!f.updateFornecedor())
                                                {
                                                    a.setContentText("Problemas ao Alterar");
                                                    a.showAndWait();
                                                } else
                                                {
                                                    a.setContentText("Alterado com Sucesso");
                                                    a.showAndWait();
                                                    estadoOriginal();
                                                }

                                            } else
                                            {
                                                a.setContentText("Informe o Cep!");
                                                a.showAndWait();
                                            }
                                        } else
                                        {
                                            a.setContentText("Informe a Cidade!");
                                            a.showAndWait();
                                        }
                                    } else
                                    {
                                        a.setContentText("Informe o Bairro!");
                                        a.showAndWait();
                                    }
                                } else
                                {
                                    a.setContentText("Informe o Endereço!");
                                    a.showAndWait();
                                }
                            } else
                            {
                                a.setContentText("Informe o Email!");
                                a.showAndWait();
                            }
                        }
                        else
                        {
                             a.setContentText("Informe a Inscricao Estadual!");
                            a.showAndWait();
                        }
                    } else
                    {
                        a.setContentText("Informe O CNPJ!");
                        a.showAndWait();
                    }
                } else
                {
                    a.setContentText("Informe o Telefone!");
                    a.showAndWait();
                }
            } else
            {
                a.setContentText("Informe a Salario!");
                a.showAndWait();
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
        String filtro = "upper(" + cbb_filtro.getValue() + ") ";

        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = "upper(" + cbb_filtro.getValue() + ") ";

        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            pndados.setDisable(true);
            btn_Alterar.setDisable(false);
            btn_Novo.setDisable(true);
            btn_Apagar.setDisable(false);

            tb_Codigo.setText("" + tabela.getSelectionModel().getSelectedItem().getCod());
            tb_Nome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            tb_Telefone.setText(tabela.getSelectionModel().getSelectedItem().getTelefone());
            tb_Email.setText(tabela.getSelectionModel().getSelectedItem().getEmail());
            tb_Endereco.setText(tabela.getSelectionModel().getSelectedItem().getEndereco());
            tb_Cidade.setText(tabela.getSelectionModel().getSelectedItem().getCidade());
            tb_Bairro.setText(tabela.getSelectionModel().getSelectedItem().getBairro());
            tb_Cnpj.setText(tabela.getSelectionModel().getSelectedItem().getCnpj());
            tb_Numero.setText(Character.toString((char) tabela.getSelectionModel().getSelectedItem().getNumRua()));
            tb_cep.setText(tabela.getSelectionModel().getSelectedItem().getCep());
        }

    }

}
