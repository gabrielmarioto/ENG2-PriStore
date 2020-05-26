/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

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
    private Button btn_NovoProduto;
    @FXML
    private Button btn_NovoTamanho;
    @FXML
    private JFXTextField tb_Desconto;
    @FXML
    private JFXTextField tb_ValorTotal;
    @FXML
    private JFXTextField tb_Desconto1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void clkBtConfirmar(ActionEvent event)
    {
    }

    @FXML
    private void clkBtCancelar(ActionEvent event)
    {
    }

    @FXML
    private void clkBtNovoP(ActionEvent event)
    {
    }

    @FXML
    private void clkBtNovoT(ActionEvent event)
    {
    }
    
}
