/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Mask.MaskFieldUtil;
import Model.Compra;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLGerarParcelasController implements Initializable
{

    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btn_Confirmar;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private AnchorPane pndados;
    @FXML
    private JFXTextField tb_Codigo;
    @FXML
    private JFXComboBox<?> cbb_Fornecedor;
    @FXML
    private JFXDatePicker dtp_Data;
    @FXML
    private JFXTextField tb_Desconto;
    @FXML
    private JFXTextField tb_ValorTotal;

    @FXML
    private JFXTextField tb_Quantidade;
    @FXML
    private JFXTextField tb_valorParcela;

    private static Compra cmp;
    @FXML
    private JFXButton btn_Gerar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        estadoOriginal();
        MaskFieldUtil.monetaryField(tb_ValorTotal);
        MaskFieldUtil.monetaryField(tb_Desconto);
        MaskFieldUtil.monetaryField(tb_valorParcela);
        preencherDados();
    }

    private void estadoOriginal()
    {
        tb_Codigo.setDisable(true);
        tb_Desconto.setDisable(true);
        tb_ValorTotal.setDisable(true);
        cbb_Fornecedor.setDisable(true);
        dtp_Data.setDisable(true);
    }

    public void preencherDados()
    {
        cmp = (Compra) FXMLEfetuarCompraController.getCompra();
        if (cmp != null)
        {
            tb_Codigo.setText("" + cmp.getCodCompra());
            tb_Desconto.setText(double2string(cmp.getDesconto()));
            tb_ValorTotal.setText(double2string(cmp.getValorTotal()));
            dtp_Data.setValue(cmp.getDataCompra());
            cbb_Fornecedor.getSelectionModel().select(0);
            cbb_Fornecedor.getSelectionModel().select(cmp.getCodForn().getCod());
        }

    }

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
        
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
        cmp = null;
        ((Button) event.getSource()).getScene().getWindow().hide();
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
    private void clkBtGerar(ActionEvent event)
    {
        if (tb_Quantidade.getText().length() > 0)
        {
            tb_valorParcela.setText(double2string(myParseDouble(tb_ValorTotal.getText()) / myParseDouble(tb_Quantidade.getText())));
        }
    }
}
