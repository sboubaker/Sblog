import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Categorie;
import models.Comment;
import models.Post;
import models.Tag;
import models.User;

import org.apache.log4j.Logger;
import org.junit.Test;

import play.test.BaseTest;
import play.test.UnitTest;
import util.Constantes;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class BasicTest extends UnitTest {

	Logger logger = Logger.getLogger(BaseTest.class);
	String postcontent="C�est vraiment un pas en avant vers une architecture o� le client tient les informations d��tat (appelez cela la session si vous voulez) et o� le serveur est compl�tement sans �tat conversationnel. Il peut cependant tr�s bien avoir un cache de donn�es, mais il n�y a plus la notion de session utilisateur du c�t� serveur. Il y a la notion de session serveur du c�t� client. Que ce soit d�s aujourd�hui avec un Cookie, ou demain avec HTML5,"+
	"c�est une approche assez diff�rente. <b>Je n�ai pas dit mieux ou moins bien, j�ai bien dit : une approche diff�rente.</b>";
	@Test
	public void cleardatabase() {
		logger.info(Tag.deleteAll());
		logger.info(Categorie.deleteAll());
		logger.info(Post.deleteAll());
		logger.info(User.deleteAll());
	}
	@Test
	public void createAndSaveOnePost() {
		Comment comments1 = new Comment();
		comments1.username = "sabri";
		comments1.usermail = "boubaker.sabri@gmail.com";
		comments1.date = new Date();
		comments1.content = "this is the first comment";
		comments1.status = false;
		Comment comments2 = new Comment();
		comments2.username = "sabri";
		comments2.usermail = "boubaker.sabri@gmail.com";
		comments2.date = new Date();
		comments2.content = "this is the second comment";
		comments2.status = false;
		Categorie categorie = new Categorie();
		categorie.name = "Dev";
		logger.info("Save one categorie");
		categorie.save();
		Tag tag = new Tag();
		tag.tag = "java";
		logger.info("Save one tag");
		tag.save();
		Post post = new Post();
		post.content = postcontent;
		post.lastchange = new Date();
		post.status = true;
		post.title = "Cloud";
		post.comments = new ArrayList<Comment>();
		post.comments.add(comments1);
		post.comments.add(comments2);
		List<Tag> tags = new ArrayList<Tag>();
		List<Categorie> categories = new ArrayList<Categorie>();
		tags.add(tag);
		categories.add(categorie);
		post.tags = tags;
		post.categories = categories;
		logger.info("Save one post");
		post.save();
		// update tag and categorie
		tag.posts.add(post);
		tag.save();
		categorie.posts.add(post);
		categorie.save();
		User user = new User();
		user.usermail = "boubaker.sabri@gmail.com";
		user.userpwd = "admin";
		user.username = "sboubaker";
		user.role = Constantes.ROLE_ADMIN;
		user.posts.add(post);
		user.save();
	}
}
