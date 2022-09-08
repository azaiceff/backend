package lesson4;

import lesson4.responses.SearchRecipesResponse.Result;
import lesson4.responses.SearchRecipesResponse.SearchRecipesResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class SearchRecipesTest extends AbstractTest{
    private static final Logger logger = LoggerFactory.getLogger(rest_assured.SearchRecipesTest.class);

    @Test
    public void minCholesterolMaxVitaminB12minFolateTest() {
        prop.put("number", Integer.toString(random.nextInt(10) + 3));
        prop.put("offset", Integer.toString(random.nextInt(1000) + 1));
        prop.put("minCholesterol", "1");
        prop.put("maxVitaminB12", "2.0");
        prop.put("minFolate", "100");
        SearchRecipesResponse response = given()
                .spec(getRequestSpecification())
                .queryParam("number", prop.get("number"))
                .queryParam("offset", prop.get("offset"))
                .queryParam("minCholesterol", prop.get("minCholesterol"))
                .queryParam("maxVitaminB12", prop.get("maxVitaminB12"))
                .queryParam("minFolate", prop.get("minFolate"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        int sizeResponse = getResults(response).size();
        if(sizeResponse > 0){
            logger.info("minCholesterolMaxVitaminB12minFolateTest: Всего найдено " + sizeResponse + " рецептов");
            assertEquals(response.getNumber(), sizeResponse, "Number eql leng");
            for (int i = 0; i < sizeResponse; i++) {
                String name = response.getResults().get(i).getNutrition().getNutrients().get(0).getName();
                assertEquals(name, "Cholesterol", "Проверка названия Cholesterol");
                logger.info((i + 1) + ": Проверка название Cholesterol - OK");
                Double amount = response.getResults().get(i).getNutrition().getNutrients().get(1).getAmount();
                Double maxVitaminB12 = Double.parseDouble((String) prop.get("maxVitaminB12"));
                assertTrue(amount < maxVitaminB12, "Проверка содержания Витамина B12 (amount < maxVitaminB12)");
                logger.info((i + 1) + ": Проверка содержания Витамина B12 (amount < maxVitaminB12) - OK");
                String unit = response.getResults().get(i).getNutrition().getNutrients().get(2).getUnit();
                assertEquals(unit, "µg", "Проверяем единицу измерения 'µg'");
                logger.info((i + 1) + ": Проверяем единицу измерения 'µg' - OK");
            }
        }else logger.info("minCholesterolMaxVitaminB12minFolateTest: No test - рецептов не найдено");
    }
    @Test
    public void titleMatchTest(){
        prop.put("number", Integer.toString(random.nextInt(30) + 3));
        SearchRecipesResponse response = given()
                .spec(getRequestSpecification())
                .queryParam("number", prop.get("number"))
                .queryParam("titleMatch", propGlobal.get("titleMatch"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        List<Result> resultsJson = getResults(response);
        assertTrue(response.getNumber() >= resultsJson.size(), "Number >= length");
        resultsJson.forEach(o -> assertTrue(o.getTitle().toLowerCase().contains((CharSequence) propGlobal.get("titleMatch"))));
    }
    @Test
    public void dietTest(){
        prop.put("number", Integer.toString(random.nextInt(69) + 3));
        SearchRecipesResponse response = given()
                .spec(getRequestSpecification())
                .queryParam("number", prop.get("number"))
                .queryParam("diet", "Ketogenic")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        List<Result> resultsJson = getResults(response);
        int sizeResponse = resultsJson.size();
        if(sizeResponse > 0) {
            logger.info("dietTest: Всего найдено " + sizeResponse + " рецептов");
            assertEquals(response.getNumber(), sizeResponse, "Number eql leng");
        }else logger.info("dietTest: No test - рецептов не найдено");
    }
    @Test
    public void maxCaffeineNoSearchTest(){
        prop.put("number", Integer.toString(random.nextInt(20) + 3));
        prop.put("offset", Integer.toString(random.nextInt(3000) + 1));
        SearchRecipesResponse response = given()
                .spec(getRequestSpecification())
                .queryParam("number", prop.get("number"))
                .queryParam("maxCaffeine", "-2")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        List<Result> resultsJson = getResults(response);
        int sizeResponse = resultsJson.size();
        assertEquals(0, sizeResponse, "No Search Recipes");
        assertEquals(response.getTotalResults(), 0, "No totalResults");
    }
    @Test
    public void addRecipeInformationTest() {
        prop.put("number", Integer.toString(random.nextInt(30) + 3));
        SearchRecipesResponse response = given()
                .spec(getRequestSpecification())
                .queryParam("number", prop.get("number"))
                .queryParam("titleMatch", propGlobal.get("titleMatch"))
                .queryParam("ignorePantry", "True")
                .queryParam("maxReadyTime", "40")
                .queryParam("addRecipeInformation", "True")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        List<Result> resultsJson = getResults(response);
        resultsJson.forEach(o -> {
            String title = o.getTitle();
            logger.info("addRecipeInformationTest - Title: " + title);
            assertTrue(title.toLowerCase().contains((CharSequence) propGlobal.get("titleMatch")));
        });
    }

    private List<Result> getResults(SearchRecipesResponse response) {
        assertNotNull(response.getResults());
        List<Result> resultsJson = response.getResults();
        int numberFromResponse = response.getNumber();
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        assertTrue(numberFromResponse >= resultsJson.size(), "Number >= length");
        return resultsJson;
    }
}
