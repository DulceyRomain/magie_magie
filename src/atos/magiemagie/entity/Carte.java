/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Administrateur
 */
@Entity
public class Carte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @ManyToOne
    private Joueur joueurProprietaire;
    
    
    public enum typeCarte{BAVE_DE_CRAPAUD,AILE_DE_CHAUVE_SOURIS,CORNE_DE_LICORNE,LAPIS_LAZULI,MANDRAGORE};

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private typeCarte ingredient;
    
    public Joueur getJoueurProprietaire() {
        return joueurProprietaire;
    }

    public void setJoueurProprietaire(Joueur joueurProprietaire) {
        this.joueurProprietaire = joueurProprietaire;
    }

    public typeCarte getIngredient() {
        return ingredient;
    }

    public void setIngredient(typeCarte ingredient) {
        this.ingredient = ingredient;
    }
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Carte)) {
            return false;
        }
        Carte other = (Carte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "atos.magiemagie.entity.Carte[ id=" + id + " ]";
    }
    
}
