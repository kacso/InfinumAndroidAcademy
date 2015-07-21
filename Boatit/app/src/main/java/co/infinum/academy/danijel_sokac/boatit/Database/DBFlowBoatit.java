package co.infinum.academy.danijel_sokac.boatit.Database;

import android.content.Context;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;
import co.infinum.academy.danijel_sokac.boatit.Network.ApiManager;

/**
 * Created by Danijel on 19.7.2015..
 */
public class DBFlowBoatit implements Boatit {
    private Context context;

    public DBFlowBoatit(Context context) {
        this.context = context;
    }

    @Override
    public AllBoats getBoats(String token) {
        Condition condition = Condition.columnsWithFunction(BoatitDatabase.NAME, "token").eq(token);
        AllBoats allBoats = new AllBoats();
        allBoats.setBoatList(new Select().from(Boat.class).queryList());
        return allBoats;
    }

    @Override
    public void updateBoat(Boat boat) {
        boat.update();
    }

    @Override
    public void deleteBoats() {
        context.deleteDatabase(BoatitDatabase.NAME + ".db");
    }

    @Override
    public void addBoat(Boat boat) {
        boat.insert();
    }

    @Override
    public void addComment(Comment comment) {
        comment.insert();
    }

    @Override
    public void updateComment(Comment comment) {
        comment.update();
    }
}
