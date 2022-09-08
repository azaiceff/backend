
package lesson4.responses.SearchRecipesResponse;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "localizedName",
    "image"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipment {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("localizedName")
    private String localizedName;
    @JsonProperty("image")
    private String image;
    @JsonProperty("temperature")
    private Temperature temperature;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
