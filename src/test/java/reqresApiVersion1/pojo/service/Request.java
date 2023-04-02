package reqresApiVersion1.pojo.service;

import io.restassured.RestAssured;
import reqresApiVersion1.pojo.request.Login;

public class Request {
    //дженерики почитать
    // обратить внимание на статус коды
    public static Object login(String login, String pass, int status, Class clazz) {
       String path = "/api/login";

       return RestAssured
                .given()
                    .body(new Login(login, pass))
                .when()
                    .post(path)
                .then()
                    .statusCode(status)
                    .extract().as(clazz);

    }
}
