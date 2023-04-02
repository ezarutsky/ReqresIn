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
public class ListResource {
    public Integer id;
    public String name;
    public Integer year;
    public String color;
    @JsonProperty("pantone_value")
    public String pantoneValue;

}

