package reqresApi.pojo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleUser {

    public Integer id;
    public String email;
    public String firstName;
    public String lastName;
    public String avatar;

    public SingleUser() {
    }
    public SingleUser(Integer id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
