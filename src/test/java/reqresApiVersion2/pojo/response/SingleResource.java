package reqresApiVersion2.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SingleResource {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("year")
    public Integer year;
    @JsonProperty("color")
    public String color;
    @JsonProperty("pantone_value")
    public String pantoneValue;

}
