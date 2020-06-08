/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.ProdPromo;
import Model.Produto;
import Model.ProdutoPm;
import Model.Promocao;
import Model.Usuario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
    private TableColumn<ProdutoPm, Integer> codcol;
    @FXML
    private TableColumn<ProdutoPm, String> colproduto;
    @FXML
    private TableColumn<ProdutoPm, Double > colpreco;  
    @FXML
    private TableColumn<ProdutoPm, Double> colprecopromo;
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
    private TableView<ProdutoPm> tabela;
     @FXML
    private TableView<Promocao> TabelaPromo;
    
    
    private Usuario u;
    private List<ProdutoPm> lista= new ArrayList<>();
    @FXML
    private HBox pnPromo;
    private Promocao p;
   
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
        MaskFieldUtil.dateField(dt_inicial.getEditor());
        MaskFieldUtil.dateField(dt_final.getEditor());
        estadoOriginal();
    }

     protected void RecebeDados(Usuario u){
       this.u=u;
    }
     
    protected void RecebeLista(List<Produto> lista)
    {
        this.lista.clear();
        for(Produto p : lista)
        {
            this.lista.add(new ProdutoPm(p.getCod(), p.getCodCategoria(), p.getNome(), p.getPreco(), p.getDescricao(), p.getCodMarca(), p.getCodColecao()));
        }
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
        dt_inicial.setValue(null);
        dt_final.setValue(null);
        lista.clear();
        carregaTabelaProd("","");
        carregaTabelaPromo("");
    }

    private void carregaTabelaPromo(String filtro)
    {
        Promocao p = new Promocao();
        List<Promocao> res = p.selectPromocao(filtro);
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
        List<ProdutoPm> listafiltrada = new ArrayList<>();
        String tipo = verificatipo();
        
        double valor =0;
        if(!tipo.isEmpty() && tb_valor.getText().length()>0)   
        {
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
        
        
        for(ProdutoPm p : lista)
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
        ObservableList<ProdutoPm> modelo;
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
        p=null;
        estadoEdicao();
    }

    @FXML
    private void clkBtAlterar(ActionEvent event)
    {
        if (TabelaPromo.getSelectionModel().getSelectedItem() != null)
        {
            this.p = (Promocao) TabelaPromo.getSelectionModel().getSelectedItem();
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
    private void clkBtConfirmar(ActionEvent event) throws SQLException
    {
        int cod;
        Promocao p = new Promocao();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        boolean update=false;
        try
        {
            cod = Integer.parseInt(tb_Codigo.getText());
            update=true;
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
                    if(dt_inicial.getValue()!=null && DataUtil(dt_inicial.getValue().toString()) && (!dt_inicial.getValue().isBefore(LocalDate.now()) || update))
                    {
                        if(dt_final.getValue()!=null && DataUtil(dt_inicial.getValue().toString()) && dt_final.getValue().isAfter(dt_inicial.getValue()))
                        {
                            if(!lista.isEmpty())
                            {
                                p= new Promocao(cod,tb_Nome.getText(),Date.valueOf(dt_inicial.getValue()),Date.valueOf(dt_final.getValue()),
                                        tipo,Double.parseDouble(tb_valor.getText().replace(".", "").replace(",", ".")));
                                if (p.getCodigo()== 0) // novo cadastro
                                {
                                    if (!p.insertPromocao(lista))
                                    {                                   
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                    else
                                    {
                                        a.setContentText("Gravado com Sucesso");
                                        a.showAndWait();
                                        lista.clear();
                                        estadoOriginal();
                                    }
                                }
                                else
                                {       
                                        List<ProdPromo> pp=new ProdPromo().selectPorProduto("ativo=true and promo_cod !="+p.getCodigo());
                                        if(!pp.isEmpty())
                                        {
                                            Alert ab = new Alert(Alert.AlertType.WARNING);
                                            ab.setContentText("Produtos desta promoção estão em outra promoção ativada, deseja ativar nesta e desativar na outra?");
                                            ab.getButtonTypes().clear(); 
                                            ab.getButtonTypes().addAll(new ButtonType("Sim",ButtonBar.ButtonData.YES),new ButtonType("Não",ButtonBar.ButtonData.NO));
                                            Optional<ButtonType> result = ab.showAndWait();
                                            if(result.get()==ButtonType.NO)
                                            {
                                                for(int i=0;i<pp.size();i++)
                                                {
                                                    for(int j=0;j<lista.size();j++)
                                                    {
                                                        if(pp.get(i).getCodigoProduto()==lista.get(j).getCod())
                                                        {
                                                            lista.remove(j); 
                                                            j=lista.size();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    
                                    if (!p.updatePromocao(lista))
                                    {
                                        a.setContentText("Problemas ao Alterar");
                                        a.showAndWait();
                                    }
                                    else
                                    {
                                        
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
                            if(dt_final==null)
                            {
                                a.setContentText("Data Final Não informada!");
                            }
                            else
                            a.setContentText("Data Final incorreta!");
                            a.showAndWait();
                            dt_final.requestFocus();
                            
                        }
                    }
                    else
                    {
                        if(dt_inicial==null)
                        {
                            a.setContentText("Data Inicial Não informada!");
                        }
                        else
                                a.setContentText("Data Inicial Invalida!");
                        a.showAndWait();    
                        dt_inicial.requestFocus();
                    }
                }
                else
                {
                    a.setContentText("Dados de Valor promocional incorreto!");
                    a.showAndWait();    
                    tb_valor.requestFocus();
                }
            }else
            {
                a.setContentText("Selecione o Tipo de Desconto!");
                a.showAndWait();  
                cbb_tipo.requestFocus();
            }
        }
        else
        {
            a.setContentText("Insira o nome da Promoção!");
            a.showAndWait();  
            tb_Nome.requestFocus();
        }
            
        carregaTabelaPromo("");
    }

    @FXML
    private void clkbtcancelar(ActionEvent event)
    {
        if (!pndados.isDisabled() || !tb_Codigo.getText().isEmpty()) // encontra em estado de edição
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
            lista= new ProdPromo().selectProdutos(" promo_cod="+tb_Codigo.getText());
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
                List<Produto> lista = new ArrayList<>();
                for(ProdutoPm p : this.lista)
                {
                   lista.add(new Produto(p.getCod(), p.getCodCategoria(), p.getNome(), p.getPreco(), p.getDescricao(), p.getCodMarca(), p.getCodColecao()));
                }
                ctr.RecebeDados(this,lista,this.p);
                
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                p=null;
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

    private boolean DataUtil(String data)
    {
        String[] datas = data.split("-");
        int ano =Integer.valueOf(datas[0]);
        int mes = Integer.valueOf(datas[1]);
        int dia = Integer.valueOf(datas[2]);
        if(dia>31 || dia<1)
            return false;
        if(mes<0 || mes>12)
            return false;
        if(ano<1000)
            return false;
        
        return true;
    }
}
