package reqresApiVersion1;

import reqresApiVersion1.Specifications.Specifications;
import reqresApiVersion1.pojo.request.Login;
import reqresApiVersion1.pojo.request.Update;
import reqresApiVersion1.pojo.response.*;
import reqresApiVersion1.pojo.request.User;
import reqresApiVersion1.pojo.request.UserData;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;
import reqresApiVersion1.pojo.service.Request;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;



public class RunTest {
    public final static String baseUrl = "https://reqres.in";


    @Test
    public void listUsers() {
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());
        List<UserData> users = RestAssured
                .given()
                    .get("/api/users?page=2")
                .then()
                    .extract().body().jsonPath().getList("data",UserData.class);

        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());

        for(int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    public void singleUser() {
        String path = "https://reqres.in/api/users/2";

        SingleUser expectedUser = new SingleUser(2, "janet.weaver@reqres.in",
                "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg" );

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());

        SingleUser infoAboutUser = RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getObject("data", SingleUser.class);

        Assert.assertEquals(expectedUser.getId(), infoAboutUser.getId());
        Assert.assertEquals(expectedUser.getEmail(), infoAboutUser.getEmail());
        Assert.assertEquals(expectedUser.getFirstName(), infoAboutUser.getFirstName());
        Assert.assertEquals(expectedUser.getLastName(), infoAboutUser.getLastName());
        Assert.assertEquals(expectedUser.getAvatar(), infoAboutUser.getAvatar());
    }

    @Test
    public void singleUserNotFound() {
        String path = "/api/users/23";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus404Error());

        Empty checkEmptyResponse = RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().as(Empty.class);
        Assert.assertNotNull(checkEmptyResponse);
    }

    @Test
    public void listResource() {
        String path = "/api/unknown";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());
        List<ListResource> list = given()
                .when()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getList("data", ListResource.class);

        List<Integer> listOfYears = list.stream().map(ListResource::getYear).collect(Collectors.toList());
        System.out.println("This is my years WITHOUT sorted method: " + listOfYears);

        List<Integer>  sortedOfYears = listOfYears.stream().sorted().collect(Collectors.toList());
        System.out.println("This is my years WITH sorted method: " + sortedOfYears);

        Assert.assertEquals(sortedOfYears, listOfYears);

    }

    @Test
    public void singleResource() {
        String path = "/api/unknown/2";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());

        SingleResource expectedResult = new SingleResource(2, "fuchsia rose", 2001,
                "#C74375", "17-2031");

        SingleResource singleResource = RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getObject("data", SingleResource.class);

        Assert.assertEquals(expectedResult.getId(), singleResource.getId());
        Assert.assertEquals(expectedResult.getName(), singleResource.getName());
        Assert.assertEquals(expectedResult.getYear(), singleResource.getYear());
        Assert.assertEquals(expectedResult.getColor(), singleResource.getColor());
        Assert.assertEquals(expectedResult.getPantoneValue(), singleResource.getPantoneValue());

    }

    @Test
    public void singleResourceNotFound() {
        String path = "/api/unknown/23";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus404Error());

        Empty checkEmptyResponse = RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().as(Empty.class);
        Assert.assertNotNull(checkEmptyResponse);
    }

    @Test
    public void createUser() {
        String path = "/api/users";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus201Create());

        Update bodyForRequest = new Update("morpheus", "leader");

        Create createUser = RestAssured
                .given()
                    .body(bodyForRequest)
                .when()
                    .post(path)
                .then()
                    .extract().as(Create.class);
        Assert.assertEquals(bodyForRequest.getName(), createUser.getName());
        Assert.assertEquals(bodyForRequest.getJob(), createUser.getJob());
    }

    @Test
    public void updateUserPut() {

        String path = "/api/users/2";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());

        Update bodyUpdate = new Update("morpheus","zion resident");

        ResponseUpdate updateUser = RestAssured
                .given()
                    .body(bodyUpdate)
                .when()
                    .put(path)
                .then()
                    .extract().as(ResponseUpdate.class);

        String regex = "(.{5})$";

        String currentTime = Clock.systemUTC().instant().toString();
        System.out.println(currentTime);

        String currentTimeWithRegex = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        System.out.println(currentTimeWithRegex);

        Assert.assertEquals(currentTimeWithRegex, updateUser.getUpdatedAt().replaceAll(regex, ""));
    }

    @Test
    public void updateUserPatch() {

        String path = "/api/users/2";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());

        Update bodyUpdate = new Update("morpheus","zion resident");

        ResponseUpdate updateUser = RestAssured
                .given()
                    .body(bodyUpdate)
                .when()
                    .patch(path)
                .then()
                    .extract().as(ResponseUpdate.class);

        String regex = "(.{6})$";

        String currentTime = Clock.systemUTC().instant().toString();
        System.out.println(currentTime);

        String currentTimeWithRegex = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        System.out.println(currentTimeWithRegex);

        Assert.assertEquals(currentTimeWithRegex, updateUser.getUpdatedAt().replaceAll(regex, ""));
    }


    @Test
    public void deleteUser() {
        String path = "/api/users/2";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus204());
        RestAssured
                .given()
                .when()
                    .delete(path)
                .then();
    }


    @Test
    public void registerSuccessful() {
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        User user = new User("eve.holt@reqres.in","pistol");

        SuccessRegistrationNewUser successUser = RestAssured
                .given()
                    .body(user)
                .when()
                    .post("/api/register")
                .then()
                    .extract().as(SuccessRegistrationNewUser.class);

        Assert.assertNotNull(successUser.getId());
        Assert.assertNotNull(successUser.getToken());

        Assert.assertEquals(expectedId, successUser.getId());
        Assert.assertEquals(expectedToken, successUser.getToken());
    }

    @Test
    public void registerUnsuccessful() {
        String path = "/api/register";
        String expectedResult = "Missing password";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus400Error());
        User user = new User("sydney@fife","");
        UnSuccessRegistrationNewUser unSuccessUser = RestAssured
                .given()
                    .body(user)
                .when()
                    .post(path)
                .then()
                    .extract().as(UnSuccessRegistrationNewUser.class);
        Assert.assertEquals(expectedResult, unSuccessUser.getError());
    }

    @Test
    public void loginSuccessful() {
        String path = "/api/login";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());

