package basicRestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static tareaCompararJson.CompararJsons.compararJsons;

public class RESTItem {

    private Response res;

    @Test
    public void verify_crud_item() {

        JSONObject body = new JSONObject();
        body.put("Content", "NewItem");
        body.put("Priority", 1);

        //Create

        res = given().
                        auth().
                        preemptive().
                        basic("upb2021@upb.com", "12345").
                        contentType(ContentType.JSON).
                        body(body.toString()).
                        log().all().
                when().post("http://todo.ly/api/items.json");

        res.then().
                statusCode(200).
                body("Content", equalTo("NewItem")).log().all();

        int idItem = res.then().extract().path("Id");

        System.out.println("TEST");
        String str1 = "{ \"Id\" : \"12345\",\n" +
                "\"Test2\" : \"Hola\",\n" +
                "\"Test\" : \"Chau\"}";
        String str2 = "{ \"Id\" : \"12345\",\n" +
                "\"Test\" : \"1234\"}";
        System.out.println(compararJsons(str1, str2));
//        compararJsons(res.asString(), res.asString());

        //Update

        body.put("Content", "NewItem UPDATE");
        res = given().
                auth().
                preemptive().
                basic("upb2021@upb.com", "12345").
                contentType(ContentType.JSON).
                body(body.toString()).
                log().all().
              when().put("http://todo.ly/api/items/" + idItem + ".json");

        res.then().
                statusCode(200).
                body("Content", equalTo("NewItem UPDATE")).log().all();

        //Delete
        res = given().
                auth().
                preemptive().
                basic("upb2021@upb.com", "12345").
                contentType(ContentType.JSON).
                log().all().
                when().delete("http://todo.ly/api/items/" + idItem + ".json");

        res.then().statusCode(200).log().all();

    }

}
