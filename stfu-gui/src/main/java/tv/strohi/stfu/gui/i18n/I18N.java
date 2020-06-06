package tv.strohi.stfu.gui.i18n;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * I18N utility class..
 */
public final class I18N {

    /**
     * the current selected Locale.
     */
    private static final ObjectProperty<Locale> locale;
    private static final Map<String, List<ObservableMap<String[], ObservableList<String>>>> comboBoxMaps = new HashMap<>();

    static {
        locale = new SimpleObjectProperty<>(getDefaultLocale());
        locale.addListener((observable, oldValue, newValue) -> Locale.setDefault(newValue));
    }

    public I18N() {
    }

    /**
     * get the supported Locales.
     *
     * @return List of Locale objects.
     */
    public static List<Locale> getSupportedLocales() {
        return new ArrayList<>(Arrays.asList(Locale.ENGLISH, Locale.GERMAN));
    }

    /**
     * get the default locale. This is the systems default if contained in the supported locales, english otherwise.
     */
    public static Locale getDefaultLocale() {
        Locale sysDefault = Locale.getDefault();
        return getSupportedLocales().contains(sysDefault) ? sysDefault : Locale.ENGLISH;
    }

    public static Locale getLocale() {
        return locale.get();
    }

    public static void setLocale(Locale locale) {
        localeProperty().set(locale);
        Locale.setDefault(locale);

        for (Map.Entry<String, List<ObservableMap<String[], ObservableList<String>>>> resBundleMaps : comboBoxMaps.entrySet()) {
            for (ObservableMap<String[], ObservableList<String>> comboBoxBinding : resBundleMaps.getValue()) {
                for (Map.Entry<String[], ObservableList<String>> entry : comboBoxBinding.entrySet()) {
                    for (int i = 0; i < entry.getKey().length; i++) {
                        entry.getValue().set(i, get(resBundleMaps.getKey(), entry.getKey()[i]));
                    }
                }
            }
        }
    }

    public static ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    /**
     * gets the string with the given key from the resource bundle for the current locale and uses it as first argument
     * to MessageFormat.format, passing in the optional args and returning the result.
     *
     * @param key  message key
     * @param args optional arguments for the message
     * @return localized formatted string
     */
    public static String get(final String resBundleName, final String key, final Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle(resBundleName, getLocale());
        return MessageFormat.format(bundle.getString(key), args);
    }

    /**
     * creates a String binding to a localized String for the given message bundle key
     *
     * @param key key
     * @return String binding
     */
    public static StringBinding createStringBinding(final String resBundleName, final String key, Object... args) {
        return Bindings.createStringBinding(() -> {
            String result = get(resBundleName, key, args);
            System.out.println();
            return result;
        }, locale);
    }

    public static ObservableList<String> registerComboBoxBinding(String resBundleName, String[] keys) {
        ObservableMap<String[], ObservableList<String>> map = FXCollections.observableHashMap();

        comboBoxMaps.computeIfAbsent(resBundleName, k -> new ArrayList<>()).add(map);

        ObservableList<String> list = FXCollections.observableArrayList();
        map.put(keys, list);

        for (String key : keys) {
            list.add(get(resBundleName, key));
        }

        return list;
    }

    /**
     * creates a String binding to a localized String for the given message bundle key
     *
     * @param key key
     * @return String binding
     */
    public StringBinding createStringBinding2(final String resBundleName, final String key, Object... args) {
        return Bindings.createStringBinding(() -> {
            String result = get(resBundleName, key, args);
            System.out.println();
            return result;
        }, locale);
    }

    /**
     * creates a String Binding to a localized String that is computed by calling the given func
     *
     * @param func function called on every change
     * @return StringBinding
     */
    public static StringBinding createStringBinding(Callable<String> func) {
        return Bindings.createStringBinding(func, locale);
    }

    /**
     * creates a bound Label whose value is computed on language change.
     *
     * @param func the function to compute the value
     * @return Label
     */
    public static Label labelForValue(Callable<String> func) {
        Label label = new Label();
        label.textProperty().bind(createStringBinding(func));
        return label;
    }

    /**
     * creates a bound Button for the given resourcebundle key
     *
     * @param key  ResourceBundle key
     * @param args optional arguments for the message
     * @return Button
     */
    public static Button buttonForKey(final String resBundleName, final String key, final Object... args) {
        Button button = new Button();
        button.textProperty().bind(createStringBinding(resBundleName, key, args));
        return button;
    }
}