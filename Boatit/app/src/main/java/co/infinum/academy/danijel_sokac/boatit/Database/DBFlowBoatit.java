package co.infinum.academy.danijel_sokac.boatit.Database;

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
    @Override
    public AllBoats getBoats(String token) {
        Condition condition = Condition.columnsWithFunction(BoatitDatabase.NAME, "token").eq(token);
        BoatDatabaseElement boatDatabaseElement =
                new Select().from(BoatDatabaseElement.class).querySingle();
//        Gson gson = new Gson();
        return ApiManager.GSON.fromJson(boatDatabaseElement.getBoats(), AllBoats.class);
    }

    @Override
    public void addBoat(BoatDatabaseElement boat) {
        boat.insert();
    }

    @Override
    public void deleteBoats() {
    }
}
