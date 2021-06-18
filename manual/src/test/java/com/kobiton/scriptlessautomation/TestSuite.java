package com.kobiton.scriptlessautomation;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TestSuite {
    
    @Test
    public void testOnGalaxyA905GAndroid111() throws Exception {
        DesiredCapabilities capabilities = Config.getPixel4Android10DesiredCapabilities1();
        TestApp testApp = new TestApp();
        testApp.findOnlineDevice(capabilities);
        testApp.setup(capabilities, 1);
        testApp.runTest();
    }

}
