package tv.strohi.stfu.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.i18n.I18N;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public I18N i18n = new I18N();

    @FXML
    public GridPane mainPane;

    @FXML
    public ComboBox<String> speedComboBox;

    @FXML
    public ComboBox<String> finishedActionComboBox;

    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(),"bundles.scenes.main");

        LocalizationBinder.addComboboxListener(speedComboBox, "bundles.scenes.main", new String[] {"tab.queue.queue.limit.kbyte", "tab.queue.queue.limit.mbyte", "tab.queue.queue.limit.gbyte", "tab.queue.queue.limit.tbyte"});
        LocalizationBinder.addComboboxListener(finishedActionComboBox, "bundles.scenes.main", new String[] {"tab.queue.queue.afterupload.nothing", "tab.queue.queue.afterupload.close", "tab.queue.queue.afterupload.shutdown"});
    }

    public void changeLanguage(ActionEvent actionEvent) {
        Locale locale = App.getLocale();
        if (locale.getLanguage().equalsIgnoreCase("de")) {
            App.setLocale("en");
        } else {
            App.setLocale("de");
        }
    }
}
