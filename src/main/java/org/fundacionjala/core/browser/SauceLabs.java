package org.fundacionjala.core.browser;

import org.fundacionjala.core.util.PropertiesInput;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class allows to establish the connection in the remote browser through SauceLabs.
 */
public class SauceLabs extends RemoteBrowserConnection implements Browser {

    private static final String URL =
            String.format("http://%s:%s@ondemand.saucelabs.com:80/wd/hub", USERNAME, ACCESS_KEY);
    private static final String BROWSER_NAME = "browserName";
    private static final String PLATFORM = "platform";
    private static final String BROWSER_VERSION = "version";

    /**
     * This is the constructor.
     */
    public SauceLabs() {
        super(URL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DesiredCapabilities setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(BROWSER_NAME, PROPERTIES_MANAGER.getProperties(PropertiesInput.REMOTE_BROWSER_NAME));
        capabilities.setCapability(PLATFORM, PROPERTIES_MANAGER.getProperties(PropertiesInput.REMOTE_PLATAFORM));
        capabilities.setCapability(BROWSER_VERSION, PROPERTIES_MANAGER.getProperties(PropertiesInput.REMOTE_BROSER_VERSION));
        capabilities.setCapability(RESOLUTION, PROPERTIES_MANAGER.getProperties(PropertiesInput.REMOTE_RESOLUTION));
        return capabilities;
    }
}
