package BackendTest.Base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Specs {

    public static String getEnv(){
        //this line wait type of Env from terminal , when not write type of env this line go to production
        String env=System.getProperty("env","PRODUCTION");
        switch (env){
            case"PRODUCTION":
                baseURI="https://qacart-todo.herokuapp.com";
                        break;
            case "LOCAL":
                baseURI="http://localhost:8080";
                break;

            default:
                throw new RuntimeException("Environment is not supported");
        }
        return baseURI;
    }

    public static RequestSpecification getRequestSpec()
    {
        RequestSpecification reqSpec = given()
                .baseUri(getEnv())
                .contentType(ContentType.JSON).log().all();
        return reqSpec;

    }

}
