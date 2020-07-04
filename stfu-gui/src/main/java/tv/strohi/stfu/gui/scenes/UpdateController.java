package tv.strohi.stfu.gui.scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tv.strohi.stfu.gui.i18n.I18N;
import tv.strohi.stfu.gui.i18n.LocalizationBinder;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class UpdateController implements Controller {
    private Stage ownStage;
    private boolean execute = true;

    @FXML
    private Label updateLabel;

    @FXML
    private GridPane mainPane;

    private final String[] names = new String[]{"update.searching", "update.download", "update.extract",
            "update.mbox.available", "update.mbox.available.title", "update.mbox.error", "update.mbox.error.title"};

    public void setOwnStage(Stage stage) {
        ownStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalizationBinder.addListeners(mainPane.getChildren().toArray(), "bundles.scenes.update");

        final Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (execute) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 8);
                    if (randomNum <= 2) {
                        updateLabel.textProperty().bind(I18N.createStringBinding("bundles.scenes.update", names[randomNum]));
                    } else if (randomNum <= 4) {
                        execute = false;
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle(I18N.get("bundles.scenes.update", names[4]).replace("&#13;", System.lineSeparator()));
                            alert.setHeaderText(I18N.get("bundles.scenes.update", names[4]).replace("&#13;", System.lineSeparator()));
                            alert.setContentText(I18N.get("bundles.scenes.update", names[3]).replace("&#13;", System.lineSeparator()));

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                ownStage.close();
                            }
                            execute = true;
                        });
                    } else if (randomNum <= 6) {
                        execute = false;
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle(I18N.get("bundles.scenes.update", names[6]).replace("&#13;", System.lineSeparator()));
                            alert.setHeaderText(I18N.get("bundles.scenes.update", names[6]).replace("&#13;", System.lineSeparator()));
                            alert.setContentText(I18N.get("bundles.scenes.update", names[5]).replace("&#13;", System.lineSeparator()));

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                ownStage.close();
                            }
                            execute = true;
                        });
                    } else {
                        ownStage.close();
                    }
                }
            }
        }));

        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
}
