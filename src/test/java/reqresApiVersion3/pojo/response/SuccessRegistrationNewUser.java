package reqresApiVersion3.pojo.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessRegistrationNewUser {
    public Integer id;
    public String token;
}

