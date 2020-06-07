package tv.strohi.stfu.gui.scenes;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {
    @FXML
    private WebView mainPane;

    private String localHostAddress = "";

    public BrowserController() {
    }

    public void loadAddress(String address) {
        mainPane.getEngine().load(address);
    }

    public String getLocalHostAddress() {
        return localHostAddress;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getEngine().setJavaScriptEnabled(true);
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        com.sun.javafx.webkit.WebConsoleListener.setDefaultListener(
                (webView, message, lineNumber, sourceId) -> System.out.println("Console: [" + sourceId + ":" + lineNumber + "] " + message)
        );

        mainPane.getEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
            if (newState == Worker.State.FAILED && mainPane.getEngine().getLocation().startsWith("http://localhost")) {
                localHostAddress = mainPane.getEngine().getLocation();
                ((Stage) mainPane.getScene().getWindow()).close();
            }
        });
    }
}
