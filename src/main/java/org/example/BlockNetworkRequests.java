package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v94.fetch.Fetch;
import org.openqa.selenium.devtools.v94.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.devtools.v94.network.model.ErrorReason;

import java.util.*;

public class BlockNetworkRequests {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver = new ChromeDriver();

        //devtools
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        ArrayList<String> lisaturls= new ArrayList<>();
        lisaturls.add("*GetBook*");
        lisaturls.add("*.jpg");

        devTools.send(Network.setBlockedURLs(lisaturls));


        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerLink='/library']")).click();


        Thread.sleep(5000);
        driver.quit();
    }
}
