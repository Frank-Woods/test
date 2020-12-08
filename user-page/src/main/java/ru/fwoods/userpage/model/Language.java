package ru.fwoods.userpage.model;

public class Language {

    private Long id;

    private String name;

    public Language() {
    }

    public Language(ru.fwoods.entities.Language language) {
        this.id = language.getId();
        this.name = language.getName();
    }

    public Language(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
