package ru.fwoods.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.fwoods.entities.Image;
import ru.fwoods.entities.Post;
import ru.fwoods.entities.User;
import ru.fwoods.posts.service.ImageService;
import ru.fwoods.posts.service.PostService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/")
    public ModelAndView getAllPost(
        Map<String, Object> model
    ) {
        List<Post> posts = postService.getAll();
        model.put("posts", posts);
        ModelAndView modelAndView = new ModelAndView("posts", model);
        return modelAndView;
    }

    @GetMapping(value = "/user")
    public ModelAndView getUserPosts(
            User user,
            Map<String, Object> model
    ) {
        List<Post> posts = postService.getAllByUser(user);
        model.put("posts", posts);
        ModelAndView modelAndView = new ModelAndView("posts/user", model);
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView getCreatePostPage() {
        ModelAndView modelAndView = new ModelAndView("posts/create");
        return modelAndView;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView getUpdatePostPage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Post post = postService.getById(id);
        List<Image> images = imageService.getImagesByPostId(post.getId());
        model.put("post", post);
        model.put("images", images);
        ModelAndView modelAndView = new ModelAndView("posts/update", model);
        return modelAndView;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPost(
            User user,
            @RequestPart(name = "post", required = false) ru.fwoods.posts.Post post,
            @RequestPart(name = "images", required = false) List<MultipartFile> images
    ) {
        postService.save(post, images, user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePost(
            @RequestPart(name = "post", required = false) ru.fwoods.posts.Post post,
            @RequestPart(name = "images", required = false) List<MultipartFile> images
    ) {
        postService.update(post, images);
        return ResponseEntity.ok().body(null);
    }
}
