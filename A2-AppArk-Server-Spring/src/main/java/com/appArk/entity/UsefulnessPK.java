/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class UsefulnessPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "review")
    private int review;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_1")
    private int user1;

    public UsefulnessPK() {
    }

    public UsefulnessPK(int review, int user1) {
        this.review = review;
        this.user1 = user1;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) review;
        hash += (int) user1;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsefulnessPK)) {
            return false;
        }
        UsefulnessPK other = (UsefulnessPK) object;
        if (this.review != other.review) {
            return false;
        }
        if (this.user1 != other.user1) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UsefulnessPK[ review=" + review + ", user1=" + user1 + " ]";
    }
    
}
