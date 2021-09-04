package ibm.java.academy.cerfiticationsapp.payload.response;

import java.util.List;

public class JwtResponse{
    private String token;
	private String type = "Bearer";
	private Long id;
	private String firstname;
    private String surname;
	private String email;
	private List<String> roles;
    
    public JwtResponse(String token, Long id, String firstname, String surname, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    
}
