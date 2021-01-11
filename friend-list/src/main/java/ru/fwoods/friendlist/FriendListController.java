package ru.fwoods.friendlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.fwoods.entities.User;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friend")
public class FriendListController {

    @Autowired
    private UserFriendServices userFriendServices;

    @GetMapping(value = "/list")
    public ModelAndView getFriendList(
            User user,
            Map<String, Object> model
    ) {
        List<User> friends = userFriendServices.getFriends(user);
        model.put("friends", friends);
        ModelAndView modelAndView = new ModelAndView("friend/list", model);
        return modelAndView;
    }

    @GetMapping(value = "/incoming")
    public ModelAndView getIncoming(
            User user,
            Map<String, Object> model
    ) {
        List<User> incoming = userFriendServices.getIncoming(user);
        model.put("incoming", incoming);
        ModelAndView modelAndView = new ModelAndView("friend/incoming", model);
        return modelAndView;
    }

    @GetMapping(value = "/outgoing")
    public ModelAndView getOutgoing(
            User user,
            Map<String, Object> model
    ) {
        List<User> outgoing = userFriendServices.getOutgoing(user);
        model.put("outgoing", outgoing);
        ModelAndView modelAndView = new ModelAndView("friend/outgoing", model);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addFriend(
            User authUser,
            User user
    ) {
        userFriendServices.addFriend(authUser, user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/confirm")
    public ResponseEntity confirmFriend(
            User authUser,
            User user
    ) {
        userFriendServices.confirmedFriend(authUser, user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity deleteFriend(
            User authUser,
            User user
    ) {
        userFriendServices.deleteFriend(authUser, user);
        return ResponseEntity.ok().body(null);
    }
}
