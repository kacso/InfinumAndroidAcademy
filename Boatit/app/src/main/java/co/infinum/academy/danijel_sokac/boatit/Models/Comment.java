package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Danijel on 17.7.2015..
 */
public class Comment {
    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("created_at")
    private String createAt;

    public String getCreateAt() {
        return createAt;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
