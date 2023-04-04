package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.xml.internal.ws.api.model.MEP;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="game")
public class Game implements  Serializable {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameId;

    @NotNull(message = "Title should not be null.")
    @Size(max = 50, message = "Title should not be longer than 50 characters.")
    private String title;

    @NotNull(message = "ESRB Rating should not be null")
    @Size(max = 50, message = "ESRB Rating should not be longer than 50 characters.")
    private String esrbRating;

    @NotNull(message = "Price should not be null.")
    @Digits(integer=3, fraction=2, message = "Price cannot have more then five numbers or more than two after the decimal.")
    private BigDecimal price;

    @NotNull(message = "Description should not be null.")
    @Size(max = 255, message = "Description should not be longer than 255 characters.")
    private String description;

    @NotNull(message = "Studio cannot be null.")
    @Size(max = 50, message = "Studio should not be longer than 50 characters.")
    private String studio;

    private int quantity;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGameId() == game.getGameId() && getQuantity() == game.getQuantity() && Objects.equals(getTitle(), game.getTitle()) && Objects.equals(getEsrbRating(), game.getEsrbRating()) && Objects.equals(getPrice(), game.getPrice()) && Objects.equals(getDescription(), game.getDescription())  && Objects.equals(getStudio(), game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getPrice(), getDescription(), getStudio(), getQuantity());
    }
}
