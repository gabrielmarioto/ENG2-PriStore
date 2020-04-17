/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Util.Banco;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Gabriel
 */
public class TelaPrincipalController implements Initializable
{
    public static BorderPane spnprincipal = null;
    @FXML
    private BorderPane pnprincipal;   

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        spnprincipal = pnprincipal;
    }    

    @FXML
    private void clkCadCategoria(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroCategoria.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadColecao(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroColecao.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadMarca(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroMarca.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadProduto(ActionEvent event)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroProduto.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadFornecedor(ActionEvent event)
    {
    }

    @FXML
    private void clkBackup(ActionEvent event)
    {
//        Banco.backup("bkp\\copiar.bat");
        Banco.backup();
    }

    @FXML
    private void clkRestauracao(ActionEvent event)
    {
//        Banco.restore("bkp\\restaurar.bat");
        Banco.restore();
    }

    @FXML
    private void clkGoToHome(ActionEvent event)
    {
        spnprincipal.setCenter(null);
    }

    @FXML
    private void clkFechar(ActionEvent event)
    {
        System.exit(0);
    }

    @FXML
    private void clkLink(ActionEvent event)
    {
         try
        {
            Desktop.getDesktop().browse(new URI("http://unoeste.br"));
        } catch (Exception ex)
        {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
