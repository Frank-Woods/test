package ru.fwoods.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.entities.Post;
import ru.fwoods.entities.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> getAllByUser(User user);
}
