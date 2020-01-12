/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kirek
 */
@Entity
@Table(name = "usefulness")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usefulness.findAll", query = "SELECT u FROM Usefulness u")
    , @NamedQuery(name = "Usefulness.findByReview", query = "SELECT u FROM Usefulness u WHERE u.usefulnessPK.review = :review")
    , @NamedQuery(name = "Usefulness.findByUser", query = "SELECT u FROM Usefulness u WHERE u.usefulnessPK.user = :user")
    , @NamedQuery(name = "Usefulness.findByPositive", query = "SELECT u FROM Usefulness u WHERE u.positive = :positive")})
public class Usefulness implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsefulnessPK usefulnessPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSITIVE")
    private short positive;
    @JoinColumn(name = "REVIEW", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reviews reviews;
    @JoinColumn(name = "USER", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Usefulness() {
    }

    public Usefulness(UsefulnessPK usefulnessPK) {
        this.usefulnessPK = usefulnessPK;
    }

    public Usefulness(UsefulnessPK usefulnessPK, short positive) {
        this.usefulnessPK = usefulnessPK;
        this.positive = positive;
    }

    public Usefulness(int review, int user) {
        this.usefulnessPK = new UsefulnessPK(review, user);
    }

    public UsefulnessPK getUsefulnessPK() {
        return usefulnessPK;
    }

    public void setUsefulnessPK(UsefulnessPK usefulnessPK) {
        this.usefulnessPK = usefulnessPK;
    }

    public short getPositive() {
        return positive;
    }

    public void setPositive(short positive) {
        this.positive = positive;
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usefulnessPK != null ? usefulnessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usefulness)) {
            return false;
        }
        Usefulness other = (Usefulness) object;
        if ((this.usefulnessPK == null && other.usefulnessPK != null) || (this.usefulnessPK != null && !this.usefulnessPK.equals(other.usefulnessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usefulness[ usefulnessPK=" + usefulnessPK + " ]";
    }
    
}
