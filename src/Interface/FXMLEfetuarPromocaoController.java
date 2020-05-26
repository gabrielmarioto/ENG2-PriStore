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
import Persistencia.PromocaoBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ElementObservableListDecorator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.AbstractList;
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
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private TableColumn<Produto, Integer> codcol;
    @FXML
    private TableColumn<Produto, String> colproduto;
    @FXML
    private TableColumn<Produto, Double > colpreco;  
    @FXML
    private TableColumn<Produto, Double> colprecopromo;
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
    private TableView<Produto> tabela;
     @FXML
    private TableView<Promocao> TabelaPromo;
    
    
    private Usuario u;
    private List<Produto> lista= new ArrayList<>();
    @FXML
    private HBox pnPromo;
   
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
        
        colprecopromo.setCellValueFactory(new PropertyValueFactory("preco2"));
        
        colcodp.setCellValueFactory(new PropertyValueFactory("codigo"));
        colpromo.setCellValueFactory(new PropertyValueFactory("nome"));
        coldata.setCellValueFactory(new PropertyValueFactory("fim"));
        MaskFieldUtil.monetaryField(tb_valor);
        estadoOriginal();
    }

     protected void RecebeDados(Usuario u){
       this.u=u;
    }
     
    protected void RecebeLista(List<Produto> lista)
    {
        this.lista=lista;
        carregaTabelaProd("","");
    }
        
    private void estadoOriginal()
    {
        pnpesquisa.setDisable(true);
        pndados.setDisable(true);
        btn_Confirmar.setDisable(true);
        btn_Cancelar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        btn_Novo.setDisable(false);
        pnPromo.setDisable(false);
        TabelaPromo.setDisable(false);

        
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
        if(res.isEmpty())
            System.out.println("é");
        ObservableList<Promocao> modelo;
        modelo = FXCollections.observableArrayList(res);
        TabelaPromo.setItems(modelo);
        TabelaPromo.refresh();
    }
    
    private String verificatipo()
    { 
        if(cbb_tipo.getSelectionModel().getSelectedIndex()==0)
            return "DF";
        if(cbb_tipo.getSelectionModel().getSelectedIndex()==1)
            return "DP";
        return "";
    }
    
    
    private double CasasDecimais2(double  valor)
    {
        BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
        double novoValor = bd.doubleValue();
        return novoValor;
        
    }
    
        private void carregaTabelaProd(String fil,String filtro)
    {
        List<Produto> listafiltrada = new ArrayList<>();
        String tipo = verificatipo();

        double valor =0;
        if(!tipo.isEmpty() && tb_valor.getText().length()>0)   
        {
            System.out.println(tb_valor.getText());
            tb_valor.setText(tb_valor.getText().replace(".", ","));
            valor = Double.parseDouble(tb_valor.getText().replace(".", "").replace(",", "."));
            if(tipo.equals("DF"))
            {
                for(int i=0;i<lista.size();i++)  
                    lista.get(i).setPreco2(CasasDecimais2(lista.get(i).getPreco()-valor));
            }
            else
                for(int i=0;i<lista.size();i++)  
                    lista.get(i).setPreco2(CasasDecimais2(lista.get(i).getPreco()-lista.get(i).getPreco()*valor/100));   
        }
        else
        {
            for(int i=0;i<lista.size();i++)
                lista.get(i).setPreco2(CasasDecimais2(lista.get(i).getPreco()));
        }
        
        
        for(Produto p : lista)
        {
            if(fil.equals("categoria"))
            {
                if(p.getCodCategoria().getNome().equals(filtro))
                    listafiltrada.add(p);
            }
            else
            if(fil.equals("Nome"))
            {
                if(p.getNome().contains(filtro))
                    listafiltrada.add(p);
            }
            else
            if(fil.equals("Coleção"))
            {
                if(p.getCodColecao().getNome().equals(filtro))
                    listafiltrada.add(p);
            } 
        }
        ObservableList<Produto> modelo;
        if(listafiltrada.isEmpty())
        {
            modelo = FXCollections.observableArrayList(lista);
        
        }
        else
            modelo = FXCollections.observableArrayList(listafiltrada); 
        tabela.setItems(modelo);
        tabela.refresh();
    }

    private void estadoEdicao()
    {
        pnpesquisa.setDisable(false);
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        pnPromo.setDisable(true);
        TabelaPromo.setDisable(true);
        tb_Nome.requestFocus();
        carregaTabelaProd("", "");
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (TabelaPromo.getSelectionModel().getSelectedItem() != null)
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
                lista.clear();
            }
            carregaTabelaPromo("");
        }
        estadoOriginal();
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        Promocao p = new Promocao();
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
            String tipo = verificatipo();
            if(!tipo.isEmpty())
            {
                if(tb_valor.getText().length()>0)
                {
                    if(dt_inicial.getValue()!=null && !dt_inicial.getValue().isBefore(LocalDate.now()))
                    {
                        if(dt_final.getValue()!=null && dt_final.getValue().isAfter(dt_inicial.getValue()))
                        {
                            if(!lista.isEmpty())
                            {
                            
                                p= new Promocao(cod,tb_Nome.getText(),Date.valueOf(dt_inicial.getValue()),Date.valueOf(dt_final.getValue()),
                                        tipo,Double.parseDouble(tb_valor.getText().replace(".", "").replace(",", ".")));
                                if (p.getCodigo()== 0) // novo cadastro
                                {
                                    if (!p.insertPromocao())
                                    {                                   
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                    else
                                    {
                                        p= new Promocao().selectPromocao("nome like '%"+p.getNome()+"%'").get(0);
                                        for(Produto pr : lista)
                                        {
                                            pr.setCodPromo(p);
                                            pr.update();
                                        }
                                        a.setContentText("Gravado com Sucesso");
                                        a.showAndWait();
                                        lista.clear();
                                        estadoOriginal();

                                    }
                                }
                                else
                                {
                                    if (!p.updatePromocao())
                                    {
                                        a.setContentText("Problemas ao Alterar");
                                        a.showAndWait();
                                    }
                                    else
                                    {
                                        List<Produto> aux = new Produto().selectProduto("codpromocao ="+p.getCodigo());
                                        aux.removeAll(lista);
                                        for(Produto pr : aux)
                                        {
                                            pr.setCodPromo(null);
                                            pr.update();
                                        }

                                        for(Produto pr : lista)
                                        {
                                            pr.setCodPromo(p);
                                            pr.update();
                                        }
                                        a.setContentText("Alterado com Sucesso");
                                        a.showAndWait();
                                        lista.clear();
                                        estadoOriginal();

                                    }
                                }
                            }
                            else
                            {
                                 a.setContentText("Nenhum produto selecionado!");
                                a.showAndWait();
                            }
                        }
                        else
                        {
                            a.setContentText("Data Final incorreta!");
                            a.showAndWait();
                            
                        }
                    }
                    else
                    {
                        a.setContentText("Data Inicial incorreta!");
                        a.showAndWait();                
                    }
                }
                else
                {
                    a.setContentText("Dados de Valor promocional incorreto!");
                        a.showAndWait();    
                }
            }else
            {
                a.setContentText("Selecione o Tipo de Desconto!");
                a.showAndWait();       
            }
        }
        else
        {
            a.setContentText("Insira o nome da Promoção!");
            a.showAndWait();  
        }
            
        carregaTabelaPromo("");
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

    private void clkTxPesquisa(KeyEvent event)
    {
        String filtro =  cbb_filtro.getValue() + ") ";

        carregaTabelaPromo(filtro + " like '%" + tb_Pesquisa.getText().toUpperCase() + "%'");
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event)
    {
        String filtro = cbb_filtro.getValue().toLowerCase();

        carregaTabelaProd(filtro, tb_Pesquisa.getText().toUpperCase().toUpperCase());
    }

    @FXML
    private void clkTabela(MouseEvent event)
    {
        if (event.getClickCount() == 2 && TabelaPromo.getSelectionModel().getSelectedIndex() >= 0)
        {
            pndados.setDisable(true);
            if(u.getNivel()>1)
                btn_Alterar.setDisable(false);
            btn_Novo.setDisable(true);
            if(u.getNivel()>2)
                btn_Apagar.setDisable(false);

            tb_Codigo.setText("" + TabelaPromo.getSelectionModel().getSelectedItem().getCodigo());
            tb_Nome.setText(TabelaPromo.getSelectionModel().getSelectedItem().getNome());
            tb_valor.setText(String.valueOf(TabelaPromo.getSelectionModel().getSelectedItem().getValor()));
            
            dt_inicial.setValue(TabelaPromo.getSelectionModel().getSelectedItem().getInicio().toLocalDate());
            dt_final.setValue(TabelaPromo.getSelectionModel().getSelectedItem().getFim().toLocalDate());
            if(TabelaPromo.getSelectionModel().getSelectedItem().getTipo().equals("DF"))
                cbb_tipo.getSelectionModel().select(0);
            else
                cbb_tipo.getSelectionModel().select(1);
            lista= new Produto().selectProduto("codpromocao="+tb_Codigo.getText());
            carregaTabelaProd("", "");
        }
    }



    @FXML
    private void clkTxPesquisaPromo(KeyEvent event) {
    }

    @FXML
    private void clkBtPesquisarPromo(ActionEvent event) {
    }

    @FXML
    private void clkAdicionar(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLSelecionaProdutos.fxml"));
            Parent root;
            try {
                root = (Parent) loader.load();
                FXMLSelecionaProdutosController ctr = loader.getController();
                ctr.RecebeDados(this,this.lista);
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException ex) {
                
            }
    }

    @FXML
    private void clkRemover(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex()>-1)
        {
            lista.remove(tabela.getSelectionModel().getSelectedItem());
            carregaTabelaProd("","");
        }   
        else
        {
            Alert a= new Alert(Alert.AlertType.ERROR);
            a.setContentText("selecione um produto");
        }
            
    }


}
