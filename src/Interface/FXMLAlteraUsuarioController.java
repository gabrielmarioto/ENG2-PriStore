/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static Interface.TelaPrincipalController.spnprincipal;
import Mask.MaskFieldUtil;
import Model.Funcionario;
import Model.Usuario;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Arthur
 */
public class FXMLAlteraUsuarioController implements Initializable {

    @FXML
    private JFXTextField tb_Nome;
    @FXML
    private Button btn_logar;
    @FXML
    private Button btn_Sair;
    @FXML
    private JFXPasswordField pf_senha;
    @FXML
    private JFXPasswordField pf_senha2;
    @FXML
    private Label txtFun;
    @FXML
    private JFXComboBox<Integer> cbb_nivel;
    
    private Funcionario f;
    private Usuario u;
    @FXML
    private JFXPasswordField pf_senhaU;
    private boolean flag;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Integer> nivel = new ArrayList<>();
        nivel.add(1);
        nivel.add(2);
        nivel.add(3);
        cbb_nivel.setItems(FXCollections.observableArrayList(nivel));
        
        MaskFieldUtil.maxField(tb_Nome, 20);
        MaskFieldUtil.maxField(pf_senha, 8);
        MaskFieldUtil.maxField(pf_senha2, 8);
    }    
    
    
    
    public void RecebeDados(Funcionario f,Usuario u,boolean flag){
        
       this.f=f;
       this.u=u;
       this.flag=flag;
       txtFun.setText("Funcionario: " + f.getNome());
       
       if(u.getNivel()==3 && u.selectUsuario("usu_nivel=3").size()==1)
       {
           cbb_nivel.setDisable(true);
       }
       cbb_nivel.getSelectionModel().select(u.getNivel()-1);
       tb_Nome.setText(u.getLogin());
       
    }

    @FXML
    private void clkSair(ActionEvent event) {
        if(!flag)
            ((Button)event.getSource()).getScene().getWindow().hide();
        else
            spnprincipal.setCenter(null);
    }

    @FXML
    private void ClkRegistra(ActionEvent event) {
       Alert a = new Alert(Alert.AlertType.ERROR);
        
       if(tb_Nome.getText().length()>0)
       {
           if(pf_senha.getText().length()>0)
           {
                if(pf_senha2.getText().length()>0)
                {
                    if(cbb_nivel.getSelectionModel().getSelectedIndex() != -1)
                    {
                        if(pf_senha.getText().equals(pf_senha.getText()))
                        {
                            Usuario u = new Usuario(f.getCodigo(), tb_Nome.getText(), pf_senha.getText(), cbb_nivel.getValue());
                            if(u.selectUsuario("usu_login= '"+u.getLogin()+"'").isEmpty())
                            {
                                if(pf_senhaU.getText().length()>0)
                                {
                                    if(!u.selectUsuario("usu_nivel=3 and usu_senha='"+pf_senhaU.getText()+"'").isEmpty()
                                            || pf_senhaU.getText().equals(this.u.getSenha()))
                                    {
                                        if(!u.update())
                                        {
                                            a.setContentText("Falha na Atualização");
                                            a.showAndWait();
                                        }
                                        else
                                        {
                                            a.setAlertType(Alert.AlertType.INFORMATION);
                                            a.setContentText("Usuario Alterado com sucesso!");
                                            a.showAndWait();
                                            clkSair(event);  
                                        }   
                                    }
                                    else
                                    {
                                        a.setContentText("Senha antiga ou do Usuario incorreta ou invalida!");
                                        a.showAndWait();
                                        pf_senhaU.requestFocus();
                                    }
                                }
                                else
                                {
                                    a.setContentText("Informe senha antiga ou do Usuario atual!");
                                    a.showAndWait();
                                    pf_senhaU.requestFocus();
                                }
                                        
                                
                            }
                            else
                            {
                                a.setContentText("Nome de Usuario já existente!");
                                a.showAndWait();
                                tb_Nome.requestFocus();
                            }
                            
                            
                        }
                        else
                        {
                            a.setContentText("Senha e Confirmação diferentes!");
                            a.showAndWait();
                            pf_senha.requestFocus();
                        }
                    }
                    else
                    {
                        a.setContentText("Informe o Nivel de Acesso!");
                        a.showAndWait();
                        cbb_nivel.requestFocus();
                    }
                }
                else
                {
                    a.setContentText("Confirmação de senha incorreta!");
                    a.showAndWait();
                    cbb_nivel.requestFocus();
                }
           }
           else
           {
               a.setContentText("Senha Invalida!");
               a.showAndWait();
               pf_senha.requestFocus();
           }
       }
       else
       {
            a.setContentText("Nome de Usuario Invalido!");
            a.showAndWait();
            tb_Nome.requestFocus();
       }
        
    }
}
