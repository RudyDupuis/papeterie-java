package fr.eni.papeterie.dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }
}

