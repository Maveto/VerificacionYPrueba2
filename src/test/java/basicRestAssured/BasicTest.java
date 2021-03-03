package basicRestAssured;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BasicTest {

    @Test
    public void verify_get_project() {
        given().
                auth().
                preemptive().
                basic("upb2021@upb.com","12345").
                log().
                all().
        when().
                get("http://todo.ly/api/projects.json").
        then().
                log().
                all();
    }

    @Test
    public void createProject() {

        given().
                auth().
                preemptive().
                basic("upb2021@upb.com", "12345").
                contentType(ContentType.JSON).
                body(new File("src\\test\\resources\\createProject.json")).
                log().
                all().
        when().
                post("http://todo.ly/api/projects.json").
        then().
                statusCode(200).
                body("Contet", equalTo("RESTAssured")).
                body("Icon", equalTo(4)).
                log().
                all();
    }
}
