package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class AdvancedStartController implements Controller {
    @FXML
    private GridPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.advanced-start");
    }
}
