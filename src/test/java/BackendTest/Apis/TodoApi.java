package BackendTest.Apis;

import BackendTest.Base.Specs;
import BackendTest.Data.Route;
import BackendTest.Models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response addTodo(String token, Todo todo)
    {
        return given().spec(Specs.getRequestSpec())
                .body(todo)
                .auth().oauth2(token)
                .when()
                .post(Route.TODOS_ROUTE)
                .then()
                .log().all().extract().response();
    }

    public static Response getTodo(String taskId, String token){
       return given().given().spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(Route.TODOS_ROUTE+"/" + taskId)
                .then()
                .log().all().extract().response();
    }

    public static Response deleteTodo(String taskId, String token)
    {
      return given().given().spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .delete(Route.TODOS_ROUTE+"/" + taskId)
                .then()
                .log().all().extract().response();
    }


}
