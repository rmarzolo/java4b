/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatapp_v1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author rmarzolo1
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label lblUsersOnline;
    @FXML
    private ScrollPane pnActivity;
    @FXML
    private RadioButton rdBanSoft;
    @FXML
    private ToggleGroup ban_group;
    @FXML
    private RadioButton rdBanPerm;
    @FXML
    private TextField txtIPInput;
    @FXML
    private Button btnBanUser;
    
    ServerApp app;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //initialize
        app = new ServerApp(9000);
        app.InitServer();
        
    }    

    @FXML
    private void BanUserClick(ActionEvent event) {
        
        if(rdBanSoft.isSelected())
        {
            //add soft ban
            
        }
        else
        {
            
        }
          
    }
    
}
