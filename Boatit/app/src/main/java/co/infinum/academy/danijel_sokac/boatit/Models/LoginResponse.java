package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danijel on 16.7.2015..
 */
public class LoginResponse {
    @Expose
    @SerializedName("response")
    LoginData response;

    public LoginData getResponse() {
        return response;
    }

}
