package co.infinum.academy.danijel_sokac.boatit.Converters;

import com.raizlabs.android.dbflow.converter.TypeConverter;

import co.infinum.academy.danijel_sokac.boatit.Models.Author;

/**
 * Created by Danijel on 21.7.2015..
 */
@com.raizlabs.android.dbflow.annotation.TypeConverter
public class AuthorDBConverter extends TypeConverter<String, Author> {

    @Override
    public String getDBValue(Author author) {
        return author == null ? null : author.getFirstName() + ";" + author.getLastName();
    }

    @Override
    public Author getModelValue(String data) {
        String[] values = data.split(";");
        if(values.length < 2) {
            return null;
        } else {
            Author author = new Author();
            author.setFirstName(values[0]);
            author.setLastName(values[1]);
            return author;
        }
    }
}
