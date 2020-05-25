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
import Model.Compra;
import Model.Fornecedor;
import Model.ItensCompra;
import Model.Marca;
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
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    private TableColumn<Produto, String> colProduto;
    @FXML
    private TableColumn<Tamanho, String> colTamanho;
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
    private ItensCompra it = null;
    private Compra c;
    private ObservableList<ItensCompra> modelo;
    private double valor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        colProduto.setCellValueFactory(new PropertyValueFactory("cod"));
        colTamanho.setCellValueFactory(new PropertyValueFactory("tamanho"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory("qtde"));
        colPreco.setCellValueFactory(new PropertyValueFactory("valorProduto"));
        MaskFieldUtil.monetaryField(tb_Preco);
        tb_ValorTotal.setDisable(true);
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
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        btn_Novo.setDisable(false);

        btn_AdicionarItem.setDisable(true);
        btn_RemoverItem.setDisable(true);
        btn_GerarParcelas.setDisable(true);
        btn_ProcurarCompra.setDisable(false);

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
        pnpesquisa.setDisable(true);
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_Apagar.setDisable(true);
        btn_Alterar.setDisable(true);
        cbb_Fornecedor.requestFocus();
    }

    public void recebeInfo(Compra c)
    {
        ItensCompraBD it = new ItensCompraBD();
        List<ItensCompra> aux = new ArrayList();
        aux = it.get("codCompra = " + c.getCodCompra());
        tb_Codigo.setText("" + c.getCodCompra());
        dtp_Data.setValue(c.getDataCompra());
        tb_Desconto.setText("" + c.getDesconto());
        tb_ValorTotal.setText("" + c.getValorTotal());

        cbb_Fornecedor.getSelectionModel().select(0);// gambis
        cbb_Fornecedor.getSelectionModel().select(c.getCodForn().getCod());
        ObservableList<ItensCompra> modelo;
        modelo = FXCollections.observableArrayList(aux);
        tabela.setItems(modelo);

        estadoEdicao();
    }

    private void carregaTabela(String filtro)
    {
        cbb_Fornecedor.setItems(FXCollections.observableArrayList(new Fornecedor().selectFornecedor("")));
        cbb_Produto.setItems(FXCollections.observableArrayList(new Produto().selectProduto("")));
        cbb_Tamanho.setItems(FXCollections.observableArrayList(new Tamanho().selectTamanho("")));
    }

    @FXML
    private void clkBtNovo(ActionEvent event)
    {
        estadoEdicao();
        tb_Codigo.setDisable(true);
    }

    @FXML
    private void clkBtApagar(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão?");
        if (a.showAndWait().get() == ButtonType.OK)
        {
            ItensCompra it = new ItensCompra();
            it = tabela.getSelectionModel().getSelectedItem();
            if (it.deleteItensCompra())
            {
                Compra c = new Compra(Integer.parseInt(tb_Codigo.getText()), cbb_Fornecedor.getValue(), Double.parseDouble(tb_ValorTotal.getText()), Double.parseDouble(tb_Desconto.getText()), dtp_Data.getValue());
                if (!c.deleteCompra())
                {
                    a.setContentText("Erro ao excluir compra!");
                }
            } else
            {
                a.setContentText("Erro ao excluir itens da compra!");
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
            if (dtp_Data.getValue().isBefore(hoje))
            {
                if (tb_ValorTotal.getText().length() > 0)
                {
                    if (Double.parseDouble(tb_Desconto.getText()) > 0)
                    {
                        double valor = Double.parseDouble(tb_ValorTotal.getText());
                        double desconto = Double.parseDouble(tb_Desconto.getText());
                        desconto /= 100;
                        desconto = valor * desconto;
                        tb_ValorTotal.setText("" + (valor - desconto));
                    }
                    c = new Compra(cod, cbb_Fornecedor.getValue(), Double.parseDouble(tb_ValorTotal.getText()), Double.parseDouble(tb_Desconto.getText()), dtp_Data.getValue());
                    if (c.getCodCompra() == 0)
                    {
                        if (c.insertCompra())
                        {
                            ItensCompra item;
                            for (int i = 0; i < aux.size(); i++)
                            {
                                item = aux.get(i);
                                item.insertItensCompra();
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
                            for (int i = 0; i < aux.size(); i++)
                            {
                                item = aux.get(i);
                                item.updateItensCompra();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroProduto.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroProdutoController ctr = loader.getController();
            ctr.RecebeDados(u);
            spnprincipal.setCenter(root);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroTamanho.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroTamanhoController ctr = loader.getController();
            ctr.RecebeDados(u);
            spnprincipal.setCenter(root);

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
                    it = new ItensCompra(cbb_Produto.getValue(), c, cbb_Tamanho.getValue(), Double.parseDouble(tb_Preco.getText()), Integer.parseInt(tb_Quantidade.getText()));
                    aux.add(it);
                    modelo = FXCollections.observableArrayList(aux);
                    tabela.setItems(modelo);

                    valor = Double.parseDouble(tb_ValorTotal.getText());
                    valor += Double.parseDouble(tb_Preco.getText());
                    tb_ValorTotal.setText("" + valor);
                }
            }
        }
    }

    @FXML
    private void clkBtRemoverItem(ActionEvent event)
    {
        if (tabela.getSelectionModel().getSelectedItem() != null)
        {
            it = new ItensCompra(tabela.getSelectionModel().getSelectedItem().getCodProduto(), tabela.getSelectionModel().getSelectedItem().getCodCompra(),tabela.getSelectionModel().getSelectedItem().getTamanho(),tabela.getSelectionModel().getSelectedItem().getValorProduto(), tabela.getSelectionModel().getSelectedItem().getQntd());
            aux.remove(it);
            modelo = FXCollections.observableArrayList(aux);
            tabela.setItems(modelo);

            valor = Double.parseDouble(tb_ValorTotal.getText());
            valor -= it.getValorProduto();
            tb_ValorTotal.setText("" + valor);
        }
    }

    @FXML
    private void clkBtProcurarCompra(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProcurarCompra.fxml"));
            Parent root = (Parent) loader.load();

            FXMLProcurarCompraController ctr = loader.getController();
            ctr.RecebeDados(u);
            spnprincipal.setCenter(root);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGerarParcelas.fxml"));
            Parent root = (Parent) loader.load();

            FXMLGerarParcelasController ctr = loader.getController();
            //ctr.RecebeDados(u);
            spnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

}
