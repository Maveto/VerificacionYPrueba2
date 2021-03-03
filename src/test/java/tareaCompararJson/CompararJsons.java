package tareaCompararJson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class CompararJsons {


    public static boolean compararJsons(String actRes, String expRes) {

        Gson gson = new Gson();

        JsonElement actResje = gson.fromJson(actRes, JsonElement.class);
        JsonObject actResjo = actResje.getAsJsonObject();

        JsonElement expResje = gson.fromJson(expRes, JsonElement.class);
        JsonObject expResjo = expResje.getAsJsonObject();

        boolean isEqual = true;

        for (Map.Entry<String, JsonElement> temp: actResjo.entrySet()) {
            if (!expResjo.has(temp.getKey())){
                isEqual = false;
                System.out.println("La siguiente llave no existe en el resultado esperado: " + temp.getKey());
            }else if (!(actResjo.get(temp.getKey()).equals(expResjo.get(temp.getKey())) || expResjo.get(temp.getKey()).getAsString().equalsIgnoreCase("IGNORE"))) {
                isEqual = false;
                System.out.println("La siguiente llave no es igual: " + temp.getKey());
                System.out.println("Expected Result: " + expResjo.get(temp.getKey()) + "\nActual Result: " + actResjo.get(temp.getKey()));
            }
        }

        return isEqual;
    }
}
