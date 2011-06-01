package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import play.data.validation.Required;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;


@Entity("tags")
public class Tag {
    
    @Id
    public ObjectId id;
    @Reference
    public List<Post> posts = new ArrayList<Post>();
    @Required
    public String tag;
    /**
     * Default constructor
     */
    public Tag() {
	// Default constructor
    }
}
