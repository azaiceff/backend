package lesson4;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lesson4.request.MealPlan;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.util.Properties;
import java.util.Random;

public class AbstractTest {
    static Properties propGlobal = new Properties();
    static Properties prop = new Properties();
    static Random random = new Random();
    private static InputStream configFileInGlobal;
    private static OutputStream configFileOut;
    private static String apiKey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        configFileInGlobal = new FileInputStream("src/main/resources/my.properties");
        InputStream configFileTestIn = new FileInputStream("src/main/resources/myTest.properties");
        propGlobal.load(configFileInGlobal);
        prop.load(configFileTestIn);
        apiKey =  propGlobal.getProperty("apiKey");
        baseUrl= propGlobal.getProperty("base_url");

    }

    @AfterAll
    static void afterTest() throws IOException {
        configFileOut = new FileOutputStream("src/main/resources/myTest.properties");
        prop.save(configFileOut, "save");
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
    RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
    ResponseSpecification getResponseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }
    RequestSpecification getRequestSpecificationClassifyCuisine(String language, String title){
        return new RequestSpecBuilder()
                .addQueryParam("apiKey", getApiKey())
                .addQueryParam("language", language)
                .setContentType("application/x-www-form-urlencoded")
                .addFormParam("title", title)
                .build();
    }
    RequestSpecification getMealPlanTest(){
        return new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("hash", propGlobal.get("hash"))
                .setContentType("application/x-www-form-urlencoded")
                .build();
    }
    String getBodyFromFile(String path) throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        MealPlan mealPlan = objectMapper.readValue(file, MealPlan.class);
        return objectMapper.writeValueAsString(mealPlan);
    }
}


