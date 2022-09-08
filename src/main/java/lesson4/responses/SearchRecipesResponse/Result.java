
package lesson4.responses.SearchRecipesResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vegetarian",
    "vegan",
    "glutenFree",
    "dairyFree",
    "veryHealthy",
    "cheap",
    "veryPopular",
    "sustainable",
    "lowFodmap",
    "weightWatcherSmartPoints",
    "gaps",
    "preparationMinutes",
    "cookingMinutes",
    "aggregateLikes",
    "healthScore",
    "creditsText",
    "license",
    "sourceName",
    "pricePerServing",
    "id",
    "title",
    "readyInMinutes",
    "servings",
    "sourceUrl",
    "image",
    "imageType",
    "summary",
    "cuisines",
    "dishTypes",
    "diets",
    "occasions",
    "analyzedInstructions",
    "spoonacularSourceUrl",
    "nutrition"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("vegetarian")
    private Boolean vegetarian;
    @JsonProperty("vegan")
    private Boolean vegan;
    @JsonProperty("glutenFree")
    private Boolean glutenFree;
    @JsonProperty("dairyFree")
    private Boolean dairyFree;
    @JsonProperty("veryHealthy")
    private Boolean veryHealthy;
    @JsonProperty("cheap")
    private Boolean cheap;
    @JsonProperty("veryPopular")
    private Boolean veryPopular;
    @JsonProperty("sustainable")
    private Boolean sustainable;
    @JsonProperty("lowFodmap")
    private Boolean lowFodmap;
    @JsonProperty("weightWatcherSmartPoints")
    private Integer weightWatcherSmartPoints;
    @JsonProperty("gaps")
    private String gaps;
    @JsonProperty("preparationMinutes")
    private Integer preparationMinutes;
    @JsonProperty("cookingMinutes")
    private Integer cookingMinutes;
    @JsonProperty("aggregateLikes")
    private Integer aggregateLikes;
    @JsonProperty("healthScore")
    private Integer healthScore;
    @JsonProperty("creditsText")
    private String creditsText;
    @JsonProperty("license")
    private String license;
    @JsonProperty("sourceName")
    private String sourceName;
    @JsonProperty("pricePerServing")
    private Double pricePerServing;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("readyInMinutes")
    private Integer readyInMinutes;
    @JsonProperty("servings")
    private Integer servings;
    @JsonProperty("sourceUrl")
    private String sourceUrl;
    @JsonProperty("image")
    private String image;
    @JsonProperty("imageType")
    private String imageType;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("cuisines")
    private List<String> cuisines = null;
    @JsonProperty("dishTypes")
    private List<String> dishTypes = null;
    @JsonProperty("diets")
    private List<String> diets = null;
    @JsonProperty("occasions")
    private List<String> occasions = null;
    @JsonProperty("analyzedInstructions")
    private List<AnalyzedInstruction> analyzedInstructions = null;
    @JsonProperty("spoonacularSourceUrl")
    private String spoonacularSourceUrl;
    @JsonProperty("nutrition")
    private Nutrition nutrition;
    @JsonProperty("author")
    private String author;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
