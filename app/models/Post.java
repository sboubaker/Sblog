package models;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import play.data.validation.Required;
import play.modules.morphia.Model;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@Entity("posts")
public class Post{
    
    @Id
    public ObjectId id;
    /** Field mapping. */
    @Required
    public String content;
    /** Field mapping. */
    public Date lastchange;
    /** Field mapping. */
    @Required
    public String status;
    /** Field mapping. */
    @Required
    public String title;
    /** Field mapping. */
    public List<PostCategories> postCategories = new ArrayList<PostCategories>();
    /** Field mapping. */
    public List<PostTags> postTags = new ArrayList<PostTags>();
    /** Field mapping. */
    @Embedded
    public List<Comment> comments = new ArrayList<Comment>();

    /**
     * Default constructor
     */
    public Post() {
	// Default constructor
    }
    /**
     * constructor
     */
    public Post(String content, Date lastchange, String title) {
	this.content = content;
	this.lastchange = lastchange;
	this.title = title;
    }
    /**
     * Test
     */
    public static void main(String []args){
	 // Create a Mongo instance that points to the MongoDB running on local host 
        Mongo mongo=null;
	try {
	    mongo = new Mongo( "localhost" );
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (MongoException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} 

        // Create a Morphia object and map our model classes 
        Morphia morphia = new Morphia(); 
        morphia.map( Post.class ).map( Comment.class ); 

        // Create a data store 
        Datastore ds = morphia.createDatastore( mongo, "sblog" ); 

        // Query for all users in the database 
        System.out.println( "Posts before we start:" ); 
        Query<Post> users = ds.find( Post.class ); 
        for( Post u : users.fetch()) 
        { 
            System.out.println( "Posts: " + u ); 
        } 
        Comment comments1=new Comment();
        comments1.username="sabri";
        comments1.usermail="boubaker@gmail.com";
        comments1.date=new Date();
        comments1.content="this is the first comment";
        comments1.status="OK";
        Comment comments2=new Comment();
        comments2.username="sabri";
        comments2.usermail="boubaker@gmail.com";
        comments2.date=new Date();
        comments2.content="this is the second comment";
        comments2.status="OK";
        
        Post post=new Post();
        post.content="blablabla<html><a/></html>";
        post.lastchange=new Date();
        post.status="OK";
        post.title="Some title";
        post.comments=new ArrayList<Comment>();
        post.comments.add(comments1);
        post.comments.add(comments2);
        ds.save( post );
        
        
     // Test querying for families 
        Query<Post> postQuery = ds.find( Post.class ); 
        for( Post f : postQuery.fetch() ) 
        { 
            System.out.println( "Family: " + f.content ); 
            System.out.println( "- Dad: " + f.status ); 
            System.out.println( "- Mom: " + f.title ); 
            List<Comment> children = f.comments; 
            System.out.println( "Children (" + children.size() + ")" ); 
            for( Comment child : children ) { 
                System.out.println( "\t" + child ); 
            } 
        } 


    }
}
