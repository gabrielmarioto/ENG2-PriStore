/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Cliente;
import Model.Compra;
import Model.Consignado;
import Model.Fornecedor;
import Model.Funcionario;
import Model.ItensCompra;
import Model.ItensConsignado;
import Model.ItensVenda;
import Model.ParcelasAPagar;
import Model.Produto;
import Model.Tamanho;
import Model.Usuario;
import Model.Venda;
import Persistencia.ConsignadoBD;
import Persistencia.ItensCompraBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bruno
 */
public class FXMLEfetuarVendaController implements Initializable
{

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btn_Novo;
    @FXML
    private JFXButton btn_Confirmar;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private AnchorPane pndados;
    @FXML
    private JFXTextField tb_Codigo;
    @FXML
    private JFXDatePicker dtp_Data;
    @FXML
    private JFXComboBox<Produto> cbb_Produto;
    @FXML
    private JFXComboBox<Tamanho> cbb_Tamanho;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<ItensVenda> tabela;
    @FXML
    private TableColumn<ItensVenda, Integer> colProduto;
    @FXML
    private TableColumn<ItensVenda, String> colTamanho;
    @FXML
    private TableColumn<ItensVenda, Double> colPreco;
    @FXML
    private JFXButton btn_AdicionarItem;
    @FXML
    private JFXButton btn_RemoverItem;    
    @FXML
    private JFXTextField tb_ValorTotal;

    private List<ItensVenda> aux = new ArrayList();
    private List<ItensVenda> del = new ArrayList();
    private List<ParcelasAPagar> Lparcelas = new ArrayList();
    
    private ItensVenda it = null;
    private Venda v;
    private Usuario u;
    private ObservableList<ItensVenda> modelo;
    private ObservableList<ParcelasAPagar> modeloParcela;
    private double valor;
    private double desconto;
    private static Object compra;
    
