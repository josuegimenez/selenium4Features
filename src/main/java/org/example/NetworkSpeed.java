package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v94.fetch.Fetch;
import org.openqa.selenium.devtools.v94.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v94.network.Network;
import org.openqa.selenium.devtools.v94.network.model.ConnectionType;
import org.openqa.selenium.devtools.v94.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/chromedriver_94");


        ChromeDriver driver = new ChromeDriver();

        //devtools
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false,3000,20000,
                10000, Optional.of(ConnectionType.CELLULAR2G)));

        //si pongo el offline a true (arriba) no habrá internet y fallará el load de la pagina
        //entonces quiero sacar el error que daría en ese caso, por si hay control de errores
        //en nuestra web o no
        //El codigo de abajo solo se ejecutara si el load falla, como por ejemplo si no hay internet
        devTools.addListener(Network.loadingFailed(),loadingFailed->{
            System.out.println(loadingFailed.getErrorText());
            System.out.println(loadingFailed.getTimestamp());
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerLink='/library']")).click();


        Thread.sleep(10000);
        driver.quit();
    }
}
