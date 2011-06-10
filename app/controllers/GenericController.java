package controllers;

import models.UiObject;
import play.mvc.Before;
import play.mvc.Controller;
import services.DataLayer;

/**
 * Created by IntelliJ IDEA.
 * User: boubaker
 * Date: 10/06/11
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
public class GenericController extends Controller{

    @Before
	static void intiIHM() {
		UiObject uiObject=new UiObject();
		uiObject.posts= DataLayer.getnewPosts(3);
		uiObject.tags=DataLayer.getAllTags();
		uiObject.categories=DataLayer.getAllCategories();
		renderArgs.put("uiObject", uiObject);
    }
}
