package init;
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

public class BasicTest extends UnitTest {

	Logger logger = Logger.getLogger(BaseTest.class);
	String postcontent="Concernant ce site, qui fabrique la C3 - la voiture la plus vendue par Citroën -,"
		+" le «plan social» est prévu pour 2013, et «l’arrêt», «courant 2014». " +
				"«Ce planning est cohérent avec les orient";
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
		comments1.number=0;
		comments1.username = "sabri";
		comments1.usermail = "boubaker.sabri@gmail.com";
		comments1.date = new Date();
		comments1.content = "this is the first comment";
		comments1.status = false;
		comments1.save();
		Comment comments2 = new Comment();
		comments2.number=1;
		comments2.username = "sabri";
		comments2.usermail = "boubaker.sabri@gmail.com";
		comments2.date = new Date();
		comments2.content = "this is the second comment";
		comments2.status = false;
		comments2.save();
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
		tags.add(tag);
		post.tags = tags;
		post.categorie = categorie;
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
