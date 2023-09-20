package BackendTest.TestCases;

import BackendTest.Models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.not;

public class TodoTest {


    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE0NzkxNn0.34KYTG8yNaKhdsnGx9vsA-GgwlhAWjtUOjf_uedLRW0";

    @Test
    public void shouldBeAbleToAddTodo() {

        Todo todo = new Todo(false, "Learn Appium");

        Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .body(todo)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .post("/api/v1/tasks")
                .then()
                .log().all().extract().response();
        System.out.println(response.body());
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.path("item"), equalTo("Learn Appium"));

        assertThat(response.path("isCompleted"), equalTo(false));

    }

    @Test
    public void shouldNoBeAbleToAddTodoIfIsCompletingIsMessing() {


        Todo todo = new Todo("Learn Appium");
        Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .body(todo)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .post("/api/v1/tasks")
                .then()
                .log().all().extract().response();

        assertThat(response.statusCode(), equalTo(400));
        assertThat(response.path("message"), equalTo("\"isCompleted\" is required"));

    }

    @Test
    public void shouldBeAbleToGetATodoByID() {
        String taskID = "650b4f02931c090014267ded";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE1MDM1MH0.Rvvp285Qpoe7P7NDovREEW5xQ5nsbxsxydrVtpYg7OM";

        Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .get("/api/v1/tasks/" + taskID)
                .then()
                .log().all().extract().response();

        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("item"), equalTo("Learn Appium"));
        assertThat(response.path("isCompleted"), equalTo(false));


    }

    @Test
    public void shouldBeAbleToDeleteATodo() {
        String taskID = "650b513f931c090014267e0d";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE1MDM1MH0.Rvvp285Qpoe7P7NDovREEW5xQ5nsbxsxydrVtpYg7OM";

        Response response = given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .delete("/api/v1/tasks/" + taskID)
                .then()
                .log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("item"), equalTo("Learn Appium"));
        assertThat(response.path("isCompleted"), equalTo(false));

    }
}
