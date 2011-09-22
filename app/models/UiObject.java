package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UiObject implements Serializable {
    public List<Post> posts = new ArrayList<Post>();
    public List<Tag> tags = new ArrayList<Tag>();
    public List<Categorie> categories = new ArrayList<Categorie>();
}
