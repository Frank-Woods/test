package ru.fwoods.entities;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            length = 30,
            nullable = false
    )
    private String name;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
