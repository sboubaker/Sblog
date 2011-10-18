package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "stat")
public class Stat extends Model {

    public String url;
    public String ip;
    public Date date;
}
