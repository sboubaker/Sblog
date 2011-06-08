package services;

import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.protocol.GetCallbackWrapper;

import models.Categorie;
import models.Post;
import models.Tag;
import models.User;
import play.cache.Cache;
import util.MongoUtil;

public class DataLayer {

	/**
	 * Methode to return all posts
	 * 
	 * @return
	 */
	public static List<Post> getPosts(boolean all) {
		if(all)
		return Post.findAll();
		else
		return Post.filter("status", true).asList();
	}
	public static List<Post> getnewPosts(int number) {
		return Post.filter("status", true).order("-lastchange").fetch(number);
	}
	public static List<Tag> getAllTags() {
		List<Tag> tags = (List<Tag>) Cache.get("tags");
		if (tags == null) {
			tags = Tag.findAll();
			Cache.set("tags", tags, "30mn");
		}
		return tags;
	}

	/**
	 * Methode to return all categories
	 * 
	 * @return
	 */
	public static List<Categorie> getAllCategories() {
		List<Categorie> categories = (List<Categorie>) Cache.get("categories");
		if (categories == null) {
			categories = Categorie.findAll();
			Cache.set("categories", categories, "30mn");
		}
		return categories;
	}

	/**
	 * Methode to return all users
	 * 
	 * @return
	 */
	public static List<User> getAllUsers() {
		return User.findAll();
	}

	/**
	 * Get post by id
	 * 
	 * @param id
	 * @return
	 */
	public static Post getPostById(String id) {
		return Post.findById(MongoUtil.formatToId(id));
	}
	/**
	 * Get post by id
	 * 
	 * @param id
	 * @return
	 */
	public static Tag getTagById(String id) {
		Tag tag=(Tag) Cache.get("tag_"+id);
		if(tag==null){
			tag=Tag.findById(MongoUtil.formatToId(id));
			if(tag!=null)
			Cache.set("tag_"+id,tag);
		}
		return tag;
	}
	public static Categorie getCategorieById(String id) {
		Categorie categorie=(Categorie) Cache.get("categorie_"+id);
		if(categorie==null){
			categorie=Categorie.findById(MongoUtil.formatToId(id));
			if(categorie!=null)
			Cache.set("categorie_"+id,categorie);
		}
		return categorie;
	}
	public static List<Post> getPostsByTag(String tagId){
		List<Post> list=null;
		Tag tag=(Tag)Tag.filter("tag", tagId).asList().get(0);
		if(tag!=null){
			list=new ArrayList<Post>();
			for(Post post:tag.posts){
				if(post.status)
					list.add(post);
			}
		}
		return list;
	}
	public static List<Post> getPostsByCategorie(String categorieId){
		List<Post> list=null;
		Categorie categorie=(Categorie)Categorie.filter("name", categorieId).asList().get(0);
		if(categorie!=null){
			list=new ArrayList<Post>();
			for(Post post:categorie.posts){
				if(post.status)
					list.add(post);
			}
		}
		return list;
	}
	/**
	 * 
	 */
	public static User getUserByEmail(String email) {
		return User.filter("usermail", email).get();
	}
}
