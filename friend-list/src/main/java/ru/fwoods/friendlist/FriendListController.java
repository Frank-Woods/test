package ru.fwoods.friendlist;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/list")
    public ModelAndView getUserPage(
            User user,
            Map<String, Object> model
    ) {
        Set<User> friends = user.getFriends();
        model.put("friends", friends);
        ModelAndView modelAndView = new ModelAndView("friend/list", model);
        return modelAndView;
    }
}
