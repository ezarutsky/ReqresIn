package Test;

import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TrueConfig {
   public String baseUrl;

    public void setUp() {

        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream("src/test/java/Test/Properties/config.properties");
            prop.load(fileInputStream);
         baseUrl = prop.getProperty("baseUrl");
//        String loginToSite = prop.getProperty("login");
//        String passwordToSite = prop.getProperty("password");

            //печатаем полученные данные в консоль
//        System.out.println(
//                "site: " + site
//                        + "\nloginToSite: " + loginToSite
//                        + "\npasswordToSite: " + passwordToSite
//        );

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + " не обнаружено");
            e.printStackTrace();
        }

//        не стоит добавлять в конфиг
        Specification.installSpecification(Specification.requestSpecification(baseUrl),
                Specification.responseSpecification(200));

    }
}
