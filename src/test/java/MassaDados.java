public class MassaDados {
    String id_Favorito;
    String urlFavoritar = "/v1/favourites";
    String corpoFavoritar = "{\"image_id\": \"9sn\", \"sub_id\": \"demo-32818f\"}"; //sub_id não é necessário

    String urlDesfavoritar = "/v1/favourites/{favourite_id}";


}
