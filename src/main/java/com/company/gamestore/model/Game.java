package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="game")
public class Game {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int game_id;

    @NotNull
    private String title;

    @NotNull
    private String esrb_rating;

    @NotNull
    private String description;

    @NotNull
    private float decimal;

    @NotNull
    private String studio;

    private int quantity;

    public Game() {
    }

    public Game(int game_id, String title, String esrb_rating, String description, float decimal, String studio, int quantity) {
        this.game_id = game_id;
        this.title = title;
        this.esrb_rating = esrb_rating;
        this.description = description;
        this.decimal = decimal;
        this.studio = studio;
        this.quantity = quantity;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(String esrb_rating) {
        this.esrb_rating = esrb_rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDecimal() {
        return decimal;
    }

    public void setDecimal(float decimal) {
        this.decimal = decimal;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getGame_id() == game.getGame_id() && Float.compare(game.getDecimal(), getDecimal()) == 0 && getQuantity() == game.getQuantity() && getTitle().equals(game.getTitle()) && getEsrb_rating().equals(game.getEsrb_rating()) && getDescription().equals(game.getDescription()) && getStudio().equals(game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame_id(), getTitle(), getEsrb_rating(), getDescription(), getDecimal(), getStudio(), getQuantity());
    }
}
