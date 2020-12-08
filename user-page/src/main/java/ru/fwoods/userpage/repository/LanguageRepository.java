package ru.fwoods.userpage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.entities.Language;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    
    Page<Language> findAllByNameContainsIgnoreCaseAndIdNotIn(String searchParam, List<Long> languages, Pageable pageable);

    Page<Language> findAllByIdNotIn(List<Long> languages, Pageable pageable);

    Page<Language> findAllByNameContainsIgnoreCase(String searchParam, Pageable pageable);
}
