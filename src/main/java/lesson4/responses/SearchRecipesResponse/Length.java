
package lesson4.responses.SearchRecipesResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "number",
    "unit"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Length {

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("unit")
    private String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