//        Login login = new Login("eve.holt@reqres.in", "cityslicka");

        Login login = Login.builder().email("eve.holt@reqres.in").password("cityslicka").build();
//        обратить внимание


        String expectedToken = "QpwL5tke4Pnpja7X4";

        Token token = RestAssured
                .given()
                    .body(login)
                .when()
                    .post(path)
                .then()
                    .extract().as(Token.class);
        Assert.assertEquals(expectedToken, token.getToken());
    }

    // ===============================================

    @Test
    public void loginSuccessfulTest() {
        String path = "/api/login";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                Specifications.responseSpecification(),
                Specifications.responseSpecificationStatus200OK());

//        Login login = new Login("eve.holt@reqres.in", "cityslicka");

        Login login = Login.builder().email("eve.holt@reqres.in").password("cityslicka").build();
//        обратить внимание


        String expectedToken = "QpwL5tke4Pnpja7X4";

       Token token =  (Token) Request.login("eve.holt@reqres.in", "cityslicka", 200, Token.class);

        Assert.assertEquals(expectedToken, token.getToken());
    }

    //==================================================

    @Test
    public void loginUnsuccessful() {
        String path = "/api/login";
        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus400Error());

        Login login = new Login("eve.holt@reqres.in", "");
        String expectedMessage = "Missing password";

        ErrorMessage messageResponse = RestAssured
                .given()
                    .body(login)
                .when()
                    .post(path)
                .then()
                    .extract().as(ErrorMessage.class);
        Assert.assertEquals(expectedMessage,messageResponse.getError() );

    }

    @Test
    public void delayedResponse() {
        String path = "/api/users?delay=3";

        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
                                            Specifications.responseSpecification(),
                                            Specifications.responseSpecificationStatus200OK());
        List<UserData> userPerPage = RestAssured
                .given()
                .when()
                    .get(path)
                .then()
                    .extract().body().jsonPath().getList("data", UserData.class);

        List<Integer> listOfId = userPerPage.stream().map(UserData::getId).collect(Collectors.toList());
        List<Integer> sortedListOfId = listOfId.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedListOfId, listOfId);

    }















}

