package reqresApiVersion2.pojo.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {
    @Getter
    public String email;
    @Getter
    public String password;
}
