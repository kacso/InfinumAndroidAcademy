package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.List;

import co.infinum.academy.danijel_sokac.boatit.Database.BoatitDatabase;

/**
 * Created by Danijel on 13.7.2015..
 */
@Table(databaseName = BoatitDatabase.NAME)
public class Boat extends BaseModel implements Serializable{
    @Column
    @PrimaryKey
    private String token;

    @Column
    @PrimaryKey
    @Expose
    @SerializedName("id")
    private int id;

    @Column
    @Expose
    @SerializedName("title")
    private String title;

    @Column
    @Expose
    @SerializedName("image_url")
    private String imageURL;

    @Expose
    @SerializedName("comments")
    private List<Comment> comments = null;

    @Column
    @Expose
    @SerializedName("score")
    private int score;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<Comment> getComments() {
        if (comments != null)
            return comments;
        else {
            return new Select().from(Comment.class).where(
                    Condition.column(Comment$Table.BOATID).is(id)).queryList();
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
