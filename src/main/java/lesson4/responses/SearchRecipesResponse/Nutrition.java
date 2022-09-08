
package lesson4.responses.SearchRecipesResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nutrients"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrition {

    @JsonProperty("nutrients")
    private List<Nutrient> nutrients = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
