package reqresApiVersion2.Specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

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

    public static ResponseSpecification responseSpecificationStatusCode(Integer code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }


    public static void installSpecification(RequestSpecification request,
                                            ResponseSpecification response,
                                            ResponseSpecification responseCode) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }
}
