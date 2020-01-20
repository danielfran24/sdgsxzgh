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
public class ParkingtypesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "parkings_id")
    private int parkingsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vehicles_id")
    private int vehiclesId;

    public ParkingtypesPK() {
    }

    public ParkingtypesPK(int parkingsId, int vehiclesId) {
        this.parkingsId = parkingsId;
        this.vehiclesId = vehiclesId;
    }

    public int getParkingsId() {
        return parkingsId;
    }

    public void setParkingsId(int parkingsId) {
        this.parkingsId = parkingsId;
    }

    public int getVehiclesId() {
        return vehiclesId;
    }

    public void setVehiclesId(int vehiclesId) {
        this.vehiclesId = vehiclesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) parkingsId;
        hash += (int) vehiclesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParkingtypesPK)) {
            return false;
        }
        ParkingtypesPK other = (ParkingtypesPK) object;
        if (this.parkingsId != other.parkingsId) {
            return false;
        }
        if (this.vehiclesId != other.vehiclesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParkingtypesPK[ parkingsId=" + parkingsId + ", vehiclesId=" + vehiclesId + " ]";
    }
    
}
