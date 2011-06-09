package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name="user")
public class User extends Model {

	@Required
	public String username;
	@Required
	public String usermail;
	@Required
	public String userpwd;
	public Date dtCreate;
	@Required
	public String role;
	@OneToMany
	public List<Post> posts = new ArrayList<Post>();
}
