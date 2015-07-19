package co.infinum.academy.danijel_sokac.boatit.Models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

import co.infinum.academy.danijel_sokac.boatit.Database.BoatitDatabase;

/**
 * Created by Danijel on 17.7.2015..
 */
public class Comment extends BaseModel implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("content")
    private String content;

    @SerializedName("created_at")
    private String createAt;

    @SerializedName("author")
    private Author author;

    public String getCreateAt() {
        return createAt;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}