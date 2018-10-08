package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by mmirali on 07/10/2018.
 */
@Entity
public class Profile {
    @Id
    private String identifire;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<ProfileFraction> profileFractionList;
    public String getIdentifire() {
        return identifire;
    }

    public void setIdentifire(String identifire) {
        this.identifire = identifire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return identifire.equals(profile.identifire);
    }

    @Override
    public int hashCode() {
        return identifire.hashCode();
    }
}
