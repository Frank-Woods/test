package ru.fwoods.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_friend")
public class UserFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "status_friend",
            length = 10,
            nullable = false
    )
    private StatusFriend statusFriend;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusFriend getStatusFriend() {
        return statusFriend;
    }

    public void setStatusFriend(StatusFriend statusFriend) {
        this.statusFriend = statusFriend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
