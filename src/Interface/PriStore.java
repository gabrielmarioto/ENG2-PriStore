/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Util.Banco;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class PriStore extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setMaximized(true);
        stage.setTitle("SGPS - PriStore");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/camiseta.png"))); // SETAR ICONE NA JANELA
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        if (!Banco.conectar())
        {
            JOptionPane.showMessageDialog(null, "Erro: " + Banco.getCon().getMensagemErro());
            if (JOptionPane.showConfirmDialog(null, "Deseja criar uma base de dados?") == JOptionPane.YES_OPTION)
            {
                if (!Banco.criarBD("pristoredb"))
                {
                    JOptionPane.showMessageDialog(null, "Erro ao criar banco: " + Banco.getCon().getMensagemErro());
                    System.exit(-1);
                } else
                {
                    Banco.realizaRestaure("cdutil\\restore.bat");
                    if (!Banco.conectar())
                    {
                        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco");
                        System.exit(-1);
                    }
                }
            } else
            {
                System.exit(-1);
            }
        }
        launch(args);
    }
}
