package entity;

import entity.Reviews;
import entity.Vehicles;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-14T15:55:50")
@StaticMetamodel(Parkings.class)
public class Parkings_ { 

    public static volatile SingularAttribute<Parkings, Integer> id;
    public static volatile CollectionAttribute<Parkings, Vehicles> vehiclesCollection;
    public static volatile CollectionAttribute<Parkings, Reviews> reviewsCollection;

}