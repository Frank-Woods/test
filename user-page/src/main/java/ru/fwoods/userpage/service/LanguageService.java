package ru.fwoods.userpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.entities.User;
import ru.fwoods.userpage.model.Language;
import ru.fwoods.userpage.repository.LanguageRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Page<ru.fwoods.userpage.model.Language> getLanguagesPage(Pageable pageable, List<Long> languages, String searchParam) {
        if (languages.size() > 0) {
            if (searchParam.length() > 0) {
                return languageRepository.findAllByNameContainsIgnoreCaseAndIdNotIn(searchParam, languages, pageable)
                        .map(Language::new);
            } else {
                return languageRepository.findAllByIdNotIn(languages, pageable)
                        .map(Language::new);
            }
        } else {
            if (searchParam.length() > 0) {
                return languageRepository.findAllByNameContainsIgnoreCase(searchParam, pageable)
                        .map(Language::new);
            } else {
                return languageRepository.findAll(pageable)
                        .map(Language::new);
            }
        }
    }

    public Set<Language> getLanguagesByUser(User user) {
        return user.getLanguages().stream().map(language -> new Language(language.getId(), language.getName())).collect(Collectors.toSet());
    }

    public ru.fwoods.entities.Language getLanguageById(Long id) {
        return languageRepository.getOne(id);
    }
}
