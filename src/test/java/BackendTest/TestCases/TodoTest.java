package BackendTest.TestCases;

import BackendTest.Apis.TodoApi;
import BackendTest.Data.ErrorMessages;
import BackendTest.Models.Todo;
import BackendTest.Steps.TodoSteps;
import BackendTest.Steps.UserSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.not;

public class TodoTest {


    String token = UserSteps.getUserToken();

    @Test
    public void shouldBeAbleToAddTodo() {

        Todo todo = TodoSteps.generateTodo();
        String token = UserSteps.getUserToken();
        Response response = TodoApi.addTodo(token,todo);


        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));

        assertThat(returnedTodo.getIsCompleted(), equalTo(todo.getIsCompleted()));

    }

    @Test
    public void shouldNoBeAbleToAddTodoIfIsCompletingIsMessing() {


        Todo todo = new Todo("Learn Appium");

        String token = UserSteps.getUserToken();
        Response response=TodoApi.addTodo(token,todo);

            Error returnedError=response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED));

    }

    @Test
    public void shouldBeAbleToGetATodoByID() {


        String token = UserSteps.getUserToken();
        Todo todo=TodoSteps.generateTodo();
        String todoID=TodoSteps.getTodoID(todo,token);
        Response response=TodoApi.getTodo(todoID,token);
        Todo returnedTodo= response.body().as(Todo.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(), equalTo(false));


    }

    @Test
    public void shouldBeAbleToDeleteATodo() {

        String token = UserSteps.getUserToken();
        Todo todo=TodoSteps.generateTodo();
        String todoID=TodoSteps.getTodoID(todo,token);
       Response response=TodoApi.deleteTodo(todoID,token);
       Todo returnedTodo=response.body().as(Todo.class);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted() , equalTo(todo.getIsCompleted()));

    }
}
