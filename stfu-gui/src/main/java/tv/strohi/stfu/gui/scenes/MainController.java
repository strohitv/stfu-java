package tv.strohi.stfu.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public GridPane mainPane;

    @FXML
    public ComboBox<String> speedComboBox;

    @FXML
    public ComboBox<String> finishedActionComboBox;

    @FXML
    public ComboBox<String> languageComboBox;

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(),"bundles.scenes.main");

        LocalizationBinder.addComboboxListener(speedComboBox, "bundles.scenes.main", "tab.queue.queue.limit.kbyte", "tab.queue.queue.limit.mbyte", "tab.queue.queue.limit.gbyte", "tab.queue.queue.limit.tbyte");
        LocalizationBinder.addComboboxListener(finishedActionComboBox, "bundles.scenes.main", "tab.queue.queue.afterupload.nothing", "tab.queue.queue.afterupload.close", "tab.queue.queue.afterupload.shutdown");

        speedComboBox.getSelectionModel().select(1);
        finishedActionComboBox.getSelectionModel().select(0);
        languageComboBox.getSelectionModel().select(App.getLocale().getLanguage().equalsIgnoreCase("de") ? 0 : 1);
    }

    public void changeLanguage(ActionEvent actionEvent) {
        App.setLocale(languageComboBox.getSelectionModel().getSelectedIndex() == 1 ? "en" : "de");
    }
}
