package controllers;

import models.Post;
import models.UiObject;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import services.DataLayer;

/**
 * User: boubaker
 * Date: 10/06/11
 * Time: 13:37
 * To change this template use File | Settings | File Templates.
 */
public class GenericController extends Controller{

    @Before
	static void intiIHM() {
        UiObject uiObject=null;
        //TODO ignore before deploying
        //uiObject=(UiObject)Cache.get("uiobject");
		if(uiObject==null){
        uiObject=new UiObject();
		uiObject.tags=DataLayer.getAllTags();
		uiObject.categories=DataLayer.getAllCategories();
        //Cache.add("uiobject",uiObject);
        }
		renderArgs.put("uiObject", uiObject);
    }
}
