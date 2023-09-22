package BackendTest.Apis;

import BackendTest.Base.Specs;
import BackendTest.Data.Route;
import BackendTest.Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response register(User user){

        return given().spec(Specs.getRequestSpec())
                .body(user)
                .when().post(Route.REGISTER_ROUTE)
                .then()
                .log().all().extract().response();

    }

    public static Response login(User user)
    {
      return given().spec(Specs.getRequestSpec())
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(Route.LOGIN_ROUTE)
                .then()
                .log().all()
                .extract().response();
    }
}
