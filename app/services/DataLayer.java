package services;

import java.util.ArrayList;
import java.util.List;

import models.Post;

public class DataLayer {

	/**
	 *  Methode to return all posts
	 * @return 
	 */
	public static List<Post> getAllPosts(){
		List<Post> list=new ArrayList<Post>();
    	Iterable<Post> posts=MongoUtil.getDatastore().find(Post.class).fetch();
    	for(Post post:posts){
    		list.add(post);
    	}
    	return list;
	}
	/**
	 * Get post by id
	 * @param id
	 * @return
	 */
	public static Post getPostById(String id){
		return MongoUtil.getDatastore().find(Post.class).field("_id").equal(MongoUtil.formatToId(id)).get();
	}
}
