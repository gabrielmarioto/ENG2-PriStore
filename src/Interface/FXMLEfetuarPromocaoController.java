/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Marca;
import Model.Produto;
import Model.Promocao;
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ElementObservableListDecorator;
import java.net.URL;
import java.sql.Date;
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
public class FXMLEfetuarPromocaoController implements Initializable
{

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
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXButton btn_Pesquisar;
    @FXML
    private JFXTextField tb_Pesquisa1;
    @FXML
    private JFXButton btn_Pesquisar1;
    @FXML
    private JFXDatePicker dt_inicial;
    @FXML
    private JFXDatePicker dt_final;
    @FXML
    private JFXComboBox<String> cbb_tipo;
    @FXML
    private JFXTextField tb_valor;
    @FXML
    private JFXButton btn_selecionar;
    @FXML
    private TableColumn<Produto, Integer> codcol;
    @FXML
    private TableColumn<Produto, String> colproduto;
    @FXML
    private TableColumn<Produto, Double > colpreco;
    @FXML
    private TableColumn<Double, Double> colprecopromo;
    @FXML
    private JFXButton btn_adicionar;
    @FXML
    private JFXButton btn_remover;
    @FXML
    private TableColumn<Promocao, Integer> colcodp;
    @FXML
    private TableColumn<Promocao, String> colpromo;
    @FXML
    private TableColumn<Promocao, Date> coldata;
    @FXML
    private TableView<Double> tabela2;
    @FXML
    private TableView<Produto> tabela;
     @FXML
    private TableView<Promocao> TabelaPromo;
    
    
    private Usuario u;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        codcol.setCellValueFactory(new PropertyValueFactory("cod"));
        colproduto.setCellValueFactory(new PropertyValueFactory("nome"));
        colpreco.setCellValueFactory(new PropertyValueFactory("preco"));
        
        colprecopromo.setCellValueFactory(new PropertyValueFactory("valor"));
        
        colcodp.setCellValueFactory(new PropertyValueFactory("codigo"));
        colpromo.setCellValueFactory(new PropertyValueFactory("nome"));
        coldata.setCellValueFactory(new PropertyValueFactory("fim"));
        MaskFieldUtil.monetaryField(tb_valor);
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
        
        List<String> Tipo = new ArrayList<>();
        Tipo.add("Desconto em Quantia fixa");
        Tipo.add("Desconto em porcentagem");
        
        List<String> Filtro= new ArrayList<>();
        Filtro.add("Nome");
        Filtro.add("Categoria");
        Filtro.add("Coleção");
        cbb_tipo.setItems(FXCollections.observableArrayList(Tipo));
        cbb_filtro.setItems(FXCollections.observableArrayList(Filtro));
        cbb_filtro.getSelectionModel().select(0);

        carregaTabelaPromo("");
    }

    private void carregaTabelaPromo(String filtro)
    {
        Promocao p = new Promocao();
        List<Promocao> res = p.selectPromocao(filtro);
        ObservableList<Promocao> modelo;
        modelo = FXCollections.observableArrayList(res);
        TabelaPromo.setItems(modelo);
    }
    
    private String verificatipo(JFXComboBox<String> cbb_tipo )
    {   
        if(cbb_tipo.getSelectionModel().getSelectedIndex()==0 && tb_valor.getText().length()>0)
            return "DF";
        if(cbb_tipo.getSelectionModel().getSelectedIndex()==1)
            return "DP";
        
        return "";
    }
    
    private void carregaTabelaProd(String filtro)
    {
        Produto p = new Produto();
        List<Produto> res = p.selectProduto(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        String tipo = verificatipo(cbb_tipo);
        double valor =0;  
        
        ObservableList<Double> modelo2 = null;
        if(!tipo.isEmpty() && !tb_valor.getText().isEmpty())   
        {
            valor = Double.parseDouble(tb_valor.getText());
            List<Double> res2 = new ArrayList<>();
            if(tipo=="DF")
            {
                for(int i=0;i<res.size();i++)  
                    res2.add(res.get(i).getPreco()-valor);
            }
            else
                for(int i=0;i<res.size();i++)  
                    res2.add(res.get(i).getPreco()-res.get(i).getPreco()*valor/100);
            
            modelo2 = FXCollections.observableArrayList(res2);
            tabela2.setItems(modelo2);
        }
        else
        tabela2.setItems(modelo2);
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
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            Promocao p = (Promocao) TabelaPromo.getSelectionModel().getSelectedItem();
            tb_Codigo.setText("" + p.getCodigo());
            tb_Nome.setText(p.getNome());
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
            Promocao p = new Promocao();
             p = TabelaPromo.getSelectionModel().getSelectedItem();
            if (!p.deletePromocao())
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            carregaTabelaPromo("");
        }
        estadoOriginal();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        Marca m = new Marca();
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
            m = new Marca(cod, tb_Nome.getText());
            if (m.getCod() == 0)
            {
                if (!m.insertMarca())
                {
                    a.setContentText("Problemas ao Gravar");
                }
            } else
            {
                if (!m.updateMarca())
                {
                    a.setContentText("Problemas ao Alterar");
                    a.showAndWait();
                }
            }
            estadoOriginal();
        } else
        {
            a.setContentText("Informe o nome!");
            a.showAndWait();
        }

        carregaTabelaPromo("");
    }

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

    private void clkTxPesquisa(KeyEvent event)
    {
        String filtro = "upper("+ cbb_filtro.getValue() + ") ";

        carregaTabelaPromo(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = "upper("+ cbb_filtro.getValue() + ") ";

        carregaTabelaPromo(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

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
        }
    }

    @FXML
    private void clkBtCancelar(ActionEvent event) {
    }

    @FXML
    private void clkseleciona(ActionEvent event) {
    }

    @FXML
    private void clkTxPesquisaPromo(KeyEvent event) {
    }

    @FXML
    private void clkadicionar(ActionEvent event) {
    }

    @FXML
    private void clkremover(ActionEvent event) {
    }

    @FXML
    private void clkBtPesquisarPromo(ActionEvent event) {
    }

}
