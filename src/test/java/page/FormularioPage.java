package page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import utils.Helpers;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class FormularioPage extends PageObject {

    Helpers helpers = new Helpers();

    @FindBy(xpath = "//button[text()='Place Order']")
    WebElementFacade btnPlaceOrder;

    @FindBy(id = "name")
    WebElementFacade txtName;

    @FindBy(id = "country")
    WebElementFacade txtCountry;

    @FindBy(id = "city")
    WebElementFacade txtCity;

    @FindBy(id = "card")
    WebElementFacade txtCard;

    @FindBy(id = "month")
    WebElementFacade txtMonth;

    @FindBy(id = "year")
    WebElementFacade txtYear;

    @FindBy(xpath = "//button[text()='Purchase']")
    WebElementFacade btnPurchase;

    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
    WebElementFacade lblPurchaseSuccess;

    public void completoElFormularioDeCompra(String nombre, String pais, String ciudad,
                                             String numeroTarjeta, String mes, String anio) {
        btnPlaceOrder.click();
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(txtName);
        txtName.sendKeys(nombre);
        txtCountry.sendKeys(pais);
        txtCity.sendKeys(ciudad);
        txtCard.sendKeys(numeroTarjeta);
        txtMonth.sendKeys(mes);
        txtYear.sendKeys(anio);
        helpers.scroll(btnPurchase);
        Serenity.takeScreenshot();
        btnPurchase.click();
    }

    public void verificoQueLaCompraSeHayaRealizadoCorrectamente() {
        if(lblPurchaseSuccess.isPresent()){
            assertThat("compra realizada con exito", true);
        }else{
            fail("la compra no se pudo completar");
        }
    }

}
