/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kirek
 */
@Entity
@Table(name = "parkings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkings.findAll", query = "SELECT p FROM Parkings p")
    , @NamedQuery(name = "Parkings.findById", query = "SELECT p FROM Parkings p WHERE p.id = :id")})
public class Parkings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinTable(name = "parkingtypes", joinColumns = {
        @JoinColumn(name = "PARKINGS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "VEHICLES_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Vehicles> vehiclesCollection;
    @OneToMany(mappedBy = "parking")
    private Collection<Reviews> reviewsCollection;

    //Variables necesarias para la lista de parkings
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected Double score;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected Double latitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected Double longitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected boolean accesibility;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    protected Integer places;
    
    
    public Parkings() {
    }

    public Parkings(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAccesibility() {
        return accesibility;
    }

    public void setAccesibility(boolean accesibility) {
        this.accesibility = accesibility;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @XmlTransient
    public Collection<Vehicles> getVehiclesCollection() {
        return vehiclesCollection;
    }

    public void setVehiclesCollection(Collection<Vehicles> vehiclesCollection) {
        this.vehiclesCollection = vehiclesCollection;
    }

    @XmlTransient
    public Collection<Reviews> getReviewsCollection() {
        return reviewsCollection;
    }

    public void setReviewsCollection(Collection<Reviews> reviewsCollection) {
        this.reviewsCollection = reviewsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parkings)) {
            return false;
        }
        Parkings other = (Parkings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parkings[ id=" + id + " ]";
    }
    
}
