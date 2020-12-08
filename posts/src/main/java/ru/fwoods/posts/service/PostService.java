package ru.fwoods.posts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.entities.Image;
import ru.fwoods.entities.Post;
import ru.fwoods.entities.User;
import ru.fwoods.posts.repository.PostRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageService imageService;

    public Post getById(Long id) {
        return postRepository.getOne(id);
    }

    public void save(ru.fwoods.posts.Post post, List<MultipartFile> images, User user) {
        Post postDomain = new Post();

        postDomain.setText(post.getText());
        postDomain.setDate(new Date());
        postDomain.setUser(user);

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> imageService.savePostImage(image, postDomain));
        }

        postRepository.save(postDomain);
    }

    public void update(ru.fwoods.posts.Post post, List<MultipartFile> images) {
        Post postDomain = postRepository.getOne(post.getId());

        postDomain.setText(post.getText());

        List<Image> postImages = imageService.getImagesByPostId(postDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.savePostImage(image, postDomain);
                } else {
                    try {
                        Long id = Long.parseLong(image.getOriginalFilename());
                        imageIds.add(id);
                    } catch (NullPointerException | NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        postImages.forEach(postImage -> {
            if (!imageIds.contains(postImage.getId())) {
                imageService.deletePostImage(postImage.getId());
            }
        });

        postRepository.save(postDomain);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllByUser(User user) {
        return postRepository.getAllByUser(user);
    }
}
