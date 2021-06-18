package com.kobiton.scriptlessautomation;

import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Config {
    enum DEVICE_SOURCE_ENUMS {KOBITON, OTHER}

    public static final String KOBITON_USERNAME = "LeslieTanNH";
    public static final String KOBITON_API_KEY = "89a36fa3-bbf5-42b7-92c5-a7d13efa7575";
    public static final String KOBITON_API_URL = "https://api.kobiton.com";
    public static final String APPIUM_SERVER_URL = "https://" + KOBITON_USERNAME + ":" + KOBITON_API_KEY + "@api.kobiton.com/wd/hub";
    public static final DEVICE_SOURCE_ENUMS DEVICE_SOURCE = DEVICE_SOURCE_ENUMS.KOBITON;
    public static final int IMPLICIT_WAIT_IN_SECOND = 30;
    public static final int DEVICE_WAITING_MAX_TRY_TIMES = 5;
    public static final int DEVICE_WAITING_INTERVAL_IN_MS = 30000;

    public static String getBasicAuthString() {
        String authString = KOBITON_USERNAME + ":" + KOBITON_API_KEY;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
        String authEncString = new String(authEncBytes);
        return "Basic " + authEncString;
    }
    
    public static DesiredCapabilities getPixel4Android10DesiredCapabilities1() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("sessionName", "Azure DevOps session");
        capabilities.setCapability("sessionDescription", "-- ** --");
        capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("captureScreenshots", true);
        capabilities.setCapability("newCommandTimeout", 15 * 60);
        capabilities.setCapability("ensureWebviewsHavePages", true);
        capabilities.setCapability("kobiton:referenceSessionId", 2686762);
        capabilities.setCapability("kobiton:visualValidation", false);
        capabilities.setCapability(MobileCapabilityType.APP, "kobiton-store:v202854");
        capabilities.setCapability("udid", "9B071FFAZ00989");
        return capabilities;
    }

}
