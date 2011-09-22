package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import models.Categorie;
import models.Post;
import models.Tag;
import models.Comment;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class DataLayer {

    /**
     * Methode to return all posts
     *
     * @return List<Post> la liste des posts
     */
    public static List<Post> getPosts(boolean all) {
        if (all)
            return Post.findAll();
        else
            return Post.find("status is true order by lastchange desc").fetch(5);
    }

    /**
     * Methode to return all tags
     *
     * @return List<Tag>  la liste des tags
     */
    public static List<Tag> getAllTags() {
        List<Tag> tags = Tag.findAll();
        for (Tag tag : tags)
            tag.init();
        return tags;
    }

    /**
     * Methode to return all categories
     *
     * @return List<Categorie>   la liste des categories
     */
    public static List<Categorie> getAllCategories() {
        List<Categorie> categories = Categorie.findAll();
        return categories;
    }

    /**
     * Get post by id
     *
     * @param id le post id
     * @return Post  l'article
     */
    public static Post getPostById(long id) {
        return Post.findById(id);
    }

    /**
     * Get post by title
     *
     * @param title le post title
     * @return Post  l'article
     */
    public static Post getPostByTitle(String title) {
        return Post.find("title", title).first();
    }

    /**
     * Get comment by Id
     *
     * @param id
     * @return
     */
    public static Comment getCommentById(long id) {
        return Comment.findById(id);
    }

    /**
     * return Tag by name
     *
     * @param name nom de la tag
     * @return Tag   la tag
     */
    public static Tag getTagByName(String name) {
        Tag tag = Tag.find("tag", name).first();
        return tag;
    }

    /**
     * return categorie by name
     *
     * @param name nom de catégorie
     * @return Categorie  la catégorie
     */
    public static Categorie getCategorieByName(String name) {
        Categorie categorie = Categorie.find("name", name).first();
        return categorie;
    }

    /**
     * return categorie by id
     *
     * @param id id de la catégorie
     * @return Categorie la catégorie
     */
    public static Categorie getCategorieById(long id) {
        Categorie categorie = Categorie.findById(id);
        return categorie;
    }

    /**
     * get posts by  tag
     *
     * @param tagName nom de la tag
     * @return List<Post> la liste des articles
     */
    public static List<Post> getPostsByTag(String tagName) {
        List<Post> list = null;
        Tag tag = getTagByName(tagName);
        if (tag != null) {
            tag.init();
            list = new ArrayList<Post>();
            for (Post post : tag.posts) {
                if (post.status) {
                    post.init();
                    list.add(post);
                }
            }
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * get posts by categorie
     *
     * @param categoriename nom de la catégorie
     * @return List<Post>   la liste des posts
     */
    public static List<Post> getPostsByCategorie(String categoriename) {
        List<Post> list = null;
        Categorie categorie = getCategorieByName(categoriename);
        if (categorie != null) {
            list = new ArrayList<Post>();
            for (Post post : categorie.posts) {
                if (post.status) {
                    post.init();
                    list.add(post);
                }
            }
        }
        Collections.reverse(list);
        return list;
    }

    public static void deletePost(Post post) {
        Categorie categorie = post.categorie;
        int index = categorie.posts.indexOf(post);
        categorie.posts.remove(index);
        categorie.save();
        post.delete();
    }
}
