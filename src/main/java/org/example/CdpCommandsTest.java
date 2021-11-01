package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CdpCommandsTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");

        
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools =driver.getDevTools();
        devTools.createSession();

        //Esta es la manera "custom" de acceder a CDP, podemos utilizar esta siempre pero no tiene mucho sentido
        // si los chicos de Selenium ya la han implementado. Para los métodos que no hayan implementado si que
        // tiene más sentido hacerlo así

        Map deviceMetrics = new HashMap();
        deviceMetrics.put("width", 400);
        deviceMetrics.put("height", 400);
        deviceMetrics.put("deviceScaleFactor", 50);
        deviceMetrics.put("mobile", true);


        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);


        driver.get("http://google.es");
        Thread.sleep(6000);
        driver.quit();
    }
}
