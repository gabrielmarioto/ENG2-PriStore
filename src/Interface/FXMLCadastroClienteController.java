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
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.layout.region.Margins;
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
    @FXML
    private JFXTextField tb_Saldo;

    private Usuario u;
    @FXML
    private JFXComboBox<String> cbb_Filtro;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colcod.setCellValueFactory(new PropertyValueFactory("cod"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        colcpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        
        MaskFieldUtil.maxField(tb_Email, 40);
        MaskFieldUtil.maxField(tb_End, 40);
        MaskFieldUtil.maxField(tb_Nome, 40);
        
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Endereco");
        
        cbb_Filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_Filtro.getSelectionModel().select(0);
        
        MaskFieldUtil.cpfField(tb_Cpf);
        MaskFieldUtil.foneField(tb_Telefone);
        MaskFieldUtil.monetaryField(tb_Saldo);
        estadoOriginal();
    }
    
     protected void RecebeDados(Usuario u){
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
            if (!c.deleteCliente())
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
        int cod;
        Cliente c = new Cliente();
        LocalDate dataAtual = LocalDate.now();
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
            if (tb_Cpf.getText().length() > 0)
            {
                if (tb_Telefone.getText().length() > 0)
                {
                    if (tb_End.getText().length() > 0)
                    {
                        if (tb_Email.getText().length() > 0)
                        {
                            
                            if (cbb_Sexo.getSelectionModel().getSelectedIndex()!= -1)
                            {
                                if (dtp_nascimento.getValue().isBefore(dataAtual))
                                {
                                    c = new Cliente(cod, tb_Nome.getText(), tb_Cpf.getText().replace(".", "").replace("-", ""), tb_End.getText(), tb_Email.getText(), tb_Telefone.getText(), cbb_Sexo.getValue().charAt(0), Float.parseFloat(tb_Saldo.getText().replace(",", ".")), dtp_nascimento.getValue());
                                    if(tb_Saldo.getText().length() == 0)
                                        c.setSaldo(0);
                                    if (c.getCod() == 0) // novo cadastro
                                    {
                                        if (!c.insertCliente())
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
                                    if (!c.updateCliente())
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
                                    a.setContentText("Corrija a Data de Nascimento!");
                                    a.showAndWait();
                                }
                            } else
                            {
                                a.setContentText("Informe o Sexo!");
                                a.showAndWait();
                            }
                        }
                        else
                        {
                             a.setContentText("Informe o Email!");
                            a.showAndWait();
                        }
                    } else
                    {
                        a.setContentText("Informe o Endereço!");
                        a.showAndWait();
                    }
                } else
                {
                    a.setContentText("Informe o Telefone!");
                    a.showAndWait();
                }
            } else
            {
                a.setContentText("Informe o CPF!");
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
        String fil;
        if(cbb_Filtro.getValue() == "Endereco")
            fil = "cli_end";
        else
            fil = "cli_nome";
        
        String filtro = "upper(" + fil + ") ";
        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkTabela(MouseEvent event) {
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
            tb_Cpf.setText(tabela.getSelectionModel().getSelectedItem().getCpf());
            tb_End.setText(tabela.getSelectionModel().getSelectedItem().getEnd());
            tb_Email.setText(tabela.getSelectionModel().getSelectedItem().getEmail());
            tb_Telefone.setText(tabela.getSelectionModel().getSelectedItem().getTelefone());
            dtp_nascimento.setValue(tabela.getSelectionModel().getSelectedItem().getDtNascimento());
            tb_Saldo.setText(tabela.getSelectionModel().getSelectedItem().getSaldo()+"");
            
            System.out.println(tabela.getSelectionModel().getSelectedItem().getSaldo());
            
            cbb_Sexo.getSelectionModel().select(0);// gambis
            cbb_Sexo.getSelectionModel().select(tabela.getSelectionModel().getSelectedItem().getSexo());
        }
    }



    @FXML
    private void clkTxPesquisa(KeyEvent event) {
        String fil;
        if(cbb_Filtro.getValue() == "Endereco")
            fil = "cli_end";
        else
            fil = "cli_nome";
        
        String filtro = "upper(" + fil + ") ";
        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    
    

    

}
