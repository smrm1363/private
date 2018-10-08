package entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by mmirali on 07/10/2018.
 */
@Entity
public class ProfileFraction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    private Profile profile;
    @NotNull
    private MonthEnum month;
    @NotNull
    private Double fraction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public MonthEnum getMonth() {
        return month;
    }

    public void setMonth(MonthEnum month) {
        this.month = month;
    }

    public Double getFraction() {
        return fraction;
    }

    public void setFraction(Double fraction) {
        this.fraction = fraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileFraction that = (ProfileFraction) o;

        if (!id.equals(that.id)) return false;
        if (!profile.equals(that.profile)) return false;
        if (month != that.month) return false;
        return fraction.equals(that.fraction);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
