package ibm.java.academy.cerfiticationsapp.payload.request;

import javax.validation.constraints.NotBlank;

public class UpdateRequest {
    @NotBlank
	private String email;

	@NotBlank
	private String firstname;

    @NotBlank
	private String surname;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
