package ru.fwoods.userpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.entities.City;
import ru.fwoods.entities.Image;
import ru.fwoods.entities.StatusPage;
import ru.fwoods.userpage.model.Language;
import ru.fwoods.userpage.model.User;
import ru.fwoods.userpage.repository.CityRepository;
import ru.fwoods.userpage.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private ImageService imageService;

    public void updateUser(User user) {
        ru.fwoods.entities.User userDomain = userRepository.getOne(user.getId());

        userDomain.setFirstName(user.getFirstName());
        userDomain.setLastName(user.getLastName());
        userDomain.setBirthday(user.getBirthday());
        userDomain.setEmail(user.getEmail());

        City city = cityService.getCityById(user.getCity());

        userDomain.setCity(city);

        userRepository.save(userDomain);
    }

    public void updateStatus(ru.fwoods.entities.User user, String status) {
        user.setStatus(status);

        userRepository.save(user);
    }

    public User getUserModel(Long id) {
        ru.fwoods.entities.User user = userRepository.getOne(id);

        User userModel = new User();

        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setBirthday(user.getBirthday());
        userModel.setEmail(user.getEmail());
        userModel.setCity(user.getCity().getId());

        return userModel;
    }

    public void updateLanguage(ru.fwoods.entities.User user, Set<Language> languages) {
        user.getLanguages().clear();

        languages.forEach(language -> {
            ru.fwoods.entities.Language languageDomain = languageService.getLanguageById(language.getId());
            user.getLanguages().add(languageDomain);
        });

        userRepository.save(user);
    }

    public void updateInfo(ru.fwoods.entities.User user, String info) {
        user.setInfo(info);

        userRepository.save(user);
    }

    public void updateImage(ru.fwoods.entities.User user, List<MultipartFile> images) {
        ru.fwoods.entities.User userDomain = userRepository.getOne(user.getId());

        List<Image> userImages = imageService.getImagesByUserId(userDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.saveUserImage(image, userDomain);
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

        userImages.forEach(userImage -> {
            if (!imageIds.contains(userImage.getId())) {
                imageService.deleteUserImage(userImage.getId());
            }
        });

        userRepository.save(userDomain);
    }

    public void updateStatusPage(ru.fwoods.entities.User user, Integer statusPage) {
        ru.fwoods.entities.User userDomain = userRepository.getOne(user.getId());

        for (StatusPage status : StatusPage.values()) {
            if (status.ordinal() == statusPage) {
                userDomain.setStatusPage(status);
            }
        }

        userRepository.save(userDomain);
    }
}
