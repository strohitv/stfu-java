package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.ResourceBundle;

public class ProcessesController implements Controller {
    @FXML
    // TODO: Ein Model mit Properties muss erstellt und verwendet werden anstelle von Processhandle
    private TableView<ProcessHandle> processView;

    @FXML
    private GridPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.processes");

        ProcessHandle.allProcesses().forEach(p -> {
            System.out.println(p.pid() + "; " + p.info().user() + "; " + p.info().command() + "; " + p.info().commandLine() + "; " + p.info().arguments() + "; " + p.info().startInstant());
        });
    }
}
