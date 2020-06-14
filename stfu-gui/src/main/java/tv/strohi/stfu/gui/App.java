package tv.strohi.stfu.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tv.strohi.stfu.gui.i18n.I18N;
import tv.strohi.stfu.gui.version.Version;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {
    private static Scene scene;
    private static Locale locale;

    public App() {
        setLocale(System.getProperty("user.language"));
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("scenes/main"));
        stage.setTitle("STFU - " + Version.getVersion() + " [BETA]");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Locale getLocale() {
        if (locale == null) {
            setLocale(System.getProperty("user.language"));
        }

        return locale;
    }

    public static void setLocale(String locale) {
        if (locale != null && locale.toLowerCase().startsWith("de")) {
            App.locale = new Locale("de", "DE");
        } else {
            App.locale = new Locale("en", "EN");
        }

        I18N.setLocale(App.locale);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/tv/strohi/stfu/gui/" + fxml + ".fxml"));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles." + fxml.replace('/', '.')));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
