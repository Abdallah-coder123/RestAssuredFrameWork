package BackendTest.TestCases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TodoTest {



    String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE0NzkxNn0.34KYTG8yNaKhdsnGx9vsA-GgwlhAWjtUOjf_uedLRW0";
@Test
    public void shouldBeAbleToAddTodo()
{
    String body ="{\n"+
            "    \"isCompleted\":false,\n"+
            "     \"item\": \"Learn Appium\"\n" +
            "}";

    given().baseUri("https://qacart-todo.herokuapp.com")
            .body(body)
            .contentType(ContentType.JSON)
            .auth().oauth2(token)
            .when()
            .post("/api/v1/tasks")
            .then()
            .log().all()
            .assertThat().statusCode(201)
            .assertThat().body("item",equalTo("Learn Appium")).
            assertThat().body("isCompleted",equalTo(false));
}
    @Test
    public void shouldNoBeAbleToAddTodoIfIsCompletingIsMessing()
    {

        String body ="{\n"+
                "     \"item\": \"Learn Appium\"\n" +
                "}";
        given().baseUri("https://qacart-todo.herokuapp.com")
                .body(body)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .post("/api/v1/tasks")
                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("\"isCompleted\" is required"));
    }
    @Test
    public void shouldBeAbleToGetATodoByID()
    {
        String taskID = "6509f29d19ef7b0014480b47";
       String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE1MDM1MH0.Rvvp285Qpoe7P7NDovREEW5xQ5nsbxsxydrVtpYg7OM";

        given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .get("/api/v1/tasks/"+taskID)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("Learn Appium")).
                assertThat().body("isCompleted",equalTo(false));

    }

    @Test
    public void shouldBeAbleToDeleteATodo()
    {
        String taskID = "6509f29d19ef7b0014480b47";
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1MDhiMTZhMDNiM2ViMDAxNGIxNTRkMyIsImZpcnN0TmFtZSI6IkhhdGVtIiwibGFzdE5hbWUiOiJIYXRhbWxlaCIsImlhdCI6MTY5NTE1MDM1MH0.Rvvp285Qpoe7P7NDovREEW5xQ5nsbxsxydrVtpYg7OM";

        given().baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .when()
                .delete("/api/v1/tasks/"+taskID)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("item",equalTo("Learn Appium")).
                assertThat().body("isCompleted",equalTo(false));

    }
}
