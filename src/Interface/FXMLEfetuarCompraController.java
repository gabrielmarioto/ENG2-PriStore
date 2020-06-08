/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Compra;
import Model.Fornecedor;
import Model.ItensCompra;
import Model.Produto;
import Model.Tamanho;
import Model.Usuario;
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
import javafx.scene.control.ComboBox;
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
 * @author Gabriel
 */
public class FXMLEfetuarCompraController implements Initializable
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
    private JFXComboBox<Fornecedor> cbb_Fornecedor;
    @FXML
    private JFXDatePicker dtp_Data;
    @FXML
    private JFXComboBox<Produto> cbb_Produto;
    @FXML
    private Button btn_NovoProduto;
    @FXML
    private JFXComboBox<Tamanho> cbb_Tamanho;
    @FXML
    private Button btn_NovoTamanho;
    @FXML
    private JFXTextField tb_Quantidade;
    @FXML
    private JFXTextField tb_Preco;
    @FXML
    private VBox pnpesquisa;
    @FXML
    private TableView<ItensCompra> tabela;
    @FXML
    private TableColumn<ItensCompra, Integer> colProduto;
    @FXML
    private TableColumn<ItensCompra, String> colTamanho;
    @FXML
    private TableColumn<ItensCompra, Integer> colQuantidade;
    @FXML
    private TableColumn<ItensCompra, Double> colPreco;
    @FXML
    private JFXButton btn_AdicionarItem;
    @FXML
    private JFXButton btn_RemoverItem;
    @FXML
    private JFXButton btn_ProcurarCompra;
    @FXML
    private JFXButton btn_GerarParcelas;

    private Usuario u;
    @FXML
    private JFXTextField tb_Desconto;
    @FXML
    private JFXTextField tb_ValorTotal;

    private List<ItensCompra> aux = new ArrayList();
    private List<ItensCompra> del = new ArrayList();
    private ItensCompra it = null;
    private Compra c;
    private ObservableList<ItensCompra> modelo;
    private double valor;
    private double desconto;
    
    private static Object compra;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colProduto.setCellValueFactory(new PropertyValueFactory("codProduto"));
        colTamanho.setCellValueFactory(new PropertyValueFactory("tamanho"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("qntd"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valorProduto"));
        MaskFieldUtil.monetaryField(tb_Preco);
        tb_ValorTotal.setDisable(true);
        tb_ValorTotal.setText(double2string(0.0));
        tb_Desconto.setText(double2string(0.0));
        MaskFieldUtil.monetaryField(tb_Desconto);
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

        btn_AdicionarItem.setDisable(true);
        btn_RemoverItem.setDisable(true);
        btn_GerarParcelas.setDisable(true);
        btn_ProcurarCompra.setDisable(false);
        tabela.getItems().clear();
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
        cbb_Fornecedor.requestFocus();
        btn_AdicionarItem.setDisable(false);
        btn_RemoverItem.setDisable(false);
        dtp_Data.setDisable(false);
        btn_ProcurarCompra.setDisable(true);
    }

    public static Object getCompra()
    {
        return compra;
    }
    
    public void recebeInfo()
    {
        Compra c = (Compra) FXMLProcurarCompraController.getCompra();
        if (c != null)
        {
            ItensCompraBD it = new ItensCompraBD();
            aux = it.get("codCompra = " + c.getCodCompra());
            tb_Codigo.setText("" + c.getCodCompra());
            dtp_Data.setValue(c.getDataCompra());
            tb_Desconto.setText(double2string(c.getDesconto()));
            desconto = myParseDouble(tb_Desconto.getText());
            tb_ValorTotal.setText(double2string(c.getValorTotal()));
            valor = myParseDouble(tb_ValorTotal.getText()) / 10;
            cbb_Fornecedor.getSelectionModel().select(0);// gambis
            cbb_Fornecedor.getSelectionModel().select(c.getCodForn().getCod());
            ObservableList<ItensCompra> modelo;
            modelo = FXCollections.observableArrayList(aux);
            tabela.setItems(modelo);
            tabela.refresh();
            btn_ProcurarCompra.setDisable(true);
            btn_GerarParcelas.setDisable(false);
            estadoEdicao();
        } else
        {
            estadoOriginal();
        }
    }

    private void carregaTabela(String filtro)
    {
        cbb_Fornecedor.setItems(FXCollections.observableArrayList(new Fornecedor().selectFornecedor("")));
        cbb_Produto.setItems(FXCollections.observableArrayList(new Produto().selectProduto("")));
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
        tb_Codigo.setDisable(true);
        tabela.setDisable(false);
        tb_ValorTotal.setText(double2string(0.0));
        tb_Desconto.setText(double2string(0.0));
        btn_ProcurarCompra.setDisable(true);
    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        int cod;
        LocalDate hoje = LocalDate.now();
        Compra c = new Compra();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        try
        {
            cod = Integer.parseInt(tb_Codigo.getText());
        } catch (Exception e)
        {
            cod = 0;
        }
        if (cbb_Fornecedor.getSelectionModel().getSelectedIndex() != -1)
        {
            if (dtp_Data.getValue().isBefore(hoje) || dtp_Data.getValue().isEqual(hoje))
            {
                if (tb_ValorTotal.getText().length() > 0)
                {
                    if (myParseDouble(tb_Desconto.getText()) > 0.0 && desconto == 0.0)
                    {
                        double valor = myParseDouble(tb_ValorTotal.getText());
                        double desconto = myParseDouble(tb_Desconto.getText());
                        desconto /= 100;
                        desconto = valor * desconto;
                        tb_ValorTotal.setText(double2string(valor - desconto));
                    }
                    c = new Compra(cod, cbb_Fornecedor.getValue(), myParseDouble(tb_ValorTotal.getText()), myParseDouble(tb_Desconto.getText()), dtp_Data.getValue());
                    if (cod == 0)
                    {
                        if (c.insertCompra())
                        {
                            ItensCompra item;
                            Tamanho t;
                            Tamanho temp = new Tamanho();
                            c.setCodCompra(c.getMaxPK());
                            for (int i = 0; i < aux.size(); i++)
                            {
                                item = aux.get(i);
                                item.setCodCompra(c);
                                item.insertItensCompra();
                                temp = temp.select(item.getTamanho().getTamanho(), item.getCodProduto().getCod());
                                t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd() + temp.getQtde());
                                t.updateTamanho();
                            }
                        } else
                        {
                            a.setContentText("Problemas ao Gravar");
                        }
                    } else
                    {                        
                        if (c.updateCompra())
                        {
                            ItensCompra item;
                            Tamanho t;
                            Tamanho temp = new Tamanho();
                            for (int i = 0; i < aux.size(); i++)
                            {
                                item = aux.get(i);
                                item.setCodCompra(c);
                                if (!item.insertItensCompra())
                                {
                                    item.updateItensCompra();
                                }
                                temp = temp.select(item.getTamanho().getTamanho(), item.getCodProduto().getCod());
                                if (temp.getQtde() > item.getQntd())
                                {
                                    t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd() + temp.getQtde());
                                } else
                                {
                                    t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd() - temp.getQtde());
                                }
                                if (temp.getQtde() == item.getQntd())
                                {
                                    t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd());
                                }
                                t.updateTamanho();
                            }
                            for (int i = 0; i < del.size(); i++)
                            {
                                item = del.get(i);
                                item.setCodCompra(c);
                                item.deleteItensCompra();
                                temp = temp.select(item.getTamanho().getTamanho(), item.getCodProduto().getCod());
                                t = new Tamanho(item.getCodProduto(), item.getTamanho().getTamanho(), item.getQntd() - temp.getQtde());
                                t.updateTamanho();
                            }
                        } else
                        {
                            a.setContentText("Problemas ao Alterar");
                            a.showAndWait();
                        }
                    }
                    estadoOriginal();
                } else
                {
                    a.setContentText("Faça uma compra!");
                }
            } else
            {
                a.setContentText("Informe a data!");
            }

        } else
        {
            a.setContentText("Informe o fornecedor!");
            a.showAndWait();
        }
        carregaTabela("");
        this.desconto = 0.0;
        this.aux.clear();
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
    private void clkBtNovoP(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroProduto.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Produtos");
            stage.showAndWait();
            FXMLCadastroProdutoController ctr = loader.getController();
            ctr.RecebeDados(u);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
        carregaTabela("");
    }

    @FXML
    private void clkBtNovoT(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroTamanho.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Tamanhos");
            stage.showAndWait();
            FXMLCadastroTamanhoController ctr = loader.getController();
            ctr.RecebeDados(u);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
        carregaTabela("");
    }

    @FXML
    private void clkBtAdicionarItem(ActionEvent event)
    {
        if (cbb_Produto.getSelectionModel().getSelectedIndex() != -1)
        {
            if (tb_Preco.getText().length() > 0)
            {
                if (tb_Quantidade.getText().length() > 0)
                {
                    it = new ItensCompra(cbb_Produto.getValue(), cbb_Tamanho.getValue(), myParseDouble(tb_Preco.getText()), Integer.parseInt(tb_Quantidade.getText()));
                    int p = -1;
                    p = aux.indexOf(it);
                    valor = myParseDouble(tb_ValorTotal.getText());
                    int quant = Integer.parseInt(tb_Quantidade.getText());
                    valor += myParseDouble(tb_Preco.getText()) * quant;
                    tb_ValorTotal.setText(double2string(valor));
                    if (p >= 0)
                    {
                        ItensCompra novo = aux.get(p);
                        it.setQntd(it.getQntd() + novo.getQntd());
                        if (novo.getValorProduto() < it.getValorProduto())
                        {
                            it.setValorProduto(myParseDouble(double2string(myParseDouble(tb_ValorTotal.getText()) / it.getQntd())));
                        }
                        aux.remove(p);
                    }
                    aux.add(it);

                    modelo = FXCollections.observableArrayList(aux);
                    tabela.setItems(modelo);
                    tabela.refresh();

                    tb_Preco.setText("");
                    tb_Quantidade.setText("");
                }
            }
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

    @FXML
    private void clkBtRemoverItem(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            it = new ItensCompra(tabela.getSelectionModel().getSelectedItem().getCodProduto(), tabela.getSelectionModel().getSelectedItem().getCodCompra(), tabela.getSelectionModel().getSelectedItem().getTamanho(), myParseDouble(tabela.getSelectionModel().getSelectedItem().getValorProduto() + ""), tabela.getSelectionModel().getSelectedItem().getQntd());
            del.add(it);
            aux.remove(it);
            modelo = FXCollections.observableArrayList(aux);
            tabela.setItems(modelo);
            valor = myParseDouble(tb_ValorTotal.getText());

            valor -= (it.getValorProduto() / 10) * it.getQntd();

            if (valor > 0)
            {
                tb_ValorTotal.setText(double2string(valor));
            } else
            {
                valor = 0.0;
                tb_ValorTotal.setText(double2string(valor));
            }

            tb_Preco.setText("");
            tb_Quantidade.setText("");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProcurarCompra.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Procurar Compras Realizadas");
            stage.showAndWait();
            FXMLProcurarCompraController ctr = loader.getController();
            ctr.RecebeDados(u);
            recebeInfo();

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkBtGerarParcelas(ActionEvent event)
    {
        try
        {
            compra = new Compra(Integer.parseInt(tb_Codigo.getText()), cbb_Fornecedor.getValue(), myParseDouble(tb_ValorTotal.getText()), myParseDouble(tb_Desconto.getText()), dtp_Data.getValue());
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerarParcelas.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Gerar Parcelas da Compra "+tb_Codigo.getText());            
            stage.showAndWait();            

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

}
