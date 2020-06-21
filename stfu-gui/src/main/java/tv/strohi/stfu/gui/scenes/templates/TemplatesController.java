package tv.strohi.stfu.gui.scenes.templates;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.i18n.I18N;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;
import tv.strohi.stfu.gui.scenes.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemplatesController implements Controller {
    @FXML
    private SplitPane mainPane;

    private AddPlaceholderVideosController addPlaceholderVideosController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getItems().toArray(), "bundles.scenes.templates.templates");

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        mainPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.8);
        mainPane.setPrefWidth(primaryScreenBounds.getWidth() * 0.8);
    }

    public void addPlaceholderFilenames(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(App.loadFXML("scenes/templates/add-placeholder-videos", null));

        Stage stage = new Stage();
        stage.setTitle(I18N.get("bundles.scenes.templates.templates", "subwindows.addplaceholdervideos"));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
