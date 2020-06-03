/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Parametros;
import Model.Usuario;
import Persistencia.ParametrizacaoBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class FXMLParametrizacaoController implements Initializable
{

    
    
    private Usuario u;
    @FXML
    private SplitPane painel;
    @FXML
    private JFXButton btn_Alterar;
    @FXML
    private JFXButton btn_Cancelar;
    @FXML
    private AnchorPane pndados;
    @FXML
    private JFXTextField tb_Nome;
    @FXML
    private JFXTextField tb_End;
    @FXML
    private JFXTextField tb_Site;
    @FXML
    private JFXTextField tb_RazaoSocial;
    @FXML
    private JFXTextField tb_Email;
    @FXML
    private JFXTextField tb_Telefone;
    @FXML
    private JFXButton btn_Confirmar;
    @FXML
    private ImageView imgvFoto;
    FileChooser arquivo;
    
    File arq;
    @Override
    
    public void initialize(URL location, ResourceBundle resources) {

        MaskFieldUtil.maxField(tb_Telefone, 40);
        MaskFieldUtil.foneField(tb_Telefone);
        Parametros teste = new Parametros();
        teste = teste.selectParametro();
        if(teste == null)
        {
            btn_Alterar.setText("Inserir");
        }
    }
    
    protected void RecebeDados(Usuario u){
       this.u=u;
    }
    private void estadoOriginal() throws IOException
    {
        pndados.setDisable(true);
        btn_Confirmar.setDisable(true);
        btn_Cancelar.setDisable(false);
        btn_Alterar.setDisable(false);
        pegaParametros();
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
        
        Parametros p = new Parametros();
        p = p.selectParametro();
        if(p != null)
        {
            tb_Email.setText(p.getEmail());
            tb_Nome.setText(p.getNomeF());
            tb_End.setText(p.getEndereco());
            tb_RazaoSocial.setText(p.getRazaoSocial());
            tb_Site.setText(p.getSite());
            tb_Telefone.setText(p.getTelefone());

            ParametrizacaoBD bd = new ParametrizacaoBD();
            InputStream img = bd.getFoto();
            if(img != null)
            {
                BufferedImage bimg = ImageIO.read(img);
                SwingFXUtils.toFXImage(bimg, null);
                imgvFoto.setImage(SwingFXUtils.toFXImage(bimg, null));
            }
        }   
 
    }
    private void pegaParametros()
    {
        Parametros par = new Parametros();
        par = par.selectParametro();
        
        // insere a razão social da empresa
        Stage stage = new Stage();
        stage =(Stage) spnprincipal.getScene().getWindow();
        stage.setTitle(par.getRazaoSocial());
        spnprincipal.setStyle("-fx-background-image: url('icons/logo.png')");
    }
    private void estadoEdicao()
    {
        pndados.setDisable(false);
        btn_Confirmar.setDisable(false);
        btn_Alterar.setDisable(true);
        tb_Nome.requestFocus();
        btn_Alterar.setDisable(true);
    }
    @FXML
    private void clkBtConfirmar(ActionEvent event) throws IOException {
        Parametros p = new Parametros();
        Parametros teste = new Parametros();
        ParametrizacaoBD par = new ParametrizacaoBD();
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if (tb_Nome.getText().length() > 0)
        {
            if (tb_RazaoSocial.getText().length() > 0)
            {
                if (tb_Telefone.getText().length() > 0)
                {
                    if (tb_End.getText().length() > 0)
                    {
                        if (tb_Email.getText().length() > 0 && tb_Email.getText().contains("@"))
                        {
                            teste = teste.selectParametro();
                            p = new Parametros(tb_Nome.getText(), tb_RazaoSocial.getText(), tb_End.getText(), tb_Site.getText(), tb_Email.getText(), tb_Telefone.getText());
                            if (tb_Site.getText().length() == 0)
                                p.setSite("-");
                            if(teste == null)
                            {

                                if (!p.insertParametros())
                                {
                                    a.setContentText("Problemas ao Gravar");
                                    a.showAndWait();
                                }
                                else
                                {
                                    a.setContentText("Parametros Gravados Com Sucesso!");
                                    gravaFoto();
                                    a.showAndWait();
                                    btn_Alterar.setText("Inserir");
                                    estadoOriginal();
                                }    
                            }else if (!p.updateParametros())
                            {
                                a.setContentText("Problemas ao Alterar");
                                a.showAndWait();
                            } else
                            {
                                gravaFoto();
                                a.setContentText("Alterado com Sucesso");
                                a.showAndWait();
                                estadoOriginal();
                            }
                            
                        }
                        else
                        {
                             a.setContentText("Informe o Email!");
                            a.showAndWait();
                        }
                    } else
                    {
                        a.setContentText("Informe o Endereço!");
                        a.showAndWait();
                    }
                } else
                {
                    a.setContentText("Informe o Telefone!");
                    a.showAndWait();
                }
            } else
            {
                a.setContentText("Informe a Razaão Social");
                a.showAndWait();
            }
        } else
        {
            a.setContentText("Informe o Nome Fantasia!");
            a.showAndWait();
        }
    }

    private void gravaFoto() throws IOException
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        ParametrizacaoBD par = new ParametrizacaoBD();
        if(imgvFoto.getImage() != null)
        {                 
            BufferedImage bimg = SwingFXUtils.fromFXImage(imgvFoto.getImage(), null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageInByte;
            ImageIO.write(bimg, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            InputStream in = new ByteArrayInputStream(imageInByte);
            if(par.gravarFoto(in, baos.toByteArray().length))
            {
                a.setContentText("Foto Alterada!");
                File outputFile = new File("C:\\Users\\BRUNO\\Desktop\\PriStore\\src\\icons\\logo.png");
                ImageIO.write(SwingFXUtils.fromFXImage(imgvFoto.getImage(),null),
                                "png", outputFile);
            }
            else
            {
                a.setContentText("Problemas ao Alterar Foto!");
            }   
        }
    }
    @FXML
    private void clkBtCancelar(ActionEvent event) throws IOException {
        if (!pndados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else
        {
            spnprincipal.setCenter(null);
        }
    }

    @FXML
    private void clkBtAlterar(ActionEvent event) {

        estadoEdicao();
       
    }

    @FXML
    private void btnLocalizar(ActionEvent event) {
        arquivo = new FileChooser();
        arq = arquivo.showOpenDialog(null);
        imgvFoto.setImage(new Image(arq.toURI().toString()));
    }

}
