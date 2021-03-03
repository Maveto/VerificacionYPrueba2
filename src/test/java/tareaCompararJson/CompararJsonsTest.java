package tareaCompararJson;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static tareaCompararJson.CompararJsons.compararJsons;

@RunWith(value = Parameterized.class)
public class CompararJsonsTest {

    String json1;
    String json2;

    public CompararJsonsTest(String json1, String json2) {
        this.json1 = json1;
        this.json2 = json2;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> getData() {
        List<Object[]> objects = new ArrayList<>();

        objects.add(new Object[]{   "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}",
                                    "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}"});

        objects.add(new Object[]{   "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}",
                                    "{ \"Id\" : \"12345\",\"Test2\" : \"Hola\",\"Test\" : \"Chau\"}"});

        objects.add(new Object[]{   "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}",
                                    "{ \"Id\" : \"12345\",\"Test\" : \"Hola\"}"});

        objects.add(new Object[]{   "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}",
                                    "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"IGNORE\"}"});

        objects.add(new Object[]{   "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"Chau\"}",
                                    "{ \"Id\" : \"12345\",\"Test\" : \"Hola\",\"Test2\" : \"ignore\"}"});

        return objects;
    }

    @Test
    public void verifyCompararJsons(){
        Assert.assertTrue("ERROR: ", compararJsons(json1, json2));
    }

}
