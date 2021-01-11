package ru.fwoods.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "first_name",
            length = 40,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 40,
            nullable = false
    )
    private String lastName;

    @Column(
            name = "status",
            length = 40
    )
    private String status;

    @Column(
            name = "gender",
            length = 10,
            nullable = false
    )
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(
            name = "birthday",
            length = 10,
            nullable = false
    )
    private Date birthday;

    @Column(
            name = "status_page",
            length = 10,
            nullable = false
    )
    private StatusPage statusPage;

    @Column(
            name = "email",
            length = 20,
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "status_account",
            length = 10,
            nullable = false
    )
    private StatusAccount statusAccount;

    @Column(
            name = "info"
    )
    private String info;

    @Column(
            name = "role",
            length = 10,
            nullable = false
    )
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    private Set<Role> roles;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_language",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "incoming_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> incoming;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "outgoing_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> outgoing;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public StatusPage getStatusPage() {
        return statusPage;
    }

    public void setStatusPage(StatusPage statusPage) {
        this.statusPage = statusPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusAccount getStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(StatusAccount statusAccount) {
        this.statusAccount = statusAccount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<User> getIncoming() {
        return incoming;
    }

    public void setIncoming(Set<User> incoming) {
        this.incoming = incoming;
    }

    public Set<User> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(Set<User> outgoing) {
        this.outgoing = outgoing;
    }
}
