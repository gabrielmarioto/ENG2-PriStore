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
import Model.Funcionario;
import Model.Marca;
import Model.Produto;
import Model.Usuario;
import Persistencia.UsuarioBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLCadastroFuncionarioController implements Initializable
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
    private VBox pnpesquisa;
    @FXML
    private TableView<Funcionario> tabela;
    @FXML
    private TableColumn<Funcionario, Integer> colcod;
    @FXML
    private TableColumn<Funcionario, String> colnome;
    @FXML
    private TableColumn<Funcionario, String> coltelefone;
    @FXML
    private TableColumn<Funcionario, Integer> colcid;
    @FXML
    private JFXTextField tb_Codigo;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private JFXTextField tb_Salario;
    @FXML
    private JFXTextField tb_Endereco;
    @FXML
    private JFXTextField tb_cpf;
    @FXML
    private JFXTextField tb_Email;
    @FXML
    private JFXTextField tb_Telefone;
    @FXML
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXTextField tb_Bairro;
    @FXML
    private JFXTextField tb_Cidade;
    @FXML
    private JFXTextField tb_cep;
    @FXML
    private JFXComboBox<String> cbb_sexo;
    
    private Usuario u;
    private TelaPrincipalController ctr;
    private ContextMenu contextMenu;
    private MenuItem cria;
    private MenuItem altera;
    private MenuItem deleta;
    private Funcionario f;
    private Usuario UsuAlt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colcod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colnome.setCellValueFactory(new PropertyValueFactory("nome"));
        coltelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colcid.setCellValueFactory(new PropertyValueFactory("cidade"));
        MaskFieldUtil.monetaryField(tb_Salario);
        MaskFieldUtil.cpfField(tb_cpf);
        MaskFieldUtil.foneField(tb_Telefone);
        MaskFieldUtil.cepField(tb_cep);
        estadoOriginal();
        contextMenu = new ContextMenu();

        cria = new MenuItem("Criar Usuario");
        cria.setOnAction((event) -> {
            CriaUsuario();
        });
        
        altera = new MenuItem("Alterar Usuario");
        altera.setOnAction((event) -> {
            AlteraUsuario();
        });
        deleta = new MenuItem("Deletar Usuario");
        deleta.setOnAction((event) -> {
            DeletaUsuario();
        });
        contextMenu.getItems().addAll(cria,altera,deleta);
        
    }

     protected void RecebeDados(Usuario u,TelaPrincipalController ctr){
       this.u=u;
       this.ctr=ctr;
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
        List<String> Sexo = new ArrayList<>();
        Sexo.add("M");
        Sexo.add("F");
        List<String> Filtro= new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Sexo");
        Filtro.add("Cidade");
        Filtro.add("CEP");
        cbb_sexo.setItems(FXCollections.observableArrayList(Sexo));
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);
        
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

        Funcionario f = new Funcionario();
        List<Funcionario> res = f.selectFuncionario(filtro);
        ObservableList<Funcionario> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        

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
            Funcionario f = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + f.getCodigo());
            tb_Nome.setText(f.getNome());
            tb_Salario.setText(String.format("%10.2f", f.getSalario()));
            tb_Email.setText(f.getEmail());
            tb_Endereco.setText(f.getEndereco());
            tb_Telefone.setText(f.getTelefone());
            tb_cpf.setText(f.getCpf());
            estadoEdicao();
            cbb_sexo.getSelectionModel().select(f.getSexo());// gambis
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) throws SQLException
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            Funcionario f = new Funcionario();
            f = tabela.getSelectionModel().getSelectedItem();
            Usuario u = new UsuarioBD().get(f.getCodigo());
            if(this.u.getCodigo()==f.getCodigo())
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Não é possivel excluir a si mesmo!");
                a.showAndWait();
            }
            else
            {
                if(u.getNivel()==3 && u.selectUsuario("usu_nivel=3").size()==1)
                {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Não é possivel excluir este Funcionario, \n pois ele é o unico com Nivel de Usuario 3");
                    a.showAndWait();
                }
                else
                if (!f.delete())
                {
                    a.setContentText("Erro ao excluir!");
                    a.showAndWait();
                }
            }
            
            estadoOriginal();
        }
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event) throws IOException
    {
        int cod;
        Funcionario f = new Funcionario();
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
            if (tb_Salario.getText().length() > 0)
            {
                if (tb_Telefone.getText().length() > 0)
                {
                    if (tb_cpf.getText().length() >= 11)
                    {
                        if (cbb_sexo.getSelectionModel().getSelectedIndex() != -1)
                        {
                            if(tb_Email.getText().length()> 0 && tb_Email.getText().contains("@"))
                            {
                                if (tb_Endereco.getText().length()> 0)
                                {
                                    if (tb_Bairro.getText().length()> 0 )
                                    {
                                        if(tb_Cidade.getText().length()> 0)
                                        {
                                            if(tb_cep.getText().length()> 0)
                                            {
                                                f = new Funcionario(cod, tb_Nome.getText(), tb_cpf.getText(),cbb_sexo.getValue().charAt(0),
                                                        Double.parseDouble(tb_Salario.getText().replace(".", "").replace(",", ".")),
                                                tb_Telefone.getText(), tb_Email.getText(), tb_Endereco.getText(),tb_Bairro.getText(),tb_Cidade.getText()
                                                        ,tb_cep.getText());
                                                boolean flag= false;
                                                if(f.selectFuncionario("").isEmpty())
                                                {
                                                    flag=true;
                                                }
                                                if (f.getCodigo()== 0) // novo cadastro
                                                {
                                                    if (!f.insert())
                                                    {
                                                        a.setContentText("Problemas ao Gravar");
                                                        a.showAndWait();
                                                    }
                                                    else
                                                    {
                                                        a.setContentText("Gravado com Sucesso");
                                                        a.showAndWait();
                                                        estadoOriginal();
                                                        Alert b = new Alert(Alert.AlertType.CONFIRMATION,"Deseja criar uma Usuario para este Funcionario?", ButtonType.YES,ButtonType.NO);
                                                        Optional<ButtonType> result = b.showAndWait();
                                                        if(result.get()==ButtonType.YES)
                                                        {
                                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastraUsuario.fxml"));
                                                            Parent root = (Parent) loader.load();

                                                            FXMLCadastraUsuarioController ctr = loader.getController();
                                                            ctr.RecebeDados(f,this.u);

                                                            Stage stage = new Stage();
                                                            Scene scene = new Scene(root);
                                                            stage.initStyle(StageStyle.UNDECORATED);
                                                            stage.initModality(Modality.APPLICATION_MODAL);
                                                            stage.setScene(scene);
                                                            stage.showAndWait();
                                                        }
                                                        if(flag)
                                                        {
                                                            ctr.VoltaLogin();
                                                        }
                                                    }
                                                    
                                                } else //alteração de cadastro
                                                if (!f.update())
                                                {
                                                    a.setContentText("Problemas ao Alterar");
                                                    a.showAndWait();
                                                }
                                                else
                                                {
                                                    a.setContentText("Alterado com Sucesso");
                                                    a.showAndWait();
                                                    estadoOriginal();
                                                }
                                              
                                            }else
                                            {
                                                a.setContentText("Informe o Cep!");
                                                a.showAndWait();
                                                
                                                tb_cep.requestFocus();
                                            }
                                        }else
                                        {
                                            a.setContentText("Informe a Cidade!");
                                            a.showAndWait();
                                            tb_Cidade.requestFocus();
                                        }
                                    } else
                                    {
                                        a.setContentText("Informe o Bairro!");
                                        a.showAndWait();
                                        tb_Bairro.requestFocus();
                                    }
                                } else
                                {
                                    a.setContentText("Informe o Endereço!");
                                    a.showAndWait();
                                    tb_Endereco.requestFocus();
                                }
                            }else
                            {
                                a.setContentText("Informe o Email!");
                                a.showAndWait();
                                tb_Email.requestFocus();
                            }
                            
                        } else
                        {
                            a.setContentText("Informe o Sexo!");
                            a.showAndWait();
                            cbb_sexo.requestFocus();
                        }
                    }
                    else
                    {
                        a.setContentText("Informe O CPF!");
                        a.showAndWait();
                        tb_cpf.requestFocus();
                    }
                } else
                {
                    a.setContentText("Informe o Telefone!");
                    a.showAndWait();
                    tb_Telefone.requestFocus();
                }
            } else
            {
                a.setContentText("Informe a Salario!");
                a.showAndWait();
                tb_Salario.requestFocus();
            }
        } else
        {
            a.setContentText("Informe o nome!");
            a.showAndWait();
            tb_Nome.requestFocus();
        }
        carregaTabela("");
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        if (!pndados.isDisabled() || btn_Novo.isDisable()) // encontra em estado de edição
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

        String filtro="upper(fun_"+cbb_filtro.getValue()+") ";
        
        carregaTabela(filtro+ " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro="upper(fun_"+cbb_filtro.getValue()+") ";

        carregaTabela(filtro+ " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
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

            tb_Codigo.setText("" + tabela.getSelectionModel().getSelectedItem().getCodigo());
            tb_Nome.setText(tabela.getSelectionModel().getSelectedItem().getNome());
            tb_cpf.setText(tabela.getSelectionModel().getSelectedItem().getCpf());
            tb_Telefone.setText(tabela.getSelectionModel().getSelectedItem().getTelefone());
            tb_Salario.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getSalario()));
            tb_Email.setText(tabela.getSelectionModel().getSelectedItem().getEmail());
            tb_Endereco.setText(tabela.getSelectionModel().getSelectedItem().getEndereco());
            tb_Cidade.setText(tabela.getSelectionModel().getSelectedItem().getCidade());
            tb_Bairro.setText(tabela.getSelectionModel().getSelectedItem().getBairro());
            tb_cep.setText(tabela.getSelectionModel().getSelectedItem().getCep());
            //cbb_sexo.getSelectionModel().select(0);// gambis
            cbb_sexo.getSelectionModel().select(String.valueOf(tabela.getSelectionModel().getSelectedItem().getSexo()));
        }
    }

    @FXML
    private void clkDTabela(ContextMenuEvent event) {
        this.f =(Funcionario) tabela.getSelectionModel().getSelectedItem();
        Usuario u = new Usuario().selectUsuario(f.getCodigo());
        
        if(u==null)
        {
              contextMenu.getItems().get(0).setDisable(false);
              contextMenu.getItems().get(1).setDisable(true);
              contextMenu.getItems().get(2).setDisable(true);
        }
        else
        {
            this.UsuAlt=u;
            contextMenu.getItems().get(0).setDisable(true);
            contextMenu.getItems().get(1).setDisable(false);
            contextMenu.getItems().get(2).setDisable(false);
        }
      
        tabela.setContextMenu(contextMenu);
    }
    
    private void CriaUsuario()
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastraUsuario.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                FXMLCadastraUsuarioController ctr = loader.getController();
                ctr.RecebeDados(this.f,this.u);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
            stage.showAndWait();
            } catch (IOException ex) {
                
            }
    }
    
    private void AlteraUsuario()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAlteraUsuario.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                FXMLAlteraUsuarioController ctr = loader.getController();
                ctr.RecebeDados(this.f,this.UsuAlt,false);

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
            stage.showAndWait();
            } catch (IOException ex) {
                
            }
    } 
    private void DeletaUsuario()
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        Usuario u = new Usuario().selectUsuario(f.getCodigo());
        if(this.u.getLogin().equals(u.getLogin()))
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Não é possivel excluir o proprio Usuario");
            a.showAndWait();
        }
        else
        {
            if(u.getNivel()==3 && u.selectUsuario("usu_nivel=3").size()==1)
            {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Não é possivel excluir este Usuario, \n pois ele é o unico com Nivel de Usuario 3");
                a.showAndWait();
            }
            else
            {
                u.delete();
                a.setContentText("Usuario deletado com sucesso!");
                a.showAndWait();
            }
        }
        
} 
}
