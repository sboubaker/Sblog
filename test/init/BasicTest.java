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
	String postcontent="Concernant ce site, qui fabrique la C3 - la voiture la plus vendue par Citron -,"
		+" le plan social est prvu pour 2013, et larrt, courant 2014. " +
				"Ce planning est cohrent avec les orient";
	//@Test
	public void cleardatabase() {
        logger.info(User.deleteAll());
        logger.info(Comment.deleteAll());
        logger.info(Post.deleteAll());
        logger.info(Tag.deleteAll());
		logger.info(Categorie.deleteAll());

	}
	@Test
	public void createAndSaveOnePost() {

		User user = new User();
		user.usermail = "boubaker.sabri@gmail.com";
		user.userpwd = "admin";
		user.username = "sboubaker";
		user.role = Constantes.ROLE_ADMIN;
		//user.posts.add(post);
		user.save();
	}
}
