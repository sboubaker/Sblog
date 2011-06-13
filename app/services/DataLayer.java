package services;

import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.protocol.GetCallbackWrapper;

import models.Categorie;
import models.Post;
import models.Tag;
import models.User;
import util.DbUtil;

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
		return Post.find("status", true).fetch();
	}
	public static List<Post> getnewPosts(int number) {
		return Post.find("status", true).fetch(number);
	}
	public static List<Tag> getAllTags() {
		List<Tag> tags = Tag.findAll();
        for(Tag tag:tags)
        tag.init();
		return tags;
	}

	/**
	 * Methode to return all categories
	 * 
	 * @return
	 */
	public static List<Categorie> getAllCategories() {
		List<Categorie> categories = Categorie.findAll();
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
	public static Post getPostById(long id) {
		return Post.findById(id);
	}

	public static Tag getTagByName(String name) {
		Tag tag=Tag.find("tag", name).first();
        if(tag!=null)
        tag.init();
		return tag;
	}
    public static Categorie getCategorieByName(String name) {
		Categorie categorie=Categorie.find("name", name).first();
		return categorie;
	}
	public static Categorie getCategorieById(long id) {
		Categorie categorie=Categorie.findById(id);
		return categorie;
	}
	public static List<Post> getPostsByTag(String tagname){
		List<Post> list=null;
		Tag tag=getTagByName(tagname);
        tag.init();
		if(tag!=null){
			list=new ArrayList<Post>();
			for(Post post:tag.posts){
				if(post.status)
                {
                    post.init();
                    list.add(post);
                }
			}
		}
		return list;
	}
	public static List<Post> getPostsByCategorie(String categoriename){
		List<Post> list=null;
		Categorie categorie=getCategorieByName(categoriename) ;
		if(categorie!=null){
			list=new ArrayList<Post>();
			for(Post post:categorie.posts){
				if(post.status)
				{
                    post.init();
                    list.add(post);
                }
			}
		}
		return list;
	}
	/**
	 * 
	 */
	public static User getUserByEmail(String email) {
        List<User> user=User.find("usermail", email).fetch();
        if(user!=null && user.size()!=0){
             return user.get(0);
        }
		return null;
	}
}
