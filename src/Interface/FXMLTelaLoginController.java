/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLTelaLoginController implements Initializable {

    @FXML
    private JFXTextField tb_Nome;
    @FXML
    private JFXTextField tb_senha;
    @FXML
    private Button btn_logar;
    @FXML
    private Button btn_Sair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void RecebeDados(TelaPrincipalController ctr){
        
        //ctr.setLogin(usuario);
    }

    @FXML
    private void clkLogar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
}
