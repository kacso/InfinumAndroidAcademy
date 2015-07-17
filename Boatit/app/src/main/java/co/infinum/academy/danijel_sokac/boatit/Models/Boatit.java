package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danijel on 13.7.2015..
 */
public class Boatit {

    @SerializedName("boat")
    List<Boat> boatList;

    public List<Boat> getBoatList() {
        return boatList;
    }
}
