package entity;

import entity.Parkings;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-16T12:36:23")
@StaticMetamodel(Vehicles.class)
public class Vehicles_ { 

    public static volatile CollectionAttribute<Vehicles, Parkings> parkingsCollection;
    public static volatile SingularAttribute<Vehicles, Integer> id;
    public static volatile SingularAttribute<Vehicles, String> type;
    public static volatile CollectionAttribute<Vehicles, Users> usersCollection;

}