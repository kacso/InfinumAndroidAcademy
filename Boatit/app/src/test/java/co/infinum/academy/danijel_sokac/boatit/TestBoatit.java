package co.infinum.academy.danijel_sokac.boatit;

import co.infinum.academy.danijel_sokac.boatit.network.TestApiManager;
import co.infinum.academy.danijel_sokac.boatit.network.TestApiManager2;

/**
 * Created by Danijel on 25.7.2015..
 */
public class TestBoatit extends BoatitApplication {

    public void init() {
        TestApiManager2.getInstance().init();
        apiManager = TestApiManager2.getInstance();
    }
}
