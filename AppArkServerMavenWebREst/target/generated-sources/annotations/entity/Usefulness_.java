package entity;

import entity.Reviews;
import entity.UsefulnessPK;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-16T12:36:23")
@StaticMetamodel(Usefulness.class)
public class Usefulness_ { 

    public static volatile SingularAttribute<Usefulness, Reviews> reviews;
    public static volatile SingularAttribute<Usefulness, UsefulnessPK> usefulnessPK;
    public static volatile SingularAttribute<Usefulness, Short> positive;
    public static volatile SingularAttribute<Usefulness, Users> users;

}