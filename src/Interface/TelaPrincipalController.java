/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.Funcionario;
import Model.Parametros;
import Model.ProdPromo;
import Model.Promocao;
import Model.Usuario;
import Persistencia.ParametrizacaoBD;
import Persistencia.UsuarioBD;
import Util.Banco;
import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private Menu mnUsu;
    @FXML
    private MenuItem miFun;
    private ImageView imgvFoto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        spnprincipal = pnprincipal;
        pnprincipal.setStyle("-fx-background-image: url('icons/logo.png')");
        
        topo.setDisable(true);
        Alert a = new Alert(Alert.AlertType.WARNING);
        
        if(new UsuarioBD().get("").isEmpty())
        {
            
            a.setContentText("Nenhum Usuario cadastrado, Por favor cadastre um");
            a.showAndWait();
            clkCadFuncionario(null);    
        }
        else
        {
            clkLogin(null);
        }
       List<Promocao> lista = new Promocao().selectPromocao(" datafinal < '"+LocalDate.now().toString()+"'");
       for(Promocao p : lista)
       {
           new ProdPromo().updateAtivo(p.getCodigo(),false);
       }
    }
   
    protected void setLogin(Usuario usuario)
    {
        topo.setDisable(false);
        this.usuario=usuario;
        pnprincipal.setCenter(null);
        mnUsu.setText(usuario.getLogin());
        if(usuario.getNivel()<3)
            miFun.setVisible(false);
        else
            miFun.setVisible(true);
        ActionEvent event = null;
        Alert a = new Alert(Alert.AlertType.WARNING);
        Parametros p = new Parametros();
        p = p.selectParametro();
        if(p == null)
        {
            a.setContentText("Realize a parametrização!");
            clkParametrizacao(event); 
        }
    }

    @FXML
    private void clkCadCategoria(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroCategoria.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroCategoriaController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroColecao.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroColecaoController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroMarca.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroMarcaController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroProduto.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroProdutoController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroFornecedor.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroFornecedorController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroFuncionario.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroFuncionarioController ctr = loader.getController();
            ctr.RecebeDados(usuario,this);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    public void VoltaLogin()
     {
         clkLogin(null);
     }
    
    @FXML
    private void clkCadCliente(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLCadastroCliente.fxml"));
            Parent root = (Parent) loader.load();

            FXMLCadastroClienteController ctr = loader.getController();
            ctr.RecebeDados(usuario);
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
    private void ClkAlterarUsu(ActionEvent event) {
        
             try
            {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAlteraUsuario.fxml"));
            Parent root = (Parent) loader.load();
            
            FXMLAlteraUsuarioController ctr = loader.getController();
            ctr.RecebeDados(new Funcionario().selectFuncionario(usuario.getCodigo()),usuario,true);
            pnprincipal.setCenter(root); 
            this.usuario = this.usuario.selectUsuario(this.usuario.getCodigo());
         }   catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkDeslogar(ActionEvent event) {
        topo.setDisable(true);
        this.usuario=null;
        mnUsu.setText("USUARIO");
        clkLogin(event);
        
    }

    @FXML
    private void clkParametrizacao(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLParametrizacao.fxml"));
            Parent root = (Parent) loader.load();

            FXMLParametrizacaoController ctr = loader.getController();
            ctr.RecebeDados(usuario);
            pnprincipal.setCenter(root);
            System.out.println("é null");

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkEfetuarPromocao(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarPromocao.fxml"));
            Parent root = (Parent) loader.load();

            FXMLEfetuarPromocaoController ctr = loader.getController();
            ctr.RecebeDados(usuario);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkEfetuarCompra(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarCompra.fxml"));
            Parent root = (Parent) loader.load();

            FXMLEfetuarCompraController ctr = loader.getController();
            ctr.RecebeDados(usuario);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkEfetuarVenda(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarVenda.fxml"));
            Parent root = (Parent) loader.load();

            FXMLEfetuarVendaController ctr = loader.getController();
            ctr.RecebeDados(usuario);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkEfetuarConsignado(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarConsignado.fxml"));
            Parent root = (Parent) loader.load();

            FXMLEfetuarConsignadoController ctr = loader.getController();
            ctr.RecebeDados(usuario);
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    @FXML
    private void clkEfetuarDevolucao(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEfetuarDevolucao.fxml"));
            Parent root = (Parent) loader.load();

            FXMLEfetuarDevolucaoController ctr = loader.getController();
            pnprincipal.setCenter(root);

        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    
}
