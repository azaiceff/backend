
package lesson4.responses.SearchRecipesResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "number",
    "step",
    "ingredients",
    "equipment",
    "length"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("step")
    private String step;
    @JsonProperty("ingredients")
    private List<Ingredient> ingredients = null;
    @JsonProperty("equipment")
    private List<Equipment> equipment = null;
    @JsonProperty("length")
    private Length length;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
