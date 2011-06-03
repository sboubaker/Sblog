import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Categorie;
import models.Comment;
import models.Post;
import models.Tag;

import org.apache.log4j.Logger;
import org.junit.Test;

import play.test.BaseTest;
import play.test.UnitTest;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class BasicTest extends UnitTest {

	Logger logger = Logger.getLogger(BaseTest.class);

	@Test
	public void createAndSaveOnePost() {
		// Create a Mongo instance that points to the MongoDB running on local
		// host
		Mongo mongo = null;
		try {
			mongo = new Mongo("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create a Morphia object and map our model classes
		Morphia morphia = new Morphia();
		morphia.map(Post.class).map(Comment.class);

		// Create a data store
		Datastore ds = morphia.createDatastore(mongo, "sblog", "sblog",
				"sblog".toCharArray());

		Comment comments1 = new Comment();
		comments1.username = "sabri";
		comments1.usermail = "boubaker.sabri@gmail.com";
		comments1.date = new Date();
		comments1.content = "this is the first comment";
		comments1.status = "OK";
		Comment comments2 = new Comment();
		comments2.username = "sabri";
		comments2.usermail = "boubaker.sabri@gmail.com";
		comments2.date = new Date();
		comments2.content = "this is the second comment";
		comments2.status = "OK";

		Categorie categorie = new Categorie();
		categorie.name = "Dev";
		logger.info("Save one categorie");
		ds.save(categorie);

		Tag tag = new Tag();
		tag.tag = "java";
		logger.info("Save one tag");
		ds.save(tag);

		Post post = new Post();
		post.content = "blablabla<html><a/></html>";
		post.lastchange = new Date();
		post.status = "OK";
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
		ds.save(post);
	}
}
