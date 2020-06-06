package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class PathsController implements Initializable {
    @FXML
    private GridPane mainPane;

    @FXML
    private ComboBox<String> sortOrderComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(),"bundles.scenes.paths");

        LocalizationBinder.addComboboxListener(sortOrderComboBox, "bundles.scenes.paths",
                "edit.sortorder.nameasc", "edit.sortorder.namedsc",
                "edit.sortorder.creationdateasc", "edit.sortorder.creationdatedsc",
                "edit.sortorder.changedateasc", "edit.sortorder.changedatedsc",
                "edit.sortorder.sizeasc", "edit.sortorder.sizedsc");

        sortOrderComboBox.getSelectionModel().select(0);
    }
}
