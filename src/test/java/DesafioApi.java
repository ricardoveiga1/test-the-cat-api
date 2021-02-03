import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DesafioApi extends MassaDados{

    @BeforeClass
    public static void urlBase(){
        RestAssured.baseURI = "https://api.thecatapi.com";
    }

    @Test
    public void deletaFavorito(){
        favoritar();
        desfavoritar();
    }

    @Test
    public void favoritar() {
        Response response =
                given().log().all()
                .contentType("application/json")
                .header("x-api-key", "2c2a0599-16d4-40b5-8973-4c2922c75655")
                .body(corpoFavoritar)
                .when().post(urlFavoritar);
                String id = response.jsonPath().getString("id");
                id_Favorito = id;

                validacao(response);
    }

    @Test
    public void desfavoritar() {
        Response response =
                given()
                .contentType("application/json")
                .pathParam("favourite_id", id_Favorito)
                .header("x-api-key", "2c2a0599-16d4-40b5-8973-4c2922c75655")
                .when().delete(urlDesfavoritar);
        validacao(response);

        //System.out.print("Retorno Desfavorita =>" + response.body().asString());
        //response.then().statusCode(200).body("message",  containsString("SUCCESS"));
    }

    public void validacao(Response res){

        res.then().statusCode(200).body("message",  containsString("SUCCESS"));//mesma validão para todas chamadas, o ideal é criar métodos de validação
        System.out.print("Retorno da API => " + res.body().asString());
        System.out.print("-------------------------------------------------") ;

    }

}
