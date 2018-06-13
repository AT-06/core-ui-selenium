package org.fundacionjala.core.util;

/**
 * Class Enum PropertiesInputs.
 */
public enum PropertiesInput {

    URL_LOGIN("urlLogin"),
    USER("user"),
    PASSWORD("password"),
    BROWSER("browser"),
    DOCKER_URL("dockerURL"),
    REMOTE_USER_NAME("remoteUserName"),
    REMOTE_ACCESS_KEY("remoteAccessKey"),
    REMOTE_BROWSER_NAME("remoteBrowserName"),
    REMOTE_BROSER_VERSION("remoteBrowserVersion"),
    REMOTE_RESOLUTION("remoteResolution"),
    REMOTE_PLATAFORM("remotePlatform"),
    REMOTE_OS("remoteOS"),
    REMOTE_OS_VERSION("remoteOSVersion"),
    EXPLICIT_WAIT("explicitWait");

    private String propertiesName;

    /**
     * Constructor.
     *
     * @param propertiesName
     */
    PropertiesInput(final String propertiesName) {
        this.propertiesName = propertiesName;
    }

    /**
     * @return propertiesName the String enum.
     */
    public String getPropertiesName() {
        return propertiesName;
    }
}
