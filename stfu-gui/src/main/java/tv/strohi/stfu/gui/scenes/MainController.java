package tv.strohi.stfu.gui.scenes;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tv.strohi.stfu.gui.App;
import tv.strohi.stfu.gui.ControllerHolder;
import tv.strohi.stfu.gui.i18n.I18N;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Controller {
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
        try {
            ControllerHolder holder = new ControllerHolder();
            Stage stage = loadStage("scenes/update", "subwindow.update", holder, false, false);
            UpdateController controller = (UpdateController) holder.getHolder();
            controller.setOwnStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            // nee passt
        }

        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.main");

        LocalizationBinder.addComboboxListener(speedComboBox, "bundles.scenes.main", "tab.queue.queue.limit.kbyte", "tab.queue.queue.limit.mbyte", "tab.queue.queue.limit.gbyte", "tab.queue.queue.limit.tbyte");
        LocalizationBinder.addComboboxListener(finishedActionComboBox, "bundles.scenes.main", "tab.queue.queue.afterupload.nothing", "tab.queue.queue.afterupload.close", "tab.queue.queue.afterupload.shutdown");

        speedComboBox.getSelectionModel().select(1);
        finishedActionComboBox.getSelectionModel().select(0);
        languageComboBox.getSelectionModel().select(App.getLocale().getLanguage().equalsIgnoreCase("de") ? 0 : 1);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        mainPane.setPrefHeight(primaryScreenBounds.getHeight() * 0.8);
        mainPane.setPrefWidth(primaryScreenBounds.getWidth() * 0.8);
    }

    public void changeLanguage() {
        App.setLocale(languageComboBox.getSelectionModel().getSelectedIndex() == 1 ? "en" : "de");
    }

    private void showModal(String scenePath, String titleResourceKey, boolean maximize) throws IOException {
        Stage stage = loadStage(scenePath, titleResourceKey, null, maximize, true);
        stage.showAndWait();
    }

    private Stage loadStage(String scenePath, String titleResourceKey, ControllerHolder controllerHolder, boolean maximize, boolean showButtons) throws IOException {
        Scene scene = new Scene(App.loadFXML(scenePath, controllerHolder));

        Stage stage = new Stage();
        stage.setTitle(I18N.get("bundles.scenes.main", titleResourceKey));
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMaximized(maximize);

        if (!showButtons) {
            stage.initStyle(StageStyle.UNDECORATED);
        }

        return stage;
    }

    public void openPathsStage() throws IOException {
        showModal("scenes/paths", "subwindow.paths", false);
    }

    public void openAccountsStage() throws IOException {
        showModal("scenes/accounts", "subwindow.accounts", false);
    }

    public void openTemplatesStage() throws IOException {
        showModal("scenes/templates/templates", "subwindow.templates", true);
    }

    public void browseProcesses() throws IOException {
        showModal("scenes/processes", "subwindow.processes", false);
    }

    public void runWithAdvancedOptions() throws IOException {
        showModal("scenes/advanced-start", "subwindow.advanced-start", false);
    }

    public void showReleaseNotes() throws IOException {
        showModal("scenes/releasenotes", "subwindow.releasenotes", false);
    }
}
