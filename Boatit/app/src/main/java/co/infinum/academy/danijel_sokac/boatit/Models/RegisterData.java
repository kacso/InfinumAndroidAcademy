package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danijel on 18.7.2015..
 */
public class RegisterData {

    @SerializedName("user")
    private User user;

    public RegisterData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
