package lesson4;

import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MealPlanTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(rest_assured.MealPlanTest.class);
    @RepeatedTest(3)
    @DisplayName("Add Meal Plan Template")
    public void aTest() throws IOException {
        Response response = given()
                .spec(getMealPlanTest())
                .body(getBodyFromFile("src/main/resources/MealPlanBodyReq.json"))
                .when()
                .post(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
        assertEquals(response.getBody().jsonPath().get("status").toString(), "success", "Add Plan - \"success\"");
    }
    @Test
    @DisplayName("Delete Meal Plan Template")
    public void bTest() {
        Response response = given()
                .spec(getMealPlanTest())
                .when()
                .get(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();
        List<HashMap<String, Object>> responseJson = response.getBody().jsonPath().getList("templates");
        if (!responseJson.isEmpty()) {
            for (int i = 0; i < responseJson.size(); i++) {
                prop.put("idPlan" + i, responseJson.get(i).get("id").toString());
                assertEquals("My new meal plan template", responseJson.get(i).get("name").toString());
                deletePlane(i);
            }
        } else  logger.info("Delete Meal Plan: No Meal Plan Template");
    }

    private void deletePlane(int numberPlane) {
        given()
                .spec(getMealPlanTest())
                .delete(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates/" + prop.get("idPlan" + numberPlane))
                .then()
                .spec(getResponseSpecification());
    }
}
