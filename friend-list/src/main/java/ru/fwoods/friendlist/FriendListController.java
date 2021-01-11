package ru.fwoods.friendlist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.fwoods.entities.User;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/friend")
public class FriendListController {

    @GetMapping(value = "/list")
    public ModelAndView getFriendList(
            User user,
            Map<String, Object> model
    ) {
        Set<User> friends = user.getFriends();
        model.put("friends", friends);
        ModelAndView modelAndView = new ModelAndView("friend/list", model);
        return modelAndView;
    }

    @GetMapping(value = "/incoming")
    public ModelAndView getIncoming(
            User user,
            Map<String, Object> model
    ) {
        Set<User> incoming = user.getIncoming();
        model.put("incoming", incoming);
        ModelAndView modelAndView = new ModelAndView("friend/incoming", model);
        return modelAndView;
    }

    @GetMapping(value = "/outgoing")
    public ModelAndView getOutgoing(
            User user,
            Map<String, Object> model
    ) {
        Set<User> outgoing = user.getOutgoing();
        model.put("outgoing", outgoing);
        ModelAndView modelAndView = new ModelAndView("friend/outgoing", model);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ResponseEntity addFriend(
            User authUser,
            User user
    ) {
        authUser.getOutgoing().add(user);
        user.getIncoming().add(authUser);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/confirm")
    public ResponseEntity confirmFriend(
            User authUser,
            User user
    ) {
        authUser.getIncoming().remove(user);
        user.getOutgoing().remove(authUser);
        authUser.getFriends().add(user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity deleteFriend(
            User authUser,
            User user
    ) {
        authUser.getFriends().remove(user);
        return ResponseEntity.ok().body(null);
    }
}
