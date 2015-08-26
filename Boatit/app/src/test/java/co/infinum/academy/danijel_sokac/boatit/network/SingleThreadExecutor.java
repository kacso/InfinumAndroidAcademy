package co.infinum.academy.danijel_sokac.boatit.network;

import java.util.concurrent.Executor;

/**
 * Created by Danijel on 25.7.2015..
 */
public class SingleThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
