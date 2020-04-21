/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Categoria;
import Model.Cliente;
import Model.Colecao;
import Model.Marca;
import Model.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class FXMLCadastroClienteController implements Initializable
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
    private JFXTextField tb_End;
    @FXML
    private JFXTextField tb_Telefone;
    @FXML
    private JFXComboBox<String> cbb_Sexo;
    @FXML
    private JFXTextField tb_Cpf;
    @FXML
    private JFXTextField tb_Email;
    @FXML
    private JFXDatePicker dtp_nascimento;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private TableView<Cliente> tabela;
    @FXML
    private TableColumn<Cliente, Integer> colcod;
    @FXML
    private TableColumn<Cliente, String> colnome;
    @FXML
    private TableColumn<Cliente, String> colcpf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        MaskFieldUtil.cpfField(tb_Cpf);
        MaskFieldUtil.foneField(tb_Telefone);
        
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
    private void carregaTabela(String filtro)
    {

        Cliente c = new Cliente();
        List<Cliente> res = c.selectCliente(filtro);
        ObservableList<Cliente> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        
        List<String> Sexo = new ArrayList<>();
        Sexo.add("M");
        Sexo.add("F");
       
        cbb_Sexo.setItems(FXCollections.observableArrayList(Sexo));
        
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
    @FXML
    private void clkBtNovo(ActionEvent event) {
        tb_Codigo.setDisable(true);
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Cliente c = (Cliente) tabela.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + c.getCod());
            tb_Nome.setText(c.getNome());
            estadoEdicao();
        }
    }
    
    @FXML
    private void clkBtApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Cliente c = new Cliente();
            c = tabela.getSelectionModel().getSelectedItem();
            if (!c.deleteCategoria())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            carregaTabela("");
        }
        estadoOriginal();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) {
        /*int cod;
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
        carregaTabela("");*/
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
        if (!pndados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else
        {
            spnprincipal.setCenter(null);
        }
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event) {
        String filtro="upper(fun_"+tb_Pesquisa.getText()+") ";
        carregaTabela(filtro+ " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event) {
    }

    
    

    

}
