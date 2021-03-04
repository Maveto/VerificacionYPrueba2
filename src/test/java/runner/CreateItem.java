package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateItem {

    Response response;

    @Given("Tengo acceso a Todo.ly")
    public void tengoAccesoATodoLy() {
        System.out.println("Conectado con Todo.ly");
    }

    @When("Envio una peticion POST a {} con el json")
    public void envioUnaPeticionPOSTAConElJson(String url, String body) {

        response = given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                post(url);
    }

    @Then("Espero que el codigo de respuesta sea {int}")
    public void esperoQueElCodigoDeRespuestaSea(int expRes) {
        response.then().statusCode(expRes);
    }

    @And("Espero que el nombre del Item sea {string}")
    public void esperoQueElNombreDelItemSea(String expNameItem) {
        response.then().body("Content", equalTo(expNameItem));
    }
}
