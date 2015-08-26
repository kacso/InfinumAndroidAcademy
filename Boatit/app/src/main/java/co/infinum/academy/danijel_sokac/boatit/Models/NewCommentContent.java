package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Danijel on 22.7.2015..
 */
public class NewCommentContent {
    @Expose
    @SerializedName("content")
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