    @FXML
    private JFXComboBox<Cliente> cbb_Cliente;
    @FXML
    private JFXButton btn_apagar;
    @FXML
    private JFXButton btn_ProcurarVenda;
    @FXML
    private TableView<ParcelasAPagar> tabelaParcelas;
    @FXML
    private TableColumn<ParcelasAPagar, Double> colValor;
    @FXML
    private JFXTextField tb_valor;
    @FXML
    private CheckBox cb_avista;
    @FXML
    private JFXButton btn_adicionarParcela;
    @FXML
    private JFXButton btn_removerParcela;
    @FXML
    private TableColumn<ItensVenda, Integer> colQuantidade;
    @FXML
    private JFXTextField tb_Quantidade;
    @FXML
    private Label tx_totalParcelas;
    @FXML
    private TableColumn<ItensVenda, Double> colTotal;
    @FXML
    private TableColumn<ItensVenda, LocalDate> colVencimento;
    @FXML
    private JFXDatePicker dtp_DataParcela;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colProduto.setCellValueFactory(new PropertyValueFactory("codProduto"));
        colTamanho.setCellValueFactory(new PropertyValueFactory("tamanho"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valorProduto"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colTotal.setCellValueFactory(new PropertyValueFactory("valorTotal"));
        
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colVencimento.setCellValueFactory(new PropertyValueFactory("vencimento"));
        MaskFieldUtil.numericField(tb_Quantidade);
        tb_ValorTotal.setDisable(true);
        tb_ValorTotal.setText(double2string(0.0));
        
        estadoOriginal();
    }

    public void RecebeDados(Usuario u)
    {
        this.u = u;
    }

    private void estadoOriginal()
    {
        pnpesquisa.setDisable(false);
        pndados.setDisable(true);
        btn_Confirmar.setDisable(true);
        btn_Cancelar.setDisable(false);
        btn_Novo.setDisable(false);
        btn_apagar.setDisable(true);
        btn_AdicionarItem.setDisable(true);
        btn_RemoverItem.setDisable(true);
        Lparcelas.clear();
        //tabela.getItems().clear();
        if(modelo != null)
        {
                modelo.clear();
                aux.clear();
        }
        dtp_Data.setDisable(true);

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
        pnpesquisa.setDisable(false);
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_AdicionarItem.setDisable(false);
        btn_RemoverItem.setDisable(false);
        dtp_Data.setDisable(false);
        btn_apagar.setDisable(false);
    }

    public static Object getCompra()
    {
        return compra;
    }
    
    public void recebeInfo()
    {
        Venda v = (Venda) FXMLProcurarVendaController.getVenda();
        //ItensConsignadoBD bd = new ConsignadoBD();
        
        ItensVenda itens = new ItensVenda();
        ParcelasAPagar parcelas = new ParcelasAPagar();
        if (v != null)
        {            
            aux = itens.selectItens("codVenda = "+v.getCod());
            Lparcelas = parcelas.selectParcelas("codVenda = "+v.getCod());

            tb_Codigo.setText("" + v.getCod());
            dtp_Data.setValue(v.getData());
            cbb_Cliente.getSelectionModel().select(0);// gambis
            cbb_Cliente.getSelectionModel().select(v.getCliente().getCod());
            cbb_Produto.getSelectionModel().select(-1);// gambis
            cbb_Tamanho.getSelectionModel().select(-1);// gambis

            btn_apagar.setDisable(false);
            
            modelo = FXCollections.observableArrayList(aux);
            modeloParcela = FXCollections.observableArrayList(Lparcelas);
            
            tabela.setItems(modelo);
            tabelaParcelas.setItems(modeloParcela);
            tabela.refresh();
            tabelaParcelas.refresh();
            
            tb_ValorTotal.setText(somaParcela().toString());
            atualizaSaldo();
            estadoEdicao();
            
        } else
        {
            estadoOriginal();
        }
    }

    private void carregaTabela(String filtro)
    {
        cbb_Produto.setItems(FXCollections.observableArrayList(new Produto().selectProduto("")));
        cbb_Cliente.setItems(FXCollections.observableArrayList(new Cliente().selectClienteSemConsignadoAberto("")));
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
        tb_Codigo.setDisable(true);
        tabela.setDisable(false);
        tb_ValorTotal.setText(double2string(0.0));
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        LocalDate dataAtual = LocalDate.now();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Venda v;
        ParcelasAPagar p =  new ParcelasAPagar();
        ItensVenda i = new ItensVenda();
        String tipoPgto;
        
        try
        {
            cod = Integer.parseInt(tb_Codigo.getText());
        } catch (Exception e)
        {
            cod = 0;
        }
        if(dtp_Data.getValue() != null)
        {
            if (dtp_Data.getValue().isBefore(dataAtual) || dtp_Data.getValue().isEqual(dataAtual))
            {
                if (cbb_Cliente.getSelectionModel().getSelectedIndex() != -1)
                {
                    if (!tabela.getItems().isEmpty())
                    {
                        if(cb_avista.isSelected() || tb_ValorTotal.getText().compareTo(tx_totalParcelas.getText()) == 0)
                        {
                            if(cb_avista.isSelected())
                            {
                                tipoPgto = "V";
                                Lparcelas.clear();
                                p = new ParcelasAPagar(Double.parseDouble(tb_ValorTotal.getText()),dtp_DataParcela.getValue());
                                Lparcelas.add(p);
                            }
                            else
                                tipoPgto = "P";

                            v = new Venda(cod,
                                    dtp_Data.getValue(),
                                    cbb_Cliente.getValue(),
                                    tipoPgto);

                            Lparcelas.forEach((k) -> {
                                k.setStatus("A");
                                k.setVenda(v);
                            });
                            if (cod == 0)
                            {
                                if (v.insertVenda())
                                {   
                                    int max = v.getMaxPK();
                                    if(!p.insereParcelas(Lparcelas, max))
                                    {
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                    if(!i.insereItens(aux, max))
                                    {
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                }else{
                                    a.setContentText("Problemas ao Gravar");
                                    a.showAndWait();
                                }
                            } else
                            {       
                                if (v.updateVenda())
                                {
                                    p.deleteParcelas(cod);
                                    i.deleteItens(cod);
                                    if(!p.insereParcelas(Lparcelas, cod))
                                    {
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                    if(!i.insereItens(aux, cod))
                                    {
                                        a.setContentText("Problemas ao Gravar");
                                        a.showAndWait();
                                    }
                                }
                            }
                            estadoOriginal();
                            //this.aux.clear();
                        }else
                        {
                            a.setContentText("Parcelas incorretas");
                            a.showAndWait();
                        }
                    } else
                    {
                        a.setContentText("Informe os itens para realizar a venda!");
                        a.showAndWait();
                    }
                } else
                {
                    a.setContentText("Informe o cliente!");
                    a.showAndWait();
                }

            } else
            {
                a.setContentText("Data inválida!");
                a.showAndWait();
            }
        }else
        {
            a.setContentText("Data inválida!");
            a.showAndWait();
        }
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
    private void clkBtAdicionarItem(ActionEvent event)
    {
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1)
        {
            if (cbb_Tamanho.getSelectionModel().getSelectedIndex() != -1)
            {
                it = new ItensVenda(0,
                        cbb_Produto.getValue(), cbb_Tamanho.getValue(),
                        cbb_Produto.getValue().getPreco(), Integer.parseInt(tb_Quantidade.getText()), 
                        cbb_Produto.getValue().getPreco() * Integer.parseInt(tb_Quantidade.getText())
                );
                
                aux.add(it);

                modelo = FXCollections.observableArrayList(aux);
                tabela.setItems(modelo);
                cbb_Produto.getSelectionModel().select(-1);
                cbb_Tamanho.getItems().clear();

                atualizaSaldo();
            }
            else
            {
               a.setContentText("Selecione um tamanho");
               a.show();
            }      
        }else
        {
           a.setContentText("Selecione um produto");
           a.show();
        }
    }

    public static Double myParseDouble(String val)
    {
        if (val.isEmpty())
        {
            return 0.0;
        }
        return Double.parseDouble(val.trim().replace(".", "").replace(",", "."));
    }

    public static String double2string(double v)
    {
        return String.format("%,.2f", v);
    }
    
    private void atualizaSaldo()
    {
        double saldo = 0;
        somaParcela();
        for (ItensVenda i : modelo)
        {
            saldo = saldo + (i.getValorProduto() * i.getQuantidade());
        }
        tb_ValorTotal.setText(saldo+"");
    }
    @FXML
    private void clkBtRemoverItem(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            int index = tabela.getSelectionModel().getSelectedIndex();
            modelo.remove(index);
            aux.remove(index);
            atualizaSaldo();
            tabela.setItems(modelo);
            
            Lparcelas.clear();
            modeloParcela = FXCollections.observableArrayList(Lparcelas);
            tabelaParcelas.setItems(modeloParcela);
        }
    }

    @FXML
    private void clkBtProcurarCompra(ActionEvent event)
    {
        estadoEdicao();
        tb_Codigo.setDisable(true);
        try
        {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProcurarVenda.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Procurar Consignados Realizados");
            stage.showAndWait();
            FXMLProcurarVendaController ctr = loader.getController();
            ctr.RecebeDados(u);
            recebeInfo();

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkColocaTamanho(Event event)
    {
        if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1)
        {
            cbb_Tamanho.setItems(FXCollections.observableArrayList(new Tamanho().selectTamanho("codProduto =" + cbb_Produto.getValue().getCod())));
        }
    }

    @FXML
    private void clkBtApagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        
        if (a.showAndWait().get() == ButtonType.OK)
        {
            int cod;
            try
            {
                cod = Integer.parseInt(tb_Codigo.getText());
            } catch (Exception e)
            {
                cod = 0;
            }
            
            ItensVenda i = new ItensVenda();     
            ParcelasAPagar p = new ParcelasAPagar();
            p.deleteParcelas(cod);
            i.deleteItens(cod);
            
            v = new Venda();
            if (!v.deleteVenda(cod))
            {
                a.setContentText("Erro ao excluir!");
                a.showAndWait();
            }
            estadoOriginal();
        }
    }

    @FXML
    private void onClickaVista(MouseEvent event) {
        if (cb_avista.isSelected())
        {
            btn_adicionarParcela.setDisable(true);
            btn_removerParcela.setDisable(true);
            tb_valor.setDisable(true);
            tabelaParcelas.setDisable(true);
            tx_totalParcelas.setText("");
            tx_totalParcelas.setDisable(true);
        }else
        {
            btn_adicionarParcela.setDisable(false);
            btn_removerParcela.setDisable(false);
            tb_valor.setDisable(false);
            tabelaParcelas.setDisable(false);
            tx_totalParcelas.setText(somaParcela().toString());
            tx_totalParcelas.setDisable(false);
        }
    }
    private Double somaParcela()
    {
        double total = 0;
        for ( ParcelasAPagar p : Lparcelas)
        {
            total = total +p.getValor();
        }
        return total;
    }

    @FXML
    private void clkBtnAdicionarIParcela(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        LocalDate dtAtual = LocalDate.now();
        
        double valor = 0;

        try
        {
            valor = Double.parseDouble(tb_valor.getText());
            if (somaParcela()+Double.parseDouble(tb_valor.getText()) <= Double.parseDouble(tb_ValorTotal.getText()) && valor > 0)
            {
                if (dtp_DataParcela.getValue().isAfter(dtAtual) || dtp_DataParcela.getValue().isEqual(dtAtual))
                {
                    ParcelasAPagar p;
                    p = new ParcelasAPagar(
                            Double.parseDouble(tb_valor.getText())
                            ,dtp_DataParcela.getValue()
                    );

                    Lparcelas.add(p);

                    modeloParcela = FXCollections.observableArrayList(Lparcelas);

                    tabelaParcelas.setItems(modeloParcela);
                    tx_totalParcelas.setText(somaParcela().toString());
                    tb_valor.setText("");
                }else
                {
                   a.setContentText("Data inválida");
                   a.show();
                }
            }else
            {
               a.setContentText("Valor maior que o total");
               a.show();
            }
        } catch (Exception e)
        {
            a.setContentText("Erro no valor da parcela"+ e);
            a.show();
        }
        somaParcela();
    }

    @FXML
    private void clkBtnRemoverIParcela(ActionEvent event) {
        if (tabelaParcelas.getSelectionModel().getSelectedItem() != null)
        {
            int index = tabelaParcelas.getSelectionModel().getSelectedIndex();
            Lparcelas.remove(index);
            modeloParcela = FXCollections.observableArrayList(Lparcelas);
            tabelaParcelas.setItems(modeloParcela);
            tx_totalParcelas.setText(somaParcela().toString());
            tabelaParcelas.setItems(modeloParcela);
        }
    }


}
