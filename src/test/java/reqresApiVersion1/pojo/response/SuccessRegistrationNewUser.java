package reqresApiVersion1.pojo.response;


import lombok.Data;
import lombok.ToString;

@Data

@ToString
public class SuccessRegistrationNewUser {


    public Integer id;
    public String token;

    public SuccessRegistrationNewUser() {
    }

    public SuccessRegistrationNewUser(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

