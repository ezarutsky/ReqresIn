package reqresApiVersion1.pojo.request;

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


//    public Login() {
//    }
//
//    public Login(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }


}
