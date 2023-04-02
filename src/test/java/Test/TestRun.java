package Test;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reqresApiVersion2.pojo.request.UserData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestRun {
     String baseUrl;

     @Before
     public  void setUp() {
         TrueConfig config = new TrueConfig();
         config.setUp();

         baseUrl = config.baseUrl;
     }
//@Before
//public void setUp() {
//
//    FileInputStream fileInputStream;
//    Properties prop = new Properties();
//    try {
//        //обращаемся к файлу и получаем данные
//        fileInputStream = new FileInputStream("src/test/java/Test/Properties/config.properties");
//        prop.load(fileInputStream);
//
//         baseUrl = prop.getProperty("baseUrl");
////        String loginToSite = prop.getProperty("login");
////        String passwordToSite = prop.getProperty("password");
//
//        //печатаем полученные данные в консоль
////        System.out.println(
////                "site: " + site
////                        + "\nloginToSite: " + loginToSite
////                        + "\npasswordToSite: " + passwordToSite
////        );
//
//    } catch (IOException e) {
//        System.out.println("Ошибка в программе: файл " + " не обнаружено");
//        e.printStackTrace();
//    }
//
//    Specification.installSpecification(Specification.requestSpecification(baseUrl),
//            Specification.responseSpecification(200));
//
//}

    @Test
    public  void  getListUsers() {
//        String baseUrl = "https://reqres.in";
        String path = "/api/users?page=2";
        String jsonPath = "data";

//        Specification.installSpecification(Specification.requestSpecification(baseUrl),
//                                           Specification.responseSpecification(200));
        List<PojoData> listOfUsers =  (List<PojoData>) Request.user(path, jsonPath, PojoData.class);
//
        listOfUsers.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assert.assertTrue(listOfUsers.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
//
        List<String> avatars = listOfUsers.stream().map(pojoData -> pojoData.getAvatar()).collect(Collectors.toList());
        List<String> ids = listOfUsers.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

//        for (int i = 0; i < avatars.size(); i++) {
//            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
//        }


//        List<PojoData> listOfUsers =   RestAssured
//                .given()
////                .baseUri(baseUrl)
//                .get(path)
//                .then()
//                .extract().body().jsonPath().getList(jsonPath, PojoData.class);

        System.out.println(listOfUsers.size());

    }
}
