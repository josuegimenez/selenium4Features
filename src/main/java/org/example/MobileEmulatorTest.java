package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.devtools.v85.emulation.model.ScreenOrientation;
import org.openqa.selenium.devtools.v85.page.model.Viewport;

import java.util.Optional;

public class MobileEmulatorTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver=new ChromeDriver();
        DevTools devTools =driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setDeviceMetricsOverride(300,400,50,true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));



        driver.get("http://google.es");
        Thread.sleep(6000);
        //driver.quit();
    }
}
