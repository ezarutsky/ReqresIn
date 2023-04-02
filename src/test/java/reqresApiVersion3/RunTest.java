package reqresApiVersion3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reqresApiVersion2.pojo.response.*;
import reqresApiVersion2.pojo.service.Request;
import reqresApiVersion2.Specifications.Specifications;
import reqresApiVersion2.pojo.request.Update;
import reqresApiVersion2.pojo.request.UserData;


import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.baseURI;

public class RunTest {

    public class Config {

        @Before
        public  void  setUp() {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

            baseURI = "https://reqres.in";

            RestAssured.patch("/api/");

            RestAssured.requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(baseURI)
                    .setContentType((ContentType.JSON))
                    .log(LogDetail.ALL)
                    .build();

            RestAssured.responseSpecification = new ResponseSpecBuilder()
                    .log(LogDetail.ALL)
                    .expectContentType(ContentType.JSON)
                    .build();
        }
    }

    public final static String baseUrl = "https://reqres.in";
    //public final static String BASE_URL = "https://reqres.in";


//    for test

    @Test
    public void singleResource() {
        String path = "unknown/2";
        String jsonPath = "data";

//        Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                Specifications.responseSpecification(),
//                Specifications.responseSpecificationStatusCode(200));

        SingleResource expectedResult = new SingleResource(2, "fuchsia rose", 2001,
                "#C74375", "17-2031");

        SingleResource singleResource = (SingleResource) Request.infoAboutUser(path, jsonPath, SingleResource.class);

        Assert.assertEquals(expectedResult.getId(), singleResource.getId());
        Assert.assertEquals(expectedResult.getName(), singleResource.getName());
        Assert.assertEquals(expectedResult.getYear(), singleResource.getYear());
        Assert.assertEquals(expectedResult.getColor(), singleResource.getColor());
        Assert.assertEquals(expectedResult.getPantoneValue(), singleResource.getPantoneValue());

    }
//    ===================

//        @Test
//        public void listUsers() {
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            String path = "/api/users?page=2";
//            String jsonPath = "data";
//
//            List<UserData> users = (List<UserData>) Request.user(path, jsonPath, UserData.class);
//
//            users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
//            Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
//
//            List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
//            List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
//
//            for (int i = 0; i < avatars.size(); i++) {
//                Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
//            }
//        }
//
//        @Test
//        public void singleUser () {
//            String path = "https://reqres.in/api/users/2";
//            String jsonPath = "data";
//
//            SingleUser expectedUser = new SingleUser(2, "janet.weaver@reqres.in",
//                    "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg");
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            SingleUser infoAboutUser = (SingleUser) Request.infoAboutUser(path, jsonPath, SingleUser.class);
//
//            Assert.assertEquals(expectedUser.getEmail(), infoAboutUser.getEmail());
//            Assert.assertEquals(expectedUser.getFirstName(), infoAboutUser.getFirstName());
//            Assert.assertEquals(expectedUser.getLastName(), infoAboutUser.getLastName());
//            Assert.assertEquals(expectedUser.getAvatar(), infoAboutUser.getAvatar());
//        }
//
//        @Test
//        public void singleUserNotFound () {
//            String path = "/api/users/23";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(404));
//
//            Empty checkEmptyResponse = (Empty) Request.checkEmptyResponse(path, Empty.class);
//            Assert.assertNotNull(checkEmptyResponse);
//        }
//
//        @Test
//        public void listResource () {
//            String path = "/api/unknown";
//            String jsonPath = "data";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            List<ListResource> list = (List<ListResource>) Request.user(path, jsonPath, ListResource.class);
//
//            List<Integer> listOfYears = list.stream().map(ListResource::getYear).collect(Collectors.toList());
//            System.out.println("This is my years WITHOUT sorted method: " + listOfYears);
//
//            List<Integer> sortedOfYears = listOfYears.stream().sorted().collect(Collectors.toList());
//            System.out.println("This is my years WITH sorted method: " + sortedOfYears);
//
//            Assert.assertEquals(sortedOfYears, listOfYears);
//
//        }
//
//        @Test
//        public void singleResource () {
//            String path = "/api/unknown/2";
//            String jsonPath = "data";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            SingleResource expectedResult = new SingleResource(2, "fuchsia rose", 2001,
//                    "#C74375", "17-2031");
//
//            SingleResource singleResource = (SingleResource) Request.infoAboutUser(path, jsonPath, SingleResource.class);
//
//            Assert.assertEquals(expectedResult.getId(), singleResource.getId());
//            Assert.assertEquals(expectedResult.getName(), singleResource.getName());
//            Assert.assertEquals(expectedResult.getYear(), singleResource.getYear());
//            Assert.assertEquals(expectedResult.getColor(), singleResource.getColor());
//            Assert.assertEquals(expectedResult.getPantoneValue(), singleResource.getPantoneValue());
//
//        }
//
//        @Test
//        public void singleResourceNotFound () {
//            String path = "/api/unknown/23";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(404));
//
//            Empty checkEmptyResponse = (Empty) Request.checkEmptyResponse(path, Empty.class);
//            Assert.assertNotNull(checkEmptyResponse);
//        }
//
//        @Test
//        public void createUser () {
//            String path = "/api/users";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(201));
//
//            Update bodyForRequest = new Update("morpheus", "leader");
//
//
//            Create createUser = (Create) Request.createUser("morpheus", "leader", path, Create.class);
//
//            Assert.assertEquals(bodyForRequest.getName(), createUser.getName());
//            Assert.assertEquals(bodyForRequest.getJob(), createUser.getJob());
//        }
//
//        @Test
//        public void updateUserPut () {
//
//            String path = "/api/users/2";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            ResponseUpdate updateUser = (ResponseUpdate) Request.updateUser("morpheus", "zion resident", path, ResponseUpdate.class);
//
//            String regex = "(.{5})$";
//
//            String currentTime = Clock.systemUTC().instant().toString();
//            System.out.println(currentTime);
//
//            String currentTimeWithRegex = Clock.systemUTC().instant().toString().replaceAll(regex, "");
//            System.out.println(currentTimeWithRegex);
//
//            Assert.assertEquals(currentTimeWithRegex, updateUser.getUpdatedAt().replaceAll(regex, ""));
//        }
//
//        @Test
//        public void updateUserPatch () {
//            String path = "/api/users/2";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            ResponseUpdate updateUser = (ResponseUpdate) Request.updateUser("morpheus", "zion resident", path, ResponseUpdate.class);
//
//            String regex = "(.{6})$";
//
//            String currentTime = Clock.systemUTC().instant().toString();
//            System.out.println(currentTime);
//
//            String currentTimeWithRegex = Clock.systemUTC().instant().toString().replaceAll(regex, "");
//            System.out.println(currentTimeWithRegex);
//
//            Assert.assertEquals(currentTimeWithRegex, updateUser.getUpdatedAt().replaceAll(regex, ""));
//        }
//
//
//        @Test
//        public void deleteUser () {
//            String path = "/api/users/2";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(204));
//            Request.delete(path);
//        }
//
//
//        @Test
//        public void registerSuccessful () {
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//            Integer expectedId = 4;
//            String expectedToken = "QpwL5tke4Pnpja7X4";
//            String path = "/api/register";
//
//            SuccessRegistrationNewUser successUser = (SuccessRegistrationNewUser) Request.successUser("eve.holt@reqres.in",
//                    "pistol", path, SuccessRegistrationNewUser.class);
//
//            Assert.assertNotNull(successUser.getId());
//            Assert.assertNotNull(successUser.getToken());
//
//            Assert.assertEquals(expectedId, successUser.getId());
//            Assert.assertEquals(expectedToken, successUser.getToken());
//        }
//
//        @Test
//        public void registerUnsuccessful () {
//            String path = "/api/register";
//            String expectedResult = "Missing password";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(400));
//
//            UnSuccessRegistrationNewUser unSuccessUser = (UnSuccessRegistrationNewUser) Request.successUser("sydney@fife", "", path,
//                    UnSuccessRegistrationNewUser.class);
//
//            Assert.assertEquals(expectedResult, unSuccessUser.getError());
//        }
//
//        @Test
//        public void loginSuccessful () {
//            String path = "/api/login";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            String expectedToken = "QpwL5tke4Pnpja7X4";
//            Token token = (Token) Request.successUser("eve.holt@reqres.in", "cityslicka", path,
//                    Token.class);
//            Assert.assertEquals(expectedToken, token.getToken());
//        }
//
//        @Test
//        public void loginUnsuccessful () {
//            String path = "/api/login";
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(400));
//            String expectedMessage = "Missing password";
//
//            ErrorMessage messageResponse = (ErrorMessage) Request.successUser("eve.holt@reqres.in", "", path, ErrorMessage.class);
//            Assert.assertEquals(expectedMessage, messageResponse.getError());
//
//        }
//
//        @Test
//        public void delayedResponse () {
//            String path = "/api/users?delay=3";
//            String jsonPath = "data";
//
//            Specifications.installSpecification(Specifications.requestSpecification(baseUrl),
//                    Specifications.responseSpecification(),
//                    Specifications.responseSpecificationStatusCode(200));
//
//            List<UserData> userPerPage = (List<UserData>) Request.userPerPage(path, jsonPath, UserData.class);
//
//            List<Integer> listOfId = userPerPage.stream().map(UserData::getId).collect(Collectors.toList());
//            List<Integer> sortedListOfId = listOfId.stream().sorted().collect(Collectors.toList());
//
//            Assert.assertEquals(sortedListOfId, listOfId);
//
//        }
//
//
//    }
}

