package entity;

import entity.Reviews;
import entity.Usefulness;
import entity.Vehicles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-14T16:01:08")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> googleId;
    public static volatile CollectionAttribute<Users, Usefulness> usefulnessCollection;
    public static volatile SingularAttribute<Users, Short> admin;
    public static volatile CollectionAttribute<Users, Reviews> reviewsCollection;
    public static volatile SingularAttribute<Users, Date> birthDate;
    public static volatile SingularAttribute<Users, Vehicles> vehicle;
    public static volatile SingularAttribute<Users, String> surname1;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> surname2;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, Short> googleUser;
    public static volatile SingularAttribute<Users, String> email;

}