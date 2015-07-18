package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Danijel on 18.7.2015..
 */
public class Author {
    @SerializedName("first_name")
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @SerializedName("last_name")
    private String lastName;
}
