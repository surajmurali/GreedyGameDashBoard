package greedygames.com.greedygamedashboard.jsonmodels;

/**
 * Created by surajmuralidharagupta on 11/16/16.
 */
public class LoginResponse {
    private String id;
    private String email;
    private String token;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
