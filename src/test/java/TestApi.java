import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestApi {
    //variável global
    String vote_id;

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String corpo = "{ \"email\": \"ricardoveiga.ti@gmail.com\",  \"appDescription\": \"test the cat api\" }";

        Response response =
                given().contentType("application/json").body(corpo).
                when().post(url);
        //validação é feita na ordem, contains e depois string
        response.then().statusCode(200).body("message",  containsString("SUCCESS"));

        System.out.print("Retorno =>" + response.body().asString());
    }

    @Test
    public void votacao(){
        String url = "https://api.thecatapi.com/v1/votes/";
        String corpo = "{\"image_id\": \"ie5eM6OOa\", \"value\": \"true\", \"sub_id\": \"demo-32818f\"}";

        Response response =
                given().contentType("application/json").body(corpo).
                when().post(url);
        //validação é feita na ordem, contains e depois string
        response.then().statusCode(200).body("message",  containsString("SUCCESS"));

        //System.out.print("Retorno => " + response.body().asString());
        //Pegando id para deletar futuramente
        String id = response.jsonPath().getString("id");
        String message = response.jsonPath().getString("message");
        System.out.print("ID => " + id );
        System.out.print("MESSAGE => " + message);
        vote_id = id;
    }

    @Test
    public void deletaVotacao(){
        votacao();
        deletaVoto();
    }

    @Test
    public void deletaVoto() {
        String url = "https://api.thecatapi.com/v1/votes/:{vote_id}";

        Response response =
                given()
                .contentType("application/json")
                .header("x-api-key", "2c2a0599-16d4-40b5-8973-4c2922c75655")
                .pathParam("vote_id", vote_id)
                .when().delete(url);

        System.out.print("Retorno =>" + response.body().asString());
        response.then().statusCode(200).body("message",  containsString("SUCCESS"));
    }
}
