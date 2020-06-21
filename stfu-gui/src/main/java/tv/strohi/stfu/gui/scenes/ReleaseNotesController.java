package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class ReleaseNotesController implements Controller {
    @FXML
    private GridPane mainPane;

    @FXML
    private WebView releaseNotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.releasenotes");

        releaseNotes.getEngine().setJavaScriptEnabled(true);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        URL url = getClass().getResource("/releasenotes/releasenotes_" + App.getLocale().getLanguage() + ".html");
        releaseNotes.getEngine().load(url.toString());
    }
}
