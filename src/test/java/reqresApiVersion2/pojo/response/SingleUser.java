package reqresApiVersion2.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SingleUser {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("email")
    public String email;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("avatar")
    public String avatar;
}
