package ru.fwoods.friendlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.entities.User;
import ru.fwoods.entities.UserFriend;

import java.util.List;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    List<UserFriend> getAllByUser(User user);

    List<UserFriend> getAllByFriend(User user);

    UserFriend getAllByUserAndFriend(User user, User friend);
}
