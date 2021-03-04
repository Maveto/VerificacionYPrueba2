package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import tareaCompararJson.CompararJsons;

import static io.restassured.RestAssured.given;

public class TareaCRUDItem {

    Response response;
    int Id_Item;

    @Given("tengo acceso a Todo.ly")
    public void tengoAccesoATodoLy() {
        System.out.println("Aceso a Todo.ly");
    }

    @When("Yo envio una peticion POST a {} con el json")
    public void yoEnvioUnaPeticionPOSTAHttpTodoLyApiItemsJsonConElJson(String url, String body) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                post(url);

        response.then().log().all();
    }

    @Then("Yo espero que el codigo de respuesta sea {int}")
    public void yoEsperoQueElCodigoDeRespuestaSea(int expRes) {
        response.then().statusCode(expRes);
    }

    @And("Yo espero que el body de la respuesta sea")
    public void yoEsperoQueElBodyDeLaRespuestaSea(String expBody) {
        Assert.assertTrue("ERROR! Los jsons no son iguales", CompararJsons.compararJsons(response.getBody().asString(), expBody));
    }

    @And("Yo obtengo el id y lo guardo en Id_Item")
    public void yoObtengoElIdYLoGuardoEnId_Item() {
        Id_Item = response.then().extract().path("Id");
    }

    @When("Yo envio una peticion PUT a {} con el json")
    public void yoEnvioUnaPeticionPUTAHttpTodoLyApiItemsId_ItemJsonConElJson(String url, String body) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                put(url.replace("Id_Item", Id_Item+""));
        response.then().log().all();
    }

    @Then("Yo quiero que el codigo de respuesta sea {int}")
    public void yoQuieroQueElCodigoDeRespuestaSea(int espRes) {
        response.then().statusCode(espRes);
    }

    @And("Espero que el body del response sea")
    public void esperoQueElBodyDelResponseSea(String expBody) {
        Assert.assertTrue("ERROR! Los jsons no son iguales", CompararJsons.compararJsons(response.getBody().asString(), expBody));
    }

    @When("Yo envio una peticion DELETE {}")
    public void yoEnvioUnaPeticionDELETEHttpTodoLyApiItemsId_ItemJson(String url) {
        response =  given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                delete(url.replace("Id_Item", Id_Item+""));
        response.then().log().all();
    }
}
