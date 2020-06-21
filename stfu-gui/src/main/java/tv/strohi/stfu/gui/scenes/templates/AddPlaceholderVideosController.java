package tv.strohi.stfu.gui.scenes.templates;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;
import tv.strohi.stfu.gui.scenes.Controller;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPlaceholderVideosController implements Controller {
    @FXML
    private GridPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.templates.add-placeholder-videos");
    }
}
