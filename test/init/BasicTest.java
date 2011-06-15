package init;
import models.Categorie;
import models.Comment;
import models.Post;
import models.Tag;

import org.apache.log4j.Logger;
import org.junit.Test;

import play.test.BaseTest;
import play.test.UnitTest;
import util.Constantes;

public class BasicTest extends UnitTest {

	Logger logger = Logger.getLogger(BaseTest.class);

    //@Test
	public void cleardatabase() {
        logger.info(Comment.deleteAll());
        logger.info(Post.deleteAll());
        logger.info(Tag.deleteAll());
		logger.info(Categorie.deleteAll());

	}
	//@Test
	public void createAndSaveOnePost() {

	}
}
