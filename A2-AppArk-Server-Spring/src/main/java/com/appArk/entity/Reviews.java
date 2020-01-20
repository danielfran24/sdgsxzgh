/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "reviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r")
    , @NamedQuery(name = "Reviews.findById", query = "SELECT r FROM Reviews r WHERE r.id = :id")
    , @NamedQuery(name = "Reviews.findByText", query = "SELECT r FROM Reviews r WHERE r.text = :text")
    , @NamedQuery(name = "Reviews.findByScore", query = "SELECT r FROM Reviews r WHERE r.score = :score")
    , @NamedQuery(name = "Reviews.findByUsefulness", query = "SELECT r FROM Reviews r WHERE r.usefulness = :usefulness")
    , @NamedQuery(name = "Reviews.findByReview", query = "SELECT r FROM Reviews r WHERE r.review = :review")})
public class Reviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "text")
    private String text;
    @Column(name = "score")
    private Integer score;
    @Column(name = "usefulness")
    private Integer usefulness;
    @Column(name = "review")
    private Integer review;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviews")
    private Collection<Usefulness> usefulnessCollection;
    @JoinColumn(name = "parking", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Parkings parking;
    @JoinColumn(name = "reviewer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users reviewer;

    public Reviews() {
    }

    public Reviews(Integer id) {
        this.id = id;
    }

    public Reviews(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getUsefulness() {
        return usefulness;
    }

    public void setUsefulness(Integer usefulness) {
        this.usefulness = usefulness;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    @XmlTransient
    public Collection<Usefulness> getUsefulnessCollection() {
        return usefulnessCollection;
    }

    public void setUsefulnessCollection(Collection<Usefulness> usefulnessCollection) {
        this.usefulnessCollection = usefulnessCollection;
    }

    public Parkings getParking() {
        return parking;
    }

    public void setParking(Parkings parking) {
        this.parking = parking;
    }

    public Users getReviewer() {
        return reviewer;
    }

    public void setReviewer(Users reviewer) {
        this.reviewer = reviewer;
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
        if (!(object instanceof Reviews)) {
            return false;
        }
        Reviews other = (Reviews) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reviews[ id=" + id + " ]";
    }
    
}
