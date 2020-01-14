package entity;

import entity.Parkings;
import entity.Reviews;
import entity.Usefulness;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-14T15:55:50")
@StaticMetamodel(Reviews.class)
public class Reviews_ { 

    public static volatile SingularAttribute<Reviews, Integer> usefulness;
    public static volatile SingularAttribute<Reviews, Parkings> parking;
    public static volatile SingularAttribute<Reviews, Integer> score;
    public static volatile CollectionAttribute<Reviews, Usefulness> usefulnessCollection;
    public static volatile SingularAttribute<Reviews, Reviews> review;
    public static volatile SingularAttribute<Reviews, Integer> id;
    public static volatile SingularAttribute<Reviews, String> text;
    public static volatile SingularAttribute<Reviews, Users> reviewer;
    public static volatile CollectionAttribute<Reviews, Reviews> reviewsCollection;

}