package ru.fwoods.userpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.fwoods.entities.*;
import ru.fwoods.userpage.service.CityService;
import ru.fwoods.userpage.service.ImageService;
import ru.fwoods.userpage.service.LanguageService;
import ru.fwoods.userpage.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/page")
    public ModelAndView getUserPage(
            User user,
            Map<String, Object> model
    ) {
        model.put("user", user);
        ModelAndView modelAndView = new ModelAndView("user/page", model);
        return modelAndView;
    }

    @GetMapping(value = "/page/update")
    public ModelAndView getUpdatePage(
            User user,
            Map<String, Object> model
    ) {
        ru.fwoods.userpage.model.User userModel = userService.getUserModel(user.getId());
        City city = cityService.getCityById(userModel.getCity());
        List<City> cities = cityService.getAllCities();

        model.put("user", user);
        model.put("city", city);
        model.put("cities", cities);
        ModelAndView modelAndView = new ModelAndView("user/page/update", model);
        return modelAndView;
    }

    @GetMapping(value = "/page/update/languages")
    public ModelAndView getUpdateLanguagesPage(
            User user,
            Map<String, Object> model
    ) {
        Set<ru.fwoods.userpage.model.Language> languages = languageService.getLanguagesByUser(user);
        model.put("languages", languages);
        ModelAndView modelAndView = new ModelAndView("user/page/update/languages", model);
        return modelAndView;
    }

    @GetMapping(value = "/page/update/info")
    public ModelAndView getUpdateInfoPage() {
        ModelAndView modelAndView = new ModelAndView("user/page/update/info");
        return modelAndView;
    }

    @GetMapping(value = "/page/update/images")
    public ModelAndView getUpdateImagesPage(
            User user,
            Map<String, Object> model
    ) {
        List<Image> images = imageService.getImagesByUserId(user.getId());
        model.put("images", images);
        ModelAndView modelAndView = new ModelAndView("user/page/update/images", model);
        return modelAndView;
    }

    @GetMapping(value = "/page/update/education")
    public ModelAndView getUpdateEducationPage() {
        ModelAndView modelAndView = new ModelAndView("user/page/education");
        return modelAndView;
    }

    @GetMapping(value = "/page/update/higher_education")
    public ModelAndView getUpdateHigherEducationPage() {
        ModelAndView modelAndView = new ModelAndView("user/page/higher_education");
        return modelAndView;
    }

    @PostMapping(value = "/page/update")
    public ResponseEntity updateUser(
            @RequestPart(name = "user") ru.fwoods.userpage.model.User user
    ) {
        userService.updateUser(user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/page/update/status")
    public ResponseEntity updateStatus(
            User user,
            @RequestPart(name = "status") String status
    ) {
        userService.updateStatus(user, status);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/page/update/languages")
    public ResponseEntity updateLanguages(
            User user,
            @RequestPart(name = "languages") Set<ru.fwoods.userpage.model.Language> languages
    ) {
        userService.updateLanguage(user, languages);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/page/update/info")
    public ResponseEntity updateInfo(
            User user,
            @RequestPart(name = "info") String info
    ) {
        userService.updateInfo(user, info);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/page/update/images")
    public ResponseEntity updateImages(
            User user,
            @RequestPart(name = "images", required = false) List<MultipartFile> images
    ) {
        userService.updateImage(user, images);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/page/update/statusPage/")
    public ResponseEntity updateStatusPage(
            User user,
            @RequestPart(name = "status", required = false) Integer statusPage
    ) {
        userService.updateStatusPage(user, statusPage);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/get/languages")
    public ResponseEntity getLanguages(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "2") Integer pageSize,
            @RequestParam(required = false, name = "excluded") List<Long> languages,
            @RequestParam(required = false, name = "searchParam", defaultValue = "") String searchParam
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ru.fwoods.userpage.model.Language> languagePage = languageService.getLanguagesPage(pageable, languages, searchParam);
        return ResponseEntity.ok().body(languagePage);
    }
}
