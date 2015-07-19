package co.infinum.academy.danijel_sokac.boatit.Database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import co.infinum.academy.danijel_sokac.boatit.Models.AllBoats;
import co.infinum.academy.danijel_sokac.boatit.Models.Boat;

/**
 * Created by Danijel on 19.7.2015..
 */
@Table(databaseName = BoatitDatabase.NAME)
public class BoatDatabaseElement extends BaseModel implements Serializable {
    @Column
    @PrimaryKey
    private String token;

    @Column
    private String boats;

    public String getBoats() {
        return boats;
    }

    public void setBoats(String boats) {
        this.boats = boats;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
