package BackendTest.TestCases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    @Test
    public void shouldBeAbleToRegister()
    {

        String body ="{\n"+
                "   \"firstName\": \"Hatem\",\n" +
                "   \"lastName\" :  \"Hatamleh\",\n"+
                "   \"email\":  \"hatem9@example.com\",\n"+
                "   \"password\": \"AboodMilan123\"\n" +
                "}";



        given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/v1/users/register")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",equalTo("Hatem"));




    }
}
