package org.fundacionjala.core.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesManager.java.
 * Class that applies Singleton pattern to instance Properties only once.
 */
public final class PropertiesManager {
    private static PropertiesManager propertiesManager;
    private Properties properties;
    private static final Logger LOGGER = Logger.getLogger(PropertiesManager.class.getName());

    /**
     * Constructor, private to apply singleton pattern.
     */
    private PropertiesManager() {
        init();
    }

    /**
     * Static method to get an class instance.
     *
     * @return instance.
     */
    public static PropertiesManager getInstance() {
        if (propertiesManager == null) {
            propertiesManager = new PropertiesManager();
        }
        return propertiesManager;
    }

    /**
     * Initialize Properties object.
     */
    private void init() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("gradle.properties")) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Gets Url Login.
     *
     * @param var variable obtained of gradle.properties.
     * @return the enviroment value.
     */
    private String getEnvValue(final String var) {
        String property = System.getProperty(var);
        return property == null ? properties.getProperty(var) : property;
    }

    /**
     * Get PropertiesInputs.
     *
     * @param prop a Enum
     * @return String Enum
     */
    public String getProperties(final PropertiesInput prop) {
        return getEnvValue(prop.getPropertiesName());
    }
}
