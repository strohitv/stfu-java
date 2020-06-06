package tv.strohi.stfu.gui.i18n;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.List;

public final class LocalizationBinder {
    private LocalizationBinder() {
    }

    public static void addComboboxListener(ComboBox<String> comboBox, String resBundleName, String[] keys) {
        ObservableMap<String, String> map = I18N.registerComboBoxBinding(resBundleName, keys);
        comboBox.itemsProperty().bind(Bindings.createObjectBinding(() -> FXCollections.observableArrayList(map.values()), map));
    }

    @SuppressWarnings("unchecked")
    public static void addListeners(Object[] node, String resBundleName) {
        for (Object component : node) {
            if (component instanceof Pane) {
                addListeners(((Pane) component).getChildren().toArray(), resBundleName);
            } else if (component instanceof MenuBar) {
                addListeners(((MenuBar) component).getMenus().toArray(), resBundleName);
            } else if (component instanceof Menu) {
                addListeners(((Menu) component).getItems().toArray(), resBundleName);
                ((Menu) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((Menu) component).getText()));
            } else if (component instanceof MenuItem) {
                if (((MenuItem) component).getText() != null) {
                    ((MenuItem) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((MenuItem) component).getText()));
                }
            } else if (component instanceof TabPane) {
                addListeners(((TabPane) component).getTabs().toArray(), resBundleName);
            } else if (component instanceof Tab) {
                addListeners(List.of(((Tab) component).getContent()).toArray(), resBundleName);
                ((Tab) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((Tab) component).getText()));
            } else if (component instanceof TitledPane) {
                addListeners(List.of(((TitledPane) component).getContent()).toArray(), resBundleName);
//                ((TitledPane) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((TitledPane) component).getText()));


//            } else if (component instanceof ComboBox<?> && ((ComboBox<?>) component).getItems().stream().allMatch(f -> f instanceof String)) {
//                // hier muss auf die ganze Liste gebindet werden.....
//                // TODO: mittels des Beispiels eine Map bauen und diese auf die Items binden.
//                //  Die muss dann halt hardcoded reingepackt werden, hilft halt alles nichts...
//                //  https://stackoverflow.com/questions/34459519/bind-comboboxitemsproperty-to-mapkeyset
//                ObservableMap<String, String> map = FXCollections.observableHashMap();
//                map.put("testlalala", "testdududu");
//                ((ComboBox<String>) component).itemsProperty().bind(Bindings.createObjectBinding(() -> FXCollections.observableArrayList(map.values()), map));
//                map.put("testlalala2", "testdududu2");
//                map.replace("testlalala2", "neeheutenichtschalala");
            } else if (component instanceof SplitMenuButton) {
                addListeners(((SplitMenuButton) component).getItems().toArray(), resBundleName);
            } else if (component instanceof TableView<?>) {
                addListeners(((TableView<?>) component).getColumns().toArray(), resBundleName);
            } else if (component instanceof TableColumn<?, ?>) {
                ((TableColumn<?, ?>) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((TableColumn<?, ?>) component).getText()));
            }

            if (component instanceof Labeled) {
                ((Labeled) component).textProperty().bind(I18N.createStringBinding(resBundleName, ((Labeled) component).getText()));
            }
        }
    }
}
