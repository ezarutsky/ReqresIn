package reqresApiVersion1.pojo.response;

public class UnSuccessRegistrationNewUser {
    private String error;

    public UnSuccessRegistrationNewUser() {

    }
    public UnSuccessRegistrationNewUser(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
