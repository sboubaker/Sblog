package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
@Table(name = "comment")
public class Comment extends Model {

    /**
     * Field mapping.
     */
    @Required
    public String content;
    /**
     * Field mapping.
     */
    @Required
    public Date date;
    /**
     * Field mapping.
     */
    @Required
    public boolean status;
    /**
     * Field mapping.
     */
    @Required
    public String usermail;
    /**
     * Field mapping.
     */
    @Required
    public String username;

    public String usersite;

    public boolean subscribe;
}
