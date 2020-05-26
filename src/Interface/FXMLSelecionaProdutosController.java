/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Mask.MaskFieldUtil;
import Model.Categoria;
import Model.Colecao;
import Model.Funcionario;
import Model.Produto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLSelecionaProdutosController implements Initializable {
    @FXML
    private JFXTextField tb_Nome;
    @FXML
    private JFXComboBox<Categoria> cbb_cat;
    @FXML
    private JFXComboBox<Colecao> cbb_cole;
    @FXML
    private JFXTextField tb_precomin;
    @FXML
    private JFXTextField tb_precomax;
    @FXML
    private JFXButton btn_pesquisar;
    @FXML
    private JFXButton btn_sair;
    @FXML
    private JFXButton btn_confirmar;
    @FXML
    private TableView<Produto> tabelafiltro;
    @FXML
    private TableColumn<Produto, String> colnomefiltro;
    @FXML
    private TableColumn<Produto, Double> colprecofiltro;
    @FXML
    private TableView<Produto> tabelaselec;
    @FXML
    private TableColumn<Produto, String> colnomeselec;
    @FXML
    private TableColumn<Produto, Double> colprecoselec;

    private List<Produto> selecionados;
    private FXMLEfetuarPromocaoController ctr;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colnomefiltro.setCellValueFactory(new PropertyValueFactory("nome"));
        colprecofiltro.setCellValueFactory(new PropertyValueFactory("preco"));
       
        colnomeselec.setCellValueFactory(new PropertyValueFactory("nome"));
        colprecoselec.setCellValueFactory(new PropertyValueFactory("preco"));
        MaskFieldUtil.monetaryField(tb_precomin);
        MaskFieldUtil.monetaryField(tb_precomax);
        
        cbb_cat.setItems(FXCollections.observableArrayList(new Categoria().selectCategoria("")));
        cbb_cole.setItems(FXCollections.observableArrayList(new Colecao().selectColecao("")));
        
        //clkPesquisa(null);
        //AtualizaTabelas();
    }    

    protected void RecebeDados(FXMLEfetuarPromocaoController ctr,List<Produto> lista){
       this.ctr=ctr;
       this.selecionados=lista;
        clkPesquisa(null);
       AtualizaTabelas();
    }
      
    
    @FXML
    private void clkPesquisa(ActionEvent event) {
        String filtro="codPromocao is null ";
        if(tb_Nome.getText().length()>0)
            filtro+=" and nome like '%"+tb_Nome.getText()+"%' ";
        if(cbb_cat.getSelectionModel().getSelectedIndex()>-1)
            filtro+=" and codcategoria = "+cbb_cat.getSelectionModel().getSelectedItem().getCod();
        if(cbb_cole.getSelectionModel().getSelectedIndex()>-1)
            filtro+=" and codcolecao = "+cbb_cole.getSelectionModel().getSelectedItem().getCod();
        if(tb_precomin.getText().length()>0)
            filtro+=" and preco >= "+Double.parseDouble(tb_precomin.getText().replace(".", "").replace(",", "."));
        if(tb_precomax.getText().length()>0)
            filtro+=" and preco <= "+Double.parseDouble(tb_precomax.getText().replace(".", "").replace(",", "."));
        Produto p = new Produto();
        List<Produto> res = p.selectProduto(filtro);
        if(!selecionados.isEmpty())
            for(Produto pr : selecionados)
            {
                res.remove(pr);
            }
        
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabelafiltro.setItems(modelo);
    }

    private void AtualizaTabelas()
    {
        clkPesquisa(null);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(selecionados);
        tabelaselec.setItems(modelo);

    }
    @FXML
    private void clkSair(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void clkConfirma(ActionEvent event) {
        ctr.RecebeLista(selecionados);
        ((Button)event.getSource()).getScene().getWindow().hide();
        
    }

    @FXML
    private void clkAdiciona(MouseEvent event) {
        if (event.getClickCount() == 2 && tabelafiltro.getSelectionModel().getSelectedIndex() >= 0)
        {
            boolean flag=true;
            for(Produto p : selecionados)
            {
                if(p.getNome().equals(tabelafiltro.getSelectionModel().getSelectedItem().getNome()))
                    flag=false;
            }
            if(flag)
                selecionados.add(tabelafiltro.getSelectionModel().getSelectedItem());
            AtualizaTabelas();
        }
    }

    @FXML
    private void clkRemove(MouseEvent event) {
        if (event.getClickCount() == 2 && tabelaselec.getSelectionModel().getSelectedIndex() >= 0)
        {
            selecionados.remove(tabelaselec.getSelectionModel().getSelectedIndex());
            AtualizaTabelas();
        }
    }
    
}
