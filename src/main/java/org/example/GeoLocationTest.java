package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GeoLocationTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver=new ChromeDriver();
        DevTools devTools =driver.getDevTools();
        devTools.createSession();
//        devTools.send(Emulation.setGeolocationOverride( 50.63297,3.05858, Optional.empty()));

        Map <String, Object> location = new HashMap <String, Object>();
        location.put("latitude", 40);
        location.put("longitude", 3);
        location.put("coordinates", 1);


        driver.executeCdpCommand("Emulation.setGeolocationOverride",location);


        driver.get("http://google.com");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(text(),'I agree')]")).click();
        driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);
        driver.findElement(By.cssSelector("[class='LC20lb DKV0Md']")).click();
        Thread.sleep(9000);
        driver.quit();
    }
}
