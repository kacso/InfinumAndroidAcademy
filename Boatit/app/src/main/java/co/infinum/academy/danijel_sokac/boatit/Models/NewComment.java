package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Danijel on 22.7.2015..
 */
public class NewComment {
    @Expose
    @SerializedName("comment")
    NewCommentContent comment;

    public NewCommentContent getComment() {
        return comment;
    }

    public void setComment(NewCommentContent comment) {
        this.comment = comment;
    }
}
