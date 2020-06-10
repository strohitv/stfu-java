package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.SplitPane;
import javafx.stage.Screen;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class TemplatesController implements Initializable {
    @FXML
    private SplitPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getItems().toArray(),"bundles.scenes.templates");

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        mainPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.8);
        mainPane.setPrefWidth(primaryScreenBounds.getWidth() * 0.8);
    }
}
