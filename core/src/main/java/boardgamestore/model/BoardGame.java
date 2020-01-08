package boardgamestore.model;

import java.time.LocalDate;
import java.util.Collection;

public class BoardGame {

    private String id;
    private String name;
    private int[] players;
    private int[] playTime;
    private int suggestedAge;
    private Collection<Category> categories;
    private Collection<Mechanism> mechanisms;
    private LocalDate releaseDate;
    private double price;
    private int quantity;

    public BoardGame(String id, String name,
                     int fromNumPlayers, int toNumPlayers, int fromTime, int toTime,
                     int suggestedAge, Collection<Category> categories,
                     Collection<Mechanism> mechanisms, LocalDate releaseDate, double price) {
        setId(id);
        setName(name);
        setPlayers(fromNumPlayers, toNumPlayers);
        setPlayTime(fromTime,toTime);
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

    public int[] getPlayers() {
        return players;
    }

    public void setPlayers(int from, int to) {
        int[] players = new int[]{from,to};
        this.players = players;
    }

    public void setPlayers(int[] players){
        this.players = players;
    }

    public int[] getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int from, int to) {
        int[] playTime = new int[]{from,to};
        this.playTime = playTime;
    }

    public void setPlayTime(int[] playTime){
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

    public Collection<Mechanism> getMechanisms() {
        return mechanisms;
    }

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
