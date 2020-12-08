package ru.fwoods.posts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.entities.Image;
import ru.fwoods.entities.Post;
import ru.fwoods.posts.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileService fileService;

    @Value("${post.upload.path}")
    private String postUploadPath;

    private String savePostImageInFileSystem(MultipartFile file) {
        return fileService.createFile(postUploadPath, file);
    }

    private void deletePostImageInFileSystem(String filename) {
        fileService.deleteFile(postUploadPath, filename);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image savePostImage(MultipartFile file, Post post) {
        Image image = new Image();
        String filename = savePostImageInFileSystem(file);
        image.setFilename(filename);
        image.setPost(post);
        return save(image);
    }

    public void delete(Image image) {
        image.setPost(null);
        image.setUser(null);
        imageRepository.deleteById(image.getId());
    }

    public void delete(Long id) {
        Image image = imageRepository.getOne(id);
        delete(image);
    }

    public void deletePostImage(Long id) {
        Image image = imageRepository.getOne(id);
        delete(id);
        deletePostImageInFileSystem(image.getFilename());
    }

    public List<Image> getImagesByPostId(Long id) {
        return imageRepository.getImagesByPostId(id);
    }
}
