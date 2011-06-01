package models;

import java.util.Date;

import org.bson.types.ObjectId;

import play.data.validation.Required;
import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Id;

@Embedded
public class Comment extends Model {
    /** Field mapping. */
    @Required
    public String content;
    /** Field mapping. */
    @Required
    public Date date;
    /** Field mapping. */
    @Required
    public String status;
    /** Field mapping. */
    @Required
    public String usermail;
    /** Field mapping. */
    @Required
    public String username;

    public Comment() {
	// Default constructor
    }

    public Comment(String content, Date date) {
	this.content = content;
	this.date = date;
    }
}
