package BackendTest.Steps;

import BackendTest.Apis.TodoApi;
import BackendTest.Models.Todo;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

import static BackendTest.Apis.TodoApi.addTodo;

public class TodoSteps {

    public static Todo generateTodo()
    {
        Faker faker=new Faker();
        String item=faker.book().title();
        boolean isCompleted=false;
        return new Todo(isCompleted,item);
    }

    public static String getTodoID(Todo todo,String token){

        Response response=TodoApi.addTodo(token,todo);
       return response.path("_id");
    }
}
