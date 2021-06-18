package com.kobiton.scriptlessautomation;


import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestApp extends TestBase {
    public void runTest() throws Exception {
        try {
            updateSettings();
            switchToNativeContext();
            setImplicitWaitInSecond(Config.IMPLICIT_WAIT_IN_SECOND);
            
            setCurrentCommandId(645821780);
            By locatorButtonSkip1 = MobileBy.id("com.vroom.app.android:id/button_skip");
            MobileElement elementButtonSkip1 = findElementBy(locatorButtonSkip1);
            hideKeyboard();
            touchAtRelativePointOfElement(elementButtonSkip1, 0.50556, 0.43651);
            
            setCurrentCommandId(645821895);
            By locatorSEARCH1 = MobileBy.AccessibilityId("SEARCH");
            MobileElement elementSEARCH1 = findElementBy(locatorSEARCH1);
            hideKeyboard();
            touchAtRelativePointOfElement(elementSEARCH1, 0.5, 0.28571);
            
            setCurrentCommandId(645821985);
            By locatorEditTextSearch2 = MobileBy.id("com.vroom.app.android:id/edit_text_search");
            MobileElement elementEditTextSearch2 = findElementBy(locatorEditTextSearch2);
            hideKeyboard();
            touchAtRelativePointOfElement(elementEditTextSearch2, 0.32903, 0.3806);
            
            setCurrentCommandId(645822127);
            By locatorEditTextSearch3 = MobileBy.id("com.vroom.app.android:id/edit_text_search");
            sendKeys("Toyota Corolla");
            
            setCurrentCommandId(645822197);
            sleep(3000);
            press(PRESS_TYPES.ENTER);
            
            setCurrentCommandId(645822257);
            By locatorVehicleRecycler3 = MobileBy.id("com.vroom.app.android:id/vehicle_recycler");
            MobileElement elementVehicleRecycler3 = findElementBy(locatorVehicleRecycler3);
            swipeFromPoint(getAbsolutePoint(0.71204, 0.9013, elementVehicleRecycler3.getRect()), 0.003510000000000013, -0.45267999999999997, 430);
            
            setCurrentCommandId(645822282);
            By locatorVehicleRecycler4 = MobileBy.id("com.vroom.app.android:id/vehicle_recycler");
            MobileElement elementVehicleRecycler4 = findElementBy(locatorVehicleRecycler4);
            swipeFromPoint(getAbsolutePoint(0.54722, 0.3892, elementVehicleRecycler4.getRect()), 0, -0.43374999999999997, 362);
            
            setCurrentCommandId(645822310);
            By locatorVehicleRecycler5 = MobileBy.id("com.vroom.app.android:id/vehicle_recycler");
            MobileElement elementVehicleRecycler5 = findElementBy(locatorVehicleRecycler5);
            swipeFromPoint(getAbsolutePoint(0.51944, 0.63998, elementVehicleRecycler5.getRect()), 0, -0.39274000000000003, 349);

        } catch (Exception e) {
            saveDebugResource();
            e.printStackTrace();
            throw e;
        } finally {
            cleanup();
        }
    }

    @Override
    public void setup(DesiredCapabilities desiredCaps, double retinaScale) throws Exception {
        super.setup(desiredCaps, retinaScale);
        Reporter.log(String.format("View session at: https://portal.kobiton.com/sessions/%s", getKobitonSessionId()));
    }
}
