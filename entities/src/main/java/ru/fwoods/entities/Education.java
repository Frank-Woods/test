package ru.fwoods.entities;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
