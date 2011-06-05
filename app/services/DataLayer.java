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
	public static List<Post> getAllPosts() {
		List<Post> posts = (List<Post>) Cache.get("posts");
		if (posts == null) {
			posts = Post.findAll();
			Cache.set("posts", posts, "30mn");
		}
		return posts;
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
		Post post=(Post) Cache.get("post_"+id);
		if(post==null){
			post=Post.findById(MongoUtil.formatToId(id));
			if(post!=null)
			Cache.set("post_"+id,post);
		}
		return post;
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
		Tag tag=getTagById(tagId);
		if(tag!=null)
			return tag.posts;
		return null;
	}
	public static List<Post> getPostsByCategorie(String categorieId){
		Categorie categorie=getCategorieById(categorieId);
		if(categorie!=null)
			return categorie.posts;
		return null;
	}
	/**
	 * 
	 */
	public static User getUserByEmail(String email) {
		return User.filter("usermail", email).get();
	}
}
