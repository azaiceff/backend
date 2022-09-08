
package lesson4.responses.SearchRecipesResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "steps"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyzedInstruction {

    @JsonProperty("name")
    private String name;
    @JsonProperty("steps")
    private List<Step> steps = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
