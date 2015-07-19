package co.infinum.academy.danijel_sokac.boatit.Database;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 19.7.2015..
 */
public interface Boatit {

    public AllBoats getBoats(String token);

    public void addBoat(BoatDatabaseElement boat);

    public void deleteBoats();
}
