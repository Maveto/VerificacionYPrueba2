package tareaCompararJson;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static tareaCompararJson.CompararJsons.compararJsons;

@RunWith(value = Parameterized.class)
public class CrudItems {

    String content;
    int priority;

    public CrudItems(String content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        List<Object[]> objects = new ArrayList<>();

        objects.add(new Object[]{"", 1});
        objects.add(new Object[]{"", 2});
        objects.add(new Object[]{"", 3});
        objects.add(new Object[]{"", 4});

        objects.add(new Object[]{"A", 1});
        objects.add(new Object[]{"AB", 2});
        objects.add(new Object[]{"ABC", 3});
        objects.add(new Object[]{"ABCD", 4});

        objects.add(new Object[]{"a", 1});
        objects.add(new Object[]{"ab", 2});
        objects.add(new Object[]{"abc", 3});
        objects.add(new Object[]{"abcd", 4});

        objects.add(new Object[]{"1", 1});
        objects.add(new Object[]{"12", 2});
        objects.add(new Object[]{"123", 3});
        objects.add(new Object[]{"1234", 4});

        objects.add(new Object[]{"abcd1234", 1});
        objects.add(new Object[]{"1234abcd", 2});
        objects.add(new Object[]{"ab12cd34", 3});
        objects.add(new Object[]{"12ab34cd", 4});

        return objects;
    }

    @Test
    public void verificar_crud_items() {

        Response res;
        JSONObject body = new JSONObject();
        body.put("Content", content);
        body.put("Priority", priority);

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
                body("Content", equalTo(content)).log().all();

        int idItem = res.then().extract().path("Id");

        //Update

        body.put("Content", content + " UPDATE");
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
                body("Content", equalTo(content + " UPDATE")).log().all();

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
