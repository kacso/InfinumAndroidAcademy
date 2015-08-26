package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Danijel on 18.7.2015..
 */
public class User {
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
