package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Danijel on 13.7.2015..
 */
public class Boat {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("image_url")
    private String imageURL;

    @SerializedName("comments")
    private List<Comment> comments;

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
        return comments;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
