package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.devtools.v94.network.model.Request;
import org.openqa.selenium.devtools.v94.network.model.Response;

import java.util.Optional;

public class NetworkLogActivity {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver = new ChromeDriver();

        //devtools
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));


        //REQUEST
        //Event will get fired
        devTools.addListener(Network.requestWillBeSent(),requestWillBeSent ->
        {
            Request req = requestWillBeSent.getRequest();
            System.out.println(req.getUrl());
//            System.out.println(req.getHeaders());
//            requestWillBeSent.get.getStatus();
        });

        //RESPONSE
        //Event will get fired
        devTools.addListener(Network.responseReceived(),responseReceived ->
        {
            Response res = responseReceived.getResponse();
//            System.out.println(res.getUrl());
//            System.out.println(res.getStatus());
            if (res.getStatus().toString().startsWith("4")){//prints the url where there has been error of 404 or something linke this
                System.out.println(res.getUrl()+" is failing with statuts code"+res.getStatus());
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerLink='/library']")).click();

        driver.quit();
    }
}
