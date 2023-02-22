package reqresApi;

import reqresApi.pojo.request.Login;
import reqresApi.pojo.request.Update;
import reqresApi.pojo.response.*;
import reqresApi.pojo.request.User;
import reqresApi.pojo.request.UserData;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RunTest {
    private final static String baseUrl = "https://reqres.in";


    @Test
    public void listUsers() {
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                                            Specifications.responseSpecificationStatus200OK());
        List<UserData> users = given()
                .get("/api/users?page=2")
                .then().log().all()
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

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());

        SingleUser infoAboutUser = given()
                .when()
                .get(path)
                .then().log().all()
                .extract().body().jsonPath().getObject("data", SingleUser.class);

        Assert.assertEquals(expectedUser.getId(), infoAboutUser.getId());
        Assert.assertEquals(expectedUser.getEmail(), infoAboutUser.getEmail());
        Assert.assertEquals(expectedUser.getFirst_name(), infoAboutUser.getFirst_name());
        Assert.assertEquals(expectedUser.getLast_name(), infoAboutUser.getLast_name());
        Assert.assertEquals(expectedUser.getAvatar(), infoAboutUser.getAvatar());
    }

    @Test
    public void singleUserNotFound() {
        String path = "/api/users/23";

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus404Error());

        given()
                .when()
                .get(path)
                .then().log().all();
    }

    @Test
    public void listResource() {
        String path = "/api/unknown";

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());
        List<ListResource> list = given()
                .when()
                .get(path)
                .then().log().all()
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

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());

        SingleResource expectedResult = new SingleResource(2, "fuchsia rose", 2001,
                "#C74375", "17-2031");

        SingleResource singleResource = given()
                .when()
                .get(path)
                .then().log().all()
                .extract().body().jsonPath().getObject("data", SingleResource.class);

        Assert.assertEquals(expectedResult.getId(), singleResource.getId());
        Assert.assertEquals(expectedResult.getName(), singleResource.getName());
        Assert.assertEquals(expectedResult.getYear(), singleResource.getYear());
        Assert.assertEquals(expectedResult.getColor(), singleResource.getColor());
        Assert.assertEquals(expectedResult.getPantone_value(), singleResource.getPantone_value());

    }

    @Test
    public void singleResourceNotFound() {
        String path = "/api/unknown/23";

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus404Error());

        given()
                .when()
                .get(path)
                .then().log().all();
    }

    @Test
    public void createUser() {
        String path = "/api/users";

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus201Create());

        Update bodyForRequest = new Update("morpheus", "leader");

        Create createUser = RestAssured
                .given()
                    .log().all()
                .body(bodyForRequest)
                .when()
                    .post(path)
                .then()
                    .log().all()
                .extract().as(Create.class);
        Assert.assertEquals(bodyForRequest.getName(), createUser.getName());
        Assert.assertEquals(bodyForRequest.getJob(), createUser.getJob());
    }

    @Test
    public void updateUserPut() {

        String path = "/api/users/2";
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());

        Update bodyUpdate = new Update("morpheus","zion resident");

        ResponseUpdate updateUser = given()
                .body(bodyUpdate)
                .when()
                .put(path)
                .then().log().all()
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
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());

        Update bodyUpdate = new Update("morpheus","zion resident");

        ResponseUpdate updateUser = given()
                .body(bodyUpdate)
                .when()
                .patch(path)
                .then().log().all()
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
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus204());
        given()
                .when()
                .delete(path)
                .then().log().all();
    }

    @Test
    public void registerSuccessful() {
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                                            Specifications.responseSpecificationStatus200OK());
        Integer expectedId = 4;
        String expectedToken = "QpwL5tke4Pnpja7X4";

        User user = new User("eve.holt@reqres.in","pistol");

        SuccessRegistrationNewUser successUser = given()
                .body(user)
                .when()
                .post("/api/register")
                .then().log().all()
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
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                                            Specifications.responseSpecificationStatus400Error());
        User user = new User("sydney@fife","");
        UnSuccessRegistrationNewUser unSuccessUser = RestAssured
                .given()
                    .body(user)
                .when()
                    .post(path)
                .then()
                    .log().all()
                    .extract().as(UnSuccessRegistrationNewUser.class);
        Assert.assertEquals(expectedResult, unSuccessUser.getError());
    }

    @Test
    public void loginSuccessful() {
        String path = "/api/login";
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus200OK());

        Login login = new Login("eve.holt@reqres.in", "cityslicka");
        String expectedToken = "QpwL5tke4Pnpja7X4";

        Token token = RestAssured
                .given()
                    .body(login)
                .when()
                    .post(path)
                .then()
                    .log().all()
                    .extract().as(Token.class);
        Assert.assertEquals(expectedToken, token.getToken());
    }

    @Test
    public void loginUnsuccessful() {
        String path = "/api/login";
        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                Specifications.responseSpecificationStatus400Error());

        Login login = new Login("eve.holt@reqres.in", "");
        String expectedMessage = "Missing password";

        ErrorMessage  messageResponse = RestAssured
                .given()
                    .log().all()
                    .body(login)
                .when()
                    .post(path)
                .then()
                    .log().all()
                    .extract().as(ErrorMessage.class);
        Assert.assertEquals(expectedMessage,messageResponse.getError() );

    }

    @Test
    public void delayedResponse() {
        String path = "/api/users?delay=3";

        Specifications.installSpecification(Specifications.requestSpecificationBaseUrl(baseUrl),
                                            Specifications.responseSpecificationStatus200OK());
        List<UserData> userPerPage = RestAssured
                .given()
                    .log().all()
                .when()
                    .get(path)
                .then()
                    .log().all()
                    .extract().body().jsonPath().getList("data", UserData.class);

        List<Integer> listOfId = userPerPage.stream().map(UserData::getId).collect(Collectors.toList());
        List<Integer> sortedListOfId = listOfId.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(sortedListOfId, listOfId);

    }















}

