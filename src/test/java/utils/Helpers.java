package utils;

import io.cucumber.java.After;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.fail;

public class Helpers extends PageObject {

    public void esperaSeleniumExplita(WebElementFacade objeto){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(objeto));
    }

    public void esperaImplicit(WebElementFacade element){
        WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOf(element));
        wait1.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void javaScriptClick(WebElementFacade elemento) {
        esperaSeleniumExplita(elemento);
        ((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].click();", elemento);
    }
    public void esperaSelenium(int segundos){
        segundos = segundos*1000;
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scroll(WebElementFacade element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElementFacade elementFacade =
                (WebElementFacade) js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @After
    public void cerrarDriver() {
        this.getDriver().close();
    }


}
