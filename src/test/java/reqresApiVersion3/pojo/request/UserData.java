package reqresApiVersion3.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    public Integer id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}
