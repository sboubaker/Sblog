package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;


@Entity
@Table(name="categorie")
public class Categorie extends Model {

	/** Field mapping. */
	public String name;
	/** Field mapping. */
	public Integer parentId;
	/** Field mapping. */
	@OneToMany
	public List<Post> posts = new ArrayList<Post>();
}
