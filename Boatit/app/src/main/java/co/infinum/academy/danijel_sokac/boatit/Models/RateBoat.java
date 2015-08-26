package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danijel on 18.7.2015..
 */
public class RateBoat {
    @Expose
    @SerializedName("response")
    Boat boat;

    public Boat getBoat() {
        return boat;
    }
}
