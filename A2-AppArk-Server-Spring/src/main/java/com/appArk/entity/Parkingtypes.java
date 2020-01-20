/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appArk.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(name = "parkingtypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkingtypes.findAll", query = "SELECT p FROM Parkingtypes p")
    , @NamedQuery(name = "Parkingtypes.findByParkingsId", query = "SELECT p FROM Parkingtypes p WHERE p.parkingtypesPK.parkingsId = :parkingsId")
    , @NamedQuery(name = "Parkingtypes.findByVehiclesId", query = "SELECT p FROM Parkingtypes p WHERE p.parkingtypesPK.vehiclesId = :vehiclesId")})
public class Parkingtypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParkingtypesPK parkingtypesPK;
    @JoinColumn(name = "vehicles_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vehicles vehicles;

    
    public Parkingtypes() {
    }

    public Parkingtypes(ParkingtypesPK parkingtypesPK) {
        this.parkingtypesPK = parkingtypesPK;
    }

    public Parkingtypes(int parkingsId, int vehiclesId) {
        this.parkingtypesPK = new ParkingtypesPK(parkingsId, vehiclesId);
    }

    public ParkingtypesPK getParkingtypesPK() {
        return parkingtypesPK;
    }

    public void setParkingtypesPK(ParkingtypesPK parkingtypesPK) {
        this.parkingtypesPK = parkingtypesPK;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parkingtypesPK != null ? parkingtypesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parkingtypes)) {
            return false;
        }
        Parkingtypes other = (Parkingtypes) object;
        if ((this.parkingtypesPK == null && other.parkingtypesPK != null) || (this.parkingtypesPK != null && !this.parkingtypesPK.equals(other.parkingtypesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parkingtypes[ parkingtypesPK=" + parkingtypesPK + " ]";
    }
    
}
