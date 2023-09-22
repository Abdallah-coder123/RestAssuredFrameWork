package BackendTest.TestCases;

import BackendTest.Apis.UserApi;
import BackendTest.Data.ErrorMessages;
import BackendTest.Models.User;
import BackendTest.Steps.UserSteps;
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
        User user = UserSteps.generateUser();
       Response response= UserApi.register(user);
//Desrializetion
        User returnedUser=response.body().as(User.class);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));


    }


    @Test
    public void shouldNotBeSameRegisterWithTheSameEmail() {
        User user=UserSteps.getRegisteredUser();
        //obj user already exist in Database
        Response response=UserApi.register(user);
        Error returnedError=response.body().as(Error.class);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.EMAIL_IS_ALREADY_REGISTERED));


    }


    @Test
    public void shouldBeAbleToLogin() {

        User user = UserSteps.getRegisteredUser();
        User loginData=new User(user.getEmail(),user.getPassword());

     Response response=UserApi.login(loginData);

//returnedUser take data from response (API/Server)
        User returnedUser=response.body().as(User.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));
        assertThat(returnedUser.getAccessToken(), not(equalTo(null)));

    }

    @Test
    public void shouldNotBeAbleToLoginIfThePassworadIsNotCorrect() {

        User user = UserSteps.getRegisteredUser();
        User loginData=new User(user.getEmail(),"wrongPassword");

        Response response=UserApi.login(loginData);

        Error returnedError=response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(401));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.EMAIL_OR_PASSWORD_WRONG));

    }

}





