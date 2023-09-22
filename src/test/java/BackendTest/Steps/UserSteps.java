package BackendTest.Steps;

import BackendTest.Apis.UserApi;
import BackendTest.Models.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class UserSteps {

    public static User generateUser()
    {
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String email=faker.internet().emailAddress();
        String password="AbdallahSaleh";

        User user=new User(firstName,lastName,email,password);
        return user;

    }

    //The Target from this method return already register user
    public static User getRegisteredUser(){
        User user=generateUser();
        UserApi.register(user);
        return user;
    }

    public static String getUserToken()
    {
        User user=generateUser();
        Response response=UserApi.register(user);
        return response.body().path("access_token");

    }

}
