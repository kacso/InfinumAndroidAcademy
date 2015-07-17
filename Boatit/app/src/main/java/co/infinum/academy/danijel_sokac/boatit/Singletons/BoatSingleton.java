package co.infinum.academy.danijel_sokac.boatit.Singletons;

import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 17.7.2015..
 */
public class BoatSingleton {
    private Boat boat;
    private static BoatSingleton boatSingleton = null;

    private BoatSingleton() {
    }

    public static BoatSingleton InstanceOfSessionSingleton() {
        if (boatSingleton == null) {
            boatSingleton = new BoatSingleton();
        }
        return boatSingleton;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
