/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Basicas;

import static Interface.TelaPrincipalController.spnprincipal;
import Model.Categoria;
import Model.Colecao;
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
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
public class FXMLCadastroColecaoController implements Initializable
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
    private VBox pnpesquisa;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXButton btn_Pesquisa;
    @FXML
    private TableView<Colecao> tabela;
    @FXML
    private TableColumn<Colecao, Integer> colcod;
    @FXML
    private TableColumn<Colecao, String> colnome;
    @FXML
    private JFXDatePicker dtp_Inicial;
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
        dtp_Inicial.setDisable(true);        
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
        Colecao c = new Colecao();
        List<Colecao> res = c.selectColecao(filtro);
        ObservableList<Colecao> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        List<String> Filtro = new ArrayList<>();
        Filtro.add("Nome");
        cbb_Filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_Filtro.getSelectionModel().select(0);
    }

    private void estadoEdicao()
    {
        pnpesquisa.setDisable(true);
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        tb_Nome.requestFocus();
        dtp_Inicial.setDisable(false);
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
            Colecao c = (Colecao) tabela.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + c.getCod());
            tb_Nome.setText(c.getNome());
            dtp_Inicial.setValue(c.getDataInicio());
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
            Colecao c = new Colecao();
            c = tabela.getSelectionModel().getSelectedItem();
            if (!c.deleteColecao())
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
        int cod;
        LocalDate dataAtual = LocalDate.now();
        Colecao c = new Colecao();
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
            if (dtp_Inicial.getValue().isBefore(dataAtual))
            {
                c = new Colecao(cod, tb_Nome.getText(), dtp_Inicial.getValue());
                if (c.getCod() == 0)
                {
                    if (!c.insertColecao())
                    {
                        a.setContentText("Problemas ao Gravar");
                    }
                } else
                {
                    if (!c.updateColecao())
                    {
                        a.setContentText("Problemas ao Alterar");
                        a.showAndWait();
                    }
                }
            }            
        } else
        {
            a.setContentText("Informe o nome!");
            a.showAndWait();
        }
        estadoOriginal();
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
        String filtro = "upper("+ cbb_Filtro.getValue() + ") ";

        carregaTabela(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
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
            dtp_Inicial.setValue(tabela.getSelectionModel().getSelectedItem().getDataInicio());
        }
    }

}
