package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Game Model that will be used in a Game Store Application.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="game")
public class Game implements  Serializable {

    /**
     * Instance variables for the Console Model. Includes
     * Annotations that match restraints set by the database.
     */
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

    /**
     * Getter for the gameId of the Game Object.
     * @return Returns an int, the gameId of the Game Object.
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Setter for the gameId of the Game Object.
     * @param gameId int taken in to set the gameId of the Game Object.
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Getter for the title of the Game Object.
     * @return Returns a String, the title of the Game Object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the Game Object.
     * @param title String taken in to set the title of the
     *              Game Object.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the esrbRating of the Game Object.
     * @return Returns a String, the esrbRating of the
     * Game Object.
     */
    public String getEsrbRating() {
        return esrbRating;
    }

    /**
     * Setter for the esrbRating of the Game Object.
     * @param esrbRating String taken in to set the esrbRating
     *                   of the Game Object.
     */
    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    /**
     * Getter for the price of the Game Object.
     * @return Returns a BigDecimal, the price of the Game Object.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter for the price of the Game Object.
     * @param price BigDecimal taken in to set the price of the
     *              Game Object.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter for the description of the Game object.
     * @return Returns a String, the Description of the
     *         Game Object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the Game Object.
     * @param description String taken in to set the description
     *                    of the Game Object.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the studio of the Game Object.
     * @return Returns a String, the studio of the Game Object.
     */
    public String getStudio() {
        return studio;
    }

    /**
     * Setter for the studio of the Game Object.
     * @param studio String taken in to set the studio of the
     *               Game Object.
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * Getter for the quantity of the Game Object.
     * @return Returns the quantity of the Game Object.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of the Game Object.
     * @param quantity int taken in to set the quantity of the Game Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * equals() for the Game Object. Will be used for comparison.
     * @param o Object o being used for comparison.
     * @return Returns true if the Object calling the method equals
     *         Object o, else it will return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGameId() == game.getGameId() && getQuantity() == game.getQuantity() && Objects.equals(getTitle(), game.getTitle()) && Objects.equals(getEsrbRating(), game.getEsrbRating()) && Objects.equals(getPrice(), game.getPrice()) && Objects.equals(getDescription(), game.getDescription())  && Objects.equals(getStudio(), game.getStudio());
    }

    /**
     * hashCode() for the Game Object. Can be used for comparison.
     * @return Returns the Game Object as an int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getPrice(), getDescription(), getStudio(), getQuantity());
    }

    /**
     * toString() for the Game Object.
     * @return Returns the Game Object as a String.
     */
    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
