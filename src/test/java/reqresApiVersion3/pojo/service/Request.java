package reqresApiVersion3.pojo.service;

import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;
import reqresApiVersion2.pojo.request.Login;
import reqresApiVersion2.pojo.request.Update;
import reqresApiVersion2.pojo.request.User;

public class Request {
    //pay attention
    private static final String PATH = "null";
    private static final String JSON_PATH = "null";

    public static Object login(String login, String pass, Class clazz) {
       String path = "/api/login";

       return RestAssured
                .given()
                    .body(new Login(login, pass))
                .when()
                    .post(path)
                .then()
                    .extract().as(clazz);

    }

    public static Object user(String path, String jsonPath, Class clazz){
        return RestAssured
                .given()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getList(jsonPath, clazz);
    }

    public  static Object infoAboutUser(String path, String jsonPath, Class clazz) {
        return RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getObject("data", clazz);
    }

    public static Object checkEmptyResponse(String path, Class clazz) {
         return RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().as(clazz);
    }

    public static  Object createUser(String name, String pass, String path, Class clazz){
        return RestAssured
                .given()
                    .body(new Update(name, pass))
                .when()
                .post(path)
                .then()
                .extract().as(clazz);
    }
        //так лучше не делатьт тк если будет много параметров, то так неудобно(сделать новый объект через билдер)
    public static Object updateUser(String name, String job, String path, Class clazz){
        return RestAssured
                .given()
                    .body(new Update(name, job))
                .when()
                    .put(path)
                .then()
                    .extract().as(clazz);
    }

    public static Object delete(String path){
        return RestAssured
                .given()
                .when()
                    .delete(path)
                .then();
    }

    public static Object successUser(String email, String pass, String path, Class clazz){
        return RestAssured
                .given()
                    .body(new User(email,pass))
                .when()
                .post(path)
                .then()
                    .extract().as(clazz);
    }

    public static Object userPerPage(String path, String jsonPath, Class clazz){
        return RestAssured
                .given()
                .when()
                .get(path)
                .then()
                .extract().body().jsonPath().getList(jsonPath, clazz);
    }
}
