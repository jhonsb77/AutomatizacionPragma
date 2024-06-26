package stepsDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.CarritoPage;
import page.FormularioPage;
import page.HomePage;
import utils.DataDrivenExcel;
import utils.Excel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompraDefinition {

    HomePage homePage;
    CarritoPage carritoPage;

    FormularioPage formularioPage;

    public static Map<String, String> data = new HashMap<>();
    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();

    @Given("ingreso al portal demoblaze {int}")
    public void ingresoAlPortalDemoblaze(int row, DataTable dataExcel) {
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);
        Excel excel = new Excel(dataFeature.get(0).get("Ruta Excel"), dataFeature.get(0).get("Pestana"), true, row);
        data = dataDriverExcel.leerExcel(excel);
        homePage.open();
    }

    @When("selecciono la categoria")
    public void selecciono_la_categoria() throws InterruptedException {
        homePage.seleccionoCategoria(data.get("categoria"));
    }


    @When("agrego los productos al carrito de compra")
    public void agrego_los_productos_al_carrito_de_compra() throws InterruptedException {
        homePage.agregarProductosCarrito(data.get("producto"));
    }

    @When("visualizo el carrito con los productos agregados")
    public void visualizo_el_carrito_con_los_productos_agregados() {
        carritoPage.visualizarCarrito(data.get("producto"));
    }

    @When("completo el formulario de compra")
    public void completo_el_formulario_de_compra() {
        formularioPage.completoElFormularioDeCompra(data.get("nombre"), data.get("pais"), data.get("ciudad"),
                data.get("numeroTarjeta"), data.get("mes"), data.get("año"));
    }

    @Then("verifico que la compra se haya realizado correctamente")
    public void verifico_que_la_compra_se_haya_realizado_correctamente() {
        formularioPage.verificoQueLaCompraSeHayaRealizadoCorrectamente();
    }

    @Then("elimino el producto deseado del carrito")
    public void elimino_el_producto_deseado_del_carrito() throws InterruptedException {
        carritoPage.eliminar(data.get("producto a eliminar"));
    }


}
