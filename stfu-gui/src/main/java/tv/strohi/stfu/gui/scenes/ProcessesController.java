package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class ProcessesController  implements Initializable {
    @FXML
    private GridPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.processes");
    }
}
