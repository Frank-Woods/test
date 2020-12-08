package ru.fwoods.userpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.entities.Image;
import ru.fwoods.entities.Post;
import ru.fwoods.entities.User;
import ru.fwoods.userpage.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileService fileService;

    @Value("${user.upload.path}")
    private String userUploadPath;

    private String saveUserImageInFileSystem(MultipartFile file) {
        return fileService.createFile(userUploadPath, file);
    }

    private void deleteUserImageInFileSystem(String filename) {
        fileService.deleteFile(userUploadPath, filename);
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public Image saveUserImage(MultipartFile file, User user) {
        Image image = new Image();
        String filename = saveUserImageInFileSystem(file);
        image.setFilename(filename);
        image.setUser(user);
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

    public void deleteUserImage(Long id) {
        Image image = imageRepository.getOne(id);
        delete(id);
        deleteUserImageInFileSystem(image.getFilename());
    }

    public List<Image> getImagesByUserId(Long id) {
        return imageRepository.getImagesByUserId(id);
    }
}
