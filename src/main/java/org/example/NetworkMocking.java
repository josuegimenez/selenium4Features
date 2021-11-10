package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v94.fetch.Fetch;
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.devtools.v94.network.model.Request;
import org.openqa.selenium.devtools.v94.network.model.Response;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver = new ChromeDriver();

        //devtools
        DevTools devTools = driver.getDevTools();
        devTools.createSession();



        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(),requestPaused -> {
            if(requestPaused.getRequest().getUrl().contains("shetty"))
            {
                String mockedURL=requestPaused.getRequest().getUrl().replace("=shetty","=BadGuy");
                System.out.println(mockedURL);
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(mockedURL), Optional.of(requestPaused.getRequest().getMethod()),
                        Optional.empty(),Optional.empty(),Optional.empty()));
            }
            else devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),Optional.of(requestPaused.getRequest().getUrl()), Optional.of(requestPaused.getRequest().getMethod()),
                    Optional.empty(),Optional.empty(),Optional.empty()));
        });


        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerLink='/library']")).click();
        Thread.sleep(3000);
        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        Thread.sleep(5000);

        driver.quit();
    }
}
