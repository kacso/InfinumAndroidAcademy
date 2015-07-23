package co.infinum.academy.danijel_sokac.boatit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.widget.Toast;

import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Models.Boat;
import co.infinum.academy.danijel_sokac.boatit.Models.Comment;

/**
 * Created by Danijel on 23.7.2015..
 */
public class DatabaseExecutor {
    Boatit db;
    Context context;

    public DatabaseExecutor(Context context, Boatit db) {
        this.db = db;
        this.context = context;
    }

    public void clearDatabase() {
        db.deleteBoats();
    }


    public void storeBoat(List<Boat> allBoats, String token) {

        for (Boat boat : allBoats) {
            try {
                boat.setToken(token);
                db.addBoat(boat);

                storeCommentsToDatabase(boat);

            } catch (SQLiteConstraintException ex) {
                ex.printStackTrace();
//                db.updateBoat(boat);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Errors while saving to db", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void storeCommentsToDatabase(Boat boat) {
        for (Comment comment : boat.getComments()) {
            comment.setBoatId(boat.getId());
            try {
                db.addComment(comment);
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
//                db.updateComment(comment);
            }
        }
    }
}
