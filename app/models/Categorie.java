package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
@Entity("categories")
public class Categorie {
    @Id
    public ObjectId id;
    /** Field mapping. */
    public String name;
    /** Field mapping. */
    public Integer parentId;
    /** Field mapping. */
    @Reference
    public List<Post> posts = new ArrayList<Post>();
    /**
     * Default constructor
     */
    public Categorie() {
	// Default constructor
    }
}
