package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import play.data.validation.Required;
import play.modules.morphia.Model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;

@Entity("users")
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
	@Reference
	public List<Post> posts = new ArrayList<Post>();
}
