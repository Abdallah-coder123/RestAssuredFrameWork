package BackendTest.TestCases;

import BackendTest.Models.User;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {

    @Test
    public void shouldBeAbleToRegister()
    {


        User user=new User("Murad","ALDBOOR","muradaboodsklkm@gmail.com","Abo8od555");




        given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/register")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body("firstName",equalTo("Murad"));
    }


    @Test
    public void shouldNotBeSameRegisterWithTheSameEmail(){
        User user=new User("Murad","ALDBOOR","muradaboodsklkm@gmail.com","Abo8od555");

        given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/register")
                .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("Email is already exists in the Database"));
    }



    @Test
    public void shouldBeAbleToLogin()
    {

        User user=new User("muradaboodsklkm@gmail.com","Abo8od555");


        given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName",equalTo("Murad"))
                .assertThat().body("access_token",not(equalTo(null)));

    }
    @Test
    public void shouldNotBeAbleToLoginIfThePassworadIsNotCorrect(){

        User user=new User("muradaboodsklkm@gmail.com","Abo8odd555");


        given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post("/api/v1/users/login")
                .then()
                .log().all()
                .assertThat().statusCode(401)
                .assertThat().body("message",equalTo("The email and password combination is not correct, please fill a correct email and password"));


    }

    }





