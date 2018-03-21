import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

import static org.hamcrest.Matchers.equalTo;


public class BaseTest {

    private static final String TEST_SITE = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/";
    private static final String CURRENT_CUSTOMER_URI = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/2";
    private static final String xml = "<CUSTOMER> \n" +
            "<ID>217365</ID>\n" +
            "<FIRSTNAME>VALERA    VALERA</FIRSTNAME>\n" +
            "<LASTNAME>Clancy</LASTNAME>\n" +
            "<STREET>18 Seventh Av.</STREET>\n" +
            "<CITY>Seattle</CITY>\n" +
            "</CUSTOMER>";

    @Test (priority = 1)
    public void getAllCustomers(){
        RestAssured.get(TEST_SITE).then().statusCode(200);
        System.out.println("Get All Customers");
    }

    @Test (priority = 2)
    public void getCustomer (){
        RestAssured.get(CURRENT_CUSTOMER_URI)
                .then().statusCode(200)
                .and().assertThat().body(" CUSTOMER.LASTNAME",equalTo("Miller"));
        System.out.println("getCustomerTest");
    }

    @Test
    public void createUser (){
        RestAssured.given()
                .contentType(ContentType.XML)
                .and().body(xml)
                .when().post(TEST_SITE)
                .then().statusCode(201);
        System.out.println("createUser");
    }

}
