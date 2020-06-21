package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Controller {
    @FXML
    private GridPane mainPane;

    private BrowserController browserController = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(),"bundles.scenes.accounts");
    }

    public void openYoutubeLink() {

    }

    public void openTwitterLink() {

    }

    public void addYoutubeAccount() throws IOException {
        Scene scene = new Scene(loadFXML("scenes/browser"));

        Stage stage = new Stage();
        stage.setTitle("Browser");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        browserController.loadAddress("https://accounts.google.com/o/oauth2/auth?client_id=812042275170-db6cf7ujravcq2l7vhu7gb7oodgii3e4.apps.googleusercontent.com&redirect_uri=http://localhost&scope=https://www.googleapis.com/auth/youtube&response_type=code&approval_prompt=force&access_type=offline");

        stage.showAndWait();

        String localhostAddress = browserController.getLocalHostAddress();
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/tv/strohi/stfu/gui/" + fxml + ".fxml"));
        Parent parentControl = fxmlLoader.load();
        browserController = fxmlLoader.getController();
        return parentControl;
    }
}
