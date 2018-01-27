package se.kjellstrand.tlmfruits.model;

public class Fruit {
    public int id;
    public String type;
    public int vitamins;
    public String image;

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", vitamins=" + vitamins +
                ", image='" + image + '\'' +
                '}';
    }
}
