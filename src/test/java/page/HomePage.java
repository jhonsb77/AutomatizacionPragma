package page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Helpers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends PageObject {

    Helpers helpers = new Helpers();

    @FindBy(xpath = "//a[contains(text(),'PRODUCT STORE')]")
    WebElementFacade btnInicio;

    @FindBy(xpath = "//*[text()='Add to cart']")
    WebElementFacade btnAddToCart;

    @FindBy(id = "cartur")
    WebElementFacade btnVerCarrito;

    @FindBy(xpath = "//button[text()='Next']")
    WebElementFacade btnNext;


    public void agregarProductosCarrito(String productos) throws InterruptedException {
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(btnVerCarrito);
        String[] listaProducto = productos.split(", ");
        List<WebElementFacade> btnProduct = new ArrayList<>();
        for (String s : listaProducto) {
            btnProduct.add(find(By.xpath("//a[text()='" + s + "']")));
        }
        for (int i=0;i<btnProduct.size();i++){
            System.out.println("entro a seleccionar el producto "+i+"---"+listaProducto[i]);
            if(!btnProduct.get(i).isPresent()){
                try{
                    helpers.scroll(btnProduct.get(i));
                }catch (Exception e){
                    helpers.scroll(btnNext);
                    btnNext.click();
                    Serenity.takeScreenshot();
                }
                Thread.sleep(2000);
                helpers.scroll(btnProduct.get(i));
            }
            btnProduct.get(i).click();
            withTimeoutOf(Duration.ofSeconds(5)).waitFor(btnAddToCart);
            Serenity.takeScreenshot();
            try{
                btnAddToCart.click();
            }catch (org.openqa.selenium.StaleElementReferenceException e){
                btnAddToCart.click();
            }
            waitFor(ExpectedConditions.alertIsPresent());
            System.out.println(getDriver().switchTo().alert().getText());
            Serenity.takeScreenshot();
            getDriver().switchTo().alert().accept();
            btnInicio.click();
        }
    }

    public void seleccionoCategoria(String categoria) throws InterruptedException {
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(btnVerCarrito);
        WebElementFacade lstCategoria  = find(By.xpath("//*[text()='" +categoria+ "']"));
        lstCategoria.click();
        Thread.sleep(1000);
        Serenity.takeScreenshot();
    }

}
