package reqresApiVersion1.Specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {


    //оптимизировать спецификации, чтобы небыло много статус кодов

    public static RequestSpecification requestSpecification(String baseUrl) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType((ContentType.JSON))
                .log(LogDetail.ALL)
                .build();
    }

    public static  ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecificationStatus200OK() {
        return new ResponseSpecBuilder()
                .expectStatusCode((200))
                .build();
    }


    public static ResponseSpecification responseSpecificationStatus400Error() {
        return new ResponseSpecBuilder()
                .expectStatusCode((400))
                .build();
    }

    public static ResponseSpecification responseSpecificationStatus404Error() {
        return new ResponseSpecBuilder()
                .expectStatusCode((404))
                .build();
    }

    public static ResponseSpecification responseSpecificationStatus204() {
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
                .build();

    }

    public static ResponseSpecification responseSpecificationStatus201Create() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static void installSpecification(RequestSpecification request,
                                            ResponseSpecification response,
                                            ResponseSpecification responseCode) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }
}
