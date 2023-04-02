package reqresApiVersion3.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Create {
    public String name;
    public String job;
    public String id;
    public String createdAt;
}
