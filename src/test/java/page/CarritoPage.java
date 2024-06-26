package page;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CarritoPage extends PageObject {

    @FindBy(id = "cartur")
    WebElementFacade btnVerCarrito;

    @FindBy(xpath = "//h2[text()='Products']")
    WebElementFacade lblProductos;

    @FindBy(xpath = "//tbody[@id='tbodyid']//td[2]")
    List<WebElementFacade> listaProductos;

    @FindBy(xpath = "//*[text()='Delete']")
    List<WebElementFacade> btnDelete;

    public void visualizarCarrito(String productos){
        String[] listaPro = productos.split(", ");
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(btnVerCarrito);
        btnVerCarrito.click();
        withTimeoutOf(Duration.ofSeconds(5)).waitFor(listaProductos);
        List<String> listaCoincidencias = new ArrayList<>();

        for(int i=0;i<listaPro.length;i++){
            for (int j=0;j<listaProductos.size();j++){
                if(listaProductos.get(j).getText().equals(listaPro[i])){
                    listaCoincidencias.add(listaProductos.get(j).getText());
                }
            }
        }
        System.out.println("numero de coincidencias "+listaCoincidencias.size());
        assertThat("no se encontro algun producto en el carrito", listaCoincidencias.size()==listaPro.length);
    }

    public void eliminar(String productos) throws InterruptedException {
        String[] listaPro = productos.split(", ");
        List<String> listaCoincidencias = new ArrayList<>();
        int posicionDelete=0;

        for(int i=0;i<listaPro.length;i++){
            for (int j=0;j<listaProductos.size();j++){
                if(listaProductos.get(j).getText().equals(listaPro[i])){
                    btnDelete.get(j).click();
                }
            }
        }
        Serenity.takeScreenshot();
        Thread.sleep(2000);
        for(int i=0;i<listaPro.length;i++){
            for (int j=0;j<listaProductos.size();j++){
                if(listaProductos.get(j).getText().equals(listaPro[i])){
                    listaCoincidencias.add(listaProductos.get(j).getText());
                }
            }
        }
        System.out.println("numero de coincidencias "+listaCoincidencias.size());
        assertThat("no se elimino correctamente el producto indicado", listaCoincidencias.size()!=listaPro.length);
    }

}
