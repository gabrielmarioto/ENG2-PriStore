/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Funcionario;
import Model.Usuario;
import Persistencia.FuncionarioBD;
import Persistencia.UsuarioBD;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Gabriel
 */
public class TelaPrincipalController implements Initializable
{
    public static BorderPane spnprincipal = null;
    private Usuario usuario = null;
    @FXML
    private BorderPane pnprincipal;   
    @FXML
    private VBox topo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        spnprincipal = pnprincipal;
//        topo.setDisable(true);
//        if(new UsuarioBD().get("").isEmpty())
//        {
//            Alert a = new Alert(Alert.AlertType.WARNING);
//            a.setContentText("Nenhum Usuario cadastrado, Por favor cadastre um");
//            a.showAndWait();
//            clkCadFuncionario(null);    
//        }
//        else
//            clkLogin(null);

    }

    protected void setLogin(Usuario usuario)
    {
        topo.setDisable(false);
        this.usuario=usuario;
        pnprincipal.setCenter(null);
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
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroFornecedor.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
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
    
    private void clkLogin(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTelaLogin.fxml"));
            Parent root = (Parent) loader.load();

            FXMLTelaLoginController ctr = loader.getController();
            ctr.RecebeDados(this);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadFuncionario(ActionEvent event)
    {
                   
         try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroFuncionario.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkCadCliente(ActionEvent event) {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastroCliente.fxml"));

            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    
}
