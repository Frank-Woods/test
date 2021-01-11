package ru.fwoods.friendlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.entities.StatusFriend;
import ru.fwoods.entities.User;
import ru.fwoods.entities.UserFriend;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFriendServices {

    @Autowired
    private UserFriendRepository userFriendRepository;


    public List<User> getFriends(User user) {
        List<User> friends = new ArrayList<>();

        List<UserFriend> userFriends = userFriendRepository.getAllByUser(user);

        userFriends.forEach(userFriend -> {
            if(userFriend.getStatusFriend().ordinal() == 0) {
                friends.add(userFriend.getFriend());
            }
        });

        return friends;
    }

    public List<User> getIncoming(User user) {
        List<User> friends = new ArrayList<>();

        List<UserFriend> userFriends = userFriendRepository.getAllByUser(user);

        userFriends.forEach(userFriend -> {
            if(userFriend.getStatusFriend().ordinal() == 1) {
                friends.add(userFriend.getFriend());
            }
        });

        return friends;
    }

    public List<User> getOutgoing(User user) {
        List<User> friends = new ArrayList<>();

        List<UserFriend> userFriends = userFriendRepository.getAllByFriend(user);

        userFriends.forEach(userFriend -> {
            if(userFriend.getStatusFriend().ordinal() == 1) {
                friends.add(userFriend.getUser());
            }
        });

        return friends;
    }

    public void addFriend(User authUser, User user) {
        UserFriend userFriendNew = new UserFriend();
        userFriendNew.setUser(authUser);
        userFriendNew.setFriend(user);
        userFriendNew.setStatusFriend(StatusFriend.NOTCONFIRMED);
        userFriendRepository.save(userFriendNew);
    }

    public void confirmedFriend(User authUser, User user) {
        UserFriend userFriend = userFriendRepository.getAllByUserAndFriend(user, authUser);
        userFriend.setStatusFriend(StatusFriend.CONFIRMED);

        UserFriend userFriendNew = new UserFriend();
        userFriendNew.setUser(authUser);
        userFriendNew.setFriend(user);
        userFriendNew.setStatusFriend(StatusFriend.CONFIRMED);
        userFriendRepository.save(userFriendNew);
    }

    public void deleteFriend(User authUser, User user) {
        UserFriend userDelete = userFriendRepository.getAllByUserAndFriend(authUser, user);
        UserFriend friendDelete = userFriendRepository.getAllByUserAndFriend(user, authUser);

        userFriendRepository.delete(userDelete);
        userFriendRepository.delete(friendDelete);
    }
}
