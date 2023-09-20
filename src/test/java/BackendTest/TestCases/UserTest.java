package BackendTest.TestCases;

import BackendTest.Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {

    @Test
    public void shouldBeAbleToRegister() {


        User user = new User("Murad", "ALDBOOR", "mfokklkm@gmail.com", "Abso8od555");


        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/register")
                .then()
                .log().all().extract().response();
//Desrializetion
        User returnedUser=response.body().as(User.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));


    }


    @Test
    public void shouldNotBeSameRegisterWithTheSameEmail() {
        User user = new User("Murad", "ALDBOOR", "muradaboodsklkm@gmail.com", "Abo8od555");

        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/register")
                .then()
                .log().all().extract().response();

        assertThat(response.statusCode(), equalTo(400));
        assertThat(response.path("message"), equalTo("Email is already exists in the Database"));


    }


    @Test
    public void shouldBeAbleToLogin() {

        User user = new User("muradaboodsklkm@gmail.com", "Abo8od555");


        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/login")
                .then()
                .log().all()
                .extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("firstName"), equalTo("Murad"));
        assertThat(response.path("access_token"), not(equalTo(null)));

    }

    @Test
    public void shouldNotBeAbleToLoginIfThePassworadIsNotCorrect() {

        User user = new User("muradaboodsklkm@gmail.com", "Abo8odd555");


        Response response = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/login")
                .then()
                .log().all().extract().response();

        assertThat(response.statusCode(), equalTo(401));
        assertThat(response.path("message"), equalTo("The email and password combination is not correct, please fill a correct email and password"));

    }

}





