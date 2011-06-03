package controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import models.Post;
import play.mvc.Controller;
import services.DataLayer;
import services.MongoUtil;

public class Consultation extends Controller {

    public static void index() {
        render();
    }
    public static void articles(int type,String value) {
    	List<Post> list=DataLayer.getAllPosts();
    	render(list);
    }
    public static void article(String id) {
    	Post post=DataLayer.getPostById(id);
    	render(post);
    }
}