import java.util.ArrayList;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private double rating;
    private String hairType;   // straight, wavy, curly or all
    private String scalpType;  // oily, dry, normal or all
    private ArrayList<String> concerns;
    private double score;      // set by Recommendation

    public Product(int id, String name, String category, double price, double rating,
                   String hairType, String scalpType, ArrayList<String> concerns) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
        this.hairType = hairType;
        this.scalpType = scalpType;
        this.concerns = concerns;
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getHairType() {
        return hairType;
    }

    public String getScalpType() {
        return scalpType;
    }

    public ArrayList<String> getConcerns() {
        return concerns;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean treatsConcern(String concern) {
        return concerns.contains(concern);
    }

    public String getConcernText() {
        String text = "";
        for (int i = 0; i < concerns.size(); i++) {
            text = text + concerns.get(i);
            if (i < concerns.size() - 1) {
                text = text + ", ";
            }
        }
        return text;
    }

    public String toString() {
        return String.format("ID %d | %s | %s | Rs.%.2f | Rating %.1f",
                id, name, category, price, rating);
    }
}
