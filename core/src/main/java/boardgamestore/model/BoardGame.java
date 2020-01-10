package boardgamestore.model;

import java.time.LocalDate;
import java.util.Collection;

public class BoardGame {

    private String id;
    private String name;
    private String players;
    private String playTime;
    private int suggestedAge;
    private Collection<Category> categories;
    private Collection<Mechanism> mechanisms;
    private LocalDate releaseDate;
    private double price;
    private int quantity;

    public BoardGame(){

    }

    public BoardGame(String id, String name,
                     String players, String playTime,
                     int suggestedAge, Collection<Category> categories,
                     Collection<Mechanism> mechanisms, LocalDate releaseDate, double price) {
        setId(id);
        setName(name);
        setPlayers(players);
        setPlayTime(playTime);
        setSuggestedAge(suggestedAge);
        setCategories(categories);
        setMechanisms(mechanisms);
        setReleaseDate(releaseDate);
        setPrice(price);
        setQuantity(0);
    }

    public String getId() {
        return id;
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

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players){
        this.players = players;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime){
        this.playTime = playTime;
    }

    public int getSuggestedAge() {
        return suggestedAge;
    }

    public void setSuggestedAge(int suggestedAge) {
        this.suggestedAge = suggestedAge;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Collection<Mechanism> getMechanisms() { return mechanisms; }

    public void setMechanisms(Collection<Mechanism> mechanisms) {
        this.mechanisms = mechanisms;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
