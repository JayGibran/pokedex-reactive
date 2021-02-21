package com.jaygibran.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Pokemon {

    @Id
    private String id;

    private String name;

    private String category;

    private String skill;

    private Double weight;

    public String getId() {
        return id;
    }

    public Pokemon(String id, String name, String category, String skill, Double weight) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.skill = skill;
        this.weight = weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) &&
                Objects.equals(name, pokemon.name) &&
                Objects.equals(category, pokemon.category) &&
                Objects.equals(skill, pokemon.skill) &&
                Objects.equals(weight, pokemon.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, skill, weight);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", skill='" + skill + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
