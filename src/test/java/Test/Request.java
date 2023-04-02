package Test;

import io.restassured.RestAssured;

public class Request {
    public static Object user(String path, String jsonPath, Class clazz) {
        return  RestAssured
                .given()
                    .get(path)
                .then()
                .extract().body().jsonPath().getList(jsonPath, clazz);
    }
}
