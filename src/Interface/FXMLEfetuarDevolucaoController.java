/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Model.Cliente;
import Model.Devolucao;
import Model.ItensVenda;
import Model.ParcelasAPagar;
import Model.Produto;
import Model.TableDevolucao;
import Model.Venda;
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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLEfetuarDevolucaoController implements Initializable {
    @FXML
    private JFXComboBox<Cliente> cbb_cli;
    @FXML
    private JFXButton btn_confirma;
    @FXML
    private JFXButton btn_sair;
    @FXML
    private JFXTextField tb_Pesquisa;
    @FXML
    private JFXComboBox<String> cbb_filtro;
    @FXML
    private JFXButton btn_Pesquisar;
    
    @FXML
    private TableView<TableDevolucao> tabela;
    @FXML
    private JFXButton btn_buscar;
    @FXML
    private TableColumn<TableDevolucao, String> colProdD;
    @FXML
    private TableColumn<TableDevolucao, Double> colPrecoD;
    @FXML
    private TableColumn<TableDevolucao, String> coltamD;
    @FXML
    private TableColumn<TableDevolucao, Integer > colqtdeD;
    @FXML
    private TableView<TableDevolucao> tabelaselec;
    @FXML
    private TableColumn<TableDevolucao, String> colProdS;
    @FXML
    private TableColumn<TableDevolucao, Double> colPrecoS;
    @FXML
    private TableColumn<TableDevolucao, Integer> coltamS;
    @FXML
    private TableColumn<TableDevolucao, Double> colqtdeS;
    @FXML
    private JFXComboBox<Venda> cbb_venda;
   
    private Cliente c = new Cliente();
    private List<TableDevolucao> listaselec = new ArrayList<>();
    @FXML
    private Label txt_total;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        colProdD.setCellValueFactory(new PropertyValueFactory("nome"));
        colPrecoD.setCellValueFactory(new PropertyValueFactory("preco"));
        colqtdeD.setCellValueFactory(new PropertyValueFactory("qtde"));
        coltamD.setCellValueFactory(new PropertyValueFactory("tamanho"));
        
        colProdS.setCellValueFactory(new PropertyValueFactory("nome"));
        colPrecoS.setCellValueFactory(new PropertyValueFactory("preco"));
        colqtdeS.setCellValueFactory(new PropertyValueFactory("qtde"));
        coltamS.setCellValueFactory(new PropertyValueFactory("tamanho"));
        
        
        cbb_cli.setItems(FXCollections.observableArrayList(new Cliente().selectCliente("")));
        List<String> lista = new ArrayList<>();
        lista.add("Nome");
        lista.add("tamanho");
        cbb_filtro.setItems(FXCollections.observableArrayList(lista));
 
    }    

    
    @SuppressWarnings("empty-statement")
     private void carregaTabela(String filtro)
    {
        List<Venda> listavenda = new Venda().selectVendas(" codcliente = "+cbb_cli.getSelectionModel().getSelectedItem().getCod() +" and codvenda = "+cbb_venda.getSelectionModel().getSelectedItem().getCod());   
       
        List<ItensVenda> listaitens = new ArrayList<>();
        for(Venda v : listavenda)
        {
            List<ParcelasAPagar> listaparcela = new ParcelasAPagar().selectParcelas("status='A' and codvenda ="+ v.getCod());
            if(listaparcela.isEmpty())
            {
                listavenda.remove(v);
            }
            else
            {
                if(cbb_filtro.getSelectionModel().getSelectedIndex()==0 && !filtro.isEmpty())
                {
                    List<Produto> prods = new Produto().selectProduto(filtro);
                    for(Produto p : prods)
                        listaitens.addAll(new ItensVenda().selectItens("codvenda ="+v.getCod()+" and codproduto ="+ p.getCod()));
                }
                else
                    if(cbb_filtro.getSelectionModel().getSelectedIndex()==1 && !filtro.isEmpty())
                        listaitens.addAll(new ItensVenda().selectItens(filtro+" and codvenda = "+v.getCod()));
                else
                listaitens.addAll(new ItensVenda().selectItens("codvenda = "+v.getCod()));
            }
                
        }
        List<TableDevolucao> lista = new ArrayList<>();
        for(ItensVenda i :listaitens)
        {
           TableDevolucao td = new TableDevolucao();
           td.pegaProduto(i.getCodProduto());
           td.setTamanho(i.getTamanho().getTamanho());
           td.setCodVenda(i.getCodvenda());
           td.setQtde(i.getQuantidade());
           lista.add(td);
         }
        tabela.setItems(FXCollections.observableArrayList(lista));
        //List<TableDevolucao> lista = new ItensVenda().selectItens("");
        
    }
    
    @FXML
    private void clkDevolucao(ActionEvent event) throws SQLException, IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Escolha o metodo", new ButtonType("Reembolso",ButtonBar.ButtonData.LEFT),
                                                                              new ButtonType("Troca", ButtonBar.ButtonData.LEFT),
                                                                              new ButtonType("Cancelar", ButtonBar.ButtonData.NO));
        Alert b = new Alert(Alert.AlertType.ERROR);
        if(cbb_cli.getSelectionModel().getSelectedIndex()>-1)
        {
            if(cbb_venda.getSelectionModel().getSelectedIndex()>-1)
            {
                if(listaselec.size()>0)
                {
                    Optional<ButtonType> result = a.showAndWait();
                    if(result.get().getText()== "Reembolso")//Reembolso
                    {
                        boolean flag=true;
                        List<ParcelasAPagar> parcelasPagas =new ParcelasAPagar().selectParcelas("status='P' and codvenda = "+ listaselec.get(0).getCodVenda());
                        for(TableDevolucao td : listaselec )
                        {
                           
                            int i=0;
                            while(i<tabela.getItems().size() && flag)
                            {
                                if(td.getCodigo()==tabela.getItems().get(i).getCodigo())
                                {
                                    if(td.getQtde()==tabela.getItems().get(i).getQtde())
                                    {
                                        flag=true;
                                    }
                                    else
                                        flag=false;
                                }
                                i++;
                            }
                        }
                        if(flag)//Devolucao de todos os produtos
                        {
                            //Restaura o estoque
                           for(ParcelasAPagar  p : parcelasPagas)
                                    c.setSaldo(c.getSaldo()+p.getValor());
                           
                           if( new ParcelasAPagar().deleteParcelas(listaselec.get(0).getCodVenda()) && 
                                   new ItensVenda().deleteItens(listaselec.get(0).getCodVenda()) && 
                                   new Venda().deleteVenda(listaselec.get(0).getCodVenda()) &&  c.updateCliente())
                           {
                               b.setAlertType(Alert.AlertType.INFORMATION);
                               b.setContentText("Devolução efetuada com sucesso,o valor das parcelas pagas foram atribuidas para o saldo do cliente,");
                           }
                           else
                           {
                               b.setContentText("Erro ao efetuar a Devolução");
                           }
                            
                        }
                        else// devolução de alguns produtos
                        {
                           
                            flag=new Devolucao().insertList(listaselec);
                            if(flag)
                            {
                                b.setAlertType(Alert.AlertType.INFORMATION);
                                b.setContentText("Devolução efetuada com sucesso");
                                
                                listaselec.clear();
                                carregaTabela("");
                                tabelaselec.refresh();
                            }
                            else
                            {
                                b.setContentText("Erro ao efetuar a Devolução");
                         
                            }
                            
                            
                        }
                        b.showAndWait();
                        //produto volta para o estoque
                    }
                     else
                        if(result.get().getText()== "Troca")//Troca
                        {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarDevolucaoTroca.fxml"));
                                Parent root = (Parent) loader.load();

                                FXMLEfetuarDevolucaoTrocaController ctr = loader.getController();
                                ctr.RecebeDados(listaselec);

                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(scene);
                                stage.showAndWait();
                            }
                            catch(IOException ex)
                            {
                                System.out.println(ex);
                            }
                        }
                }
                else
                {
                    b.setContentText("Nenhum produto foi selecionado para troca");
                    b.showAndWait();
                }
            }
            else
            {
                b.setContentText("Nenhuma Venda foi selecionado para troca");
                b.showAndWait();
            }
        }
        else
        {
            b.setContentText("Nenhum Cliente foi selecionado para troca");
            b.showAndWait();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) {
        spnprincipal.setCenter(null);
    }

    @FXML
    private void clkTxPesquisaPromo(KeyEvent event) {
    }

    @FXML
    private void clkBtPesquisar(ActionEvent event) {
        if(cbb_filtro.getSelectionModel().getSelectedIndex()>-1 && tb_Pesquisa.getText().length()>0)
        {
            String filtro="";
            if(cbb_filtro.getSelectionModel().getSelectedIndex()==0)
            {
                filtro="nome like '%"+tb_Pesquisa.getText()+"%'";
            }
            else
                filtro="tamanho ='"+tb_Pesquisa.getText()+"'";
            carregaTabela(filtro);
        }
    }



    @FXML
    private void clkBuscar(ActionEvent event) {
        if(cbb_cli.getSelectionModel().getSelectedIndex()>-1 && cbb_venda.getSelectionModel().getSelectedIndex()>-1)
        {
            carregaTabela("");
            listaselec.clear();
            AtualizaValorDevolucao();
        }
    }

    @FXML
    private void clkBusca(Event event) {
        if(cbb_cli.getSelectionModel().getSelectedIndex()>-1 && c.getCod()!=cbb_cli.getSelectionModel().getSelectedItem().getCod())
        {
            List<Venda> listav = new Venda().selectVendas(" codcliente="+cbb_cli.getSelectionModel().getSelectedItem().getCod());
            c=cbb_cli.getSelectionModel().getSelectedItem();
            cbb_venda.setItems(FXCollections.observableArrayList(listav));
        }
    }

    @FXML
    private void clkTabelaAdiciona(MouseEvent event) {
        if (event.getClickCount() == 2 && tabela.getSelectionModel().getSelectedIndex() >= 0)
        {
            boolean contem=false;
            for(TableDevolucao td : listaselec)
            {
                if(td.getNome().equals(tabela.getSelectionModel().getSelectedItem().getNome()))
                {
                    contem=true;
                    if(tabela.getSelectionModel().getSelectedItem().getQtde()>td.getQtde())
                        td.setQtde(td.getQtde()+1);
                }
                    
            }
            if(!contem)
            {
                TableDevolucao td = tabela.getSelectionModel().getSelectedItem();
                TableDevolucao tdn = new TableDevolucao(td.getCodigo(),td.getNome(),td.getPreco(),1,td.getTamanho(),td.getCodVenda());
                listaselec.add(tdn);
            }
           tabelaselec.setItems(FXCollections.observableArrayList(listaselec));
           tabelaselec.refresh();
            AtualizaValorDevolucao();
        }
    }

    @FXML
    private void clkTabelaRemove(MouseEvent event) {
        if (event.getClickCount() == 2 && tabelaselec.getSelectionModel().getSelectedIndex() >= 0)
        {
            if(tabelaselec.getSelectionModel().getSelectedItem().getQtde()>1)
            {
                listaselec.get(tabelaselec.getSelectionModel().getSelectedIndex()).setQtde(tabelaselec.getSelectionModel().getSelectedItem().getQtde()-1);
            }
            else
                listaselec.remove(tabelaselec.getSelectionModel().getSelectedIndex());
            
            tabelaselec.setItems(FXCollections.observableArrayList(listaselec));
            tabelaselec.refresh();
            AtualizaValorDevolucao();
        }
    }
    public static String double2string(double v)
    {
        return String.format("%,.2f", v);
    }
    
    private void AtualizaValorDevolucao()
    {
        double valor=0;
        for(TableDevolucao td : listaselec)
        {
            valor+= td.getPreco()*td.getQtde();
        }
        
        txt_total.setText("Total da Devolução: "+double2string(valor));
    }
        
}
