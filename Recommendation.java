import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Recommendation {

    // Score rules:
    //   each matching concern  = 10
    //   hair type matches      = 5  (product for "all" types = 2)
    //   scalp type matches     = 5  (product for "all" scalps = 2)
    //   product rating         = added as it is (max 5)
    // a product which does not match any concern gets 0 and is not recommended
    public double calculateScore(Product product, HairProfile profile) {
        int matches = 0;
        for (int i = 0; i < profile.getConcerns().size(); i++) {
            if (product.treatsConcern(profile.getConcerns().get(i))) {
                matches++;
            }
        }
        if (matches == 0) {
            return 0;
        }

        double score = matches * 10;

        if (product.getHairType().equals(profile.getHairType())) {
            score = score + 5;
        } else if (product.getHairType().equals("all")) {
            score = score + 2;
        }

        if (product.getScalpType().equals(profile.getScalpType())) {
            score = score + 5;
        } else if (product.getScalpType().equals("all")) {
            score = score + 2;
        }

        score = score + product.getRating();
        return score;
    }

    // PriorityQueue keeps the highest score product at the front
    public PriorityQueue<Product> rankProducts(ArrayList<Product> products, HairProfile profile) {
        PriorityQueue<Product> queue = new PriorityQueue<Product>(new Comparator<Product>() {
            public int compare(Product a, Product b) {
                return Double.compare(b.getScore(), a.getScore());
            }
        });

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            double score = calculateScore(p, profile);
            if (score > 0) {
                p.setScore(score);
                queue.add(p);
            }
        }
        return queue;
    }

    public void showRecommendations(PriorityQueue<Product> queue, HairProfile profile) {
        System.out.println("\n--- Recommended Products For You ---");
        if (queue.isEmpty()) {
            System.out.println("No specific products needed for your hair right now.");
            System.out.println("You can still browse all products from the menu.");
            return;
        }

        ArrayList<String> covered = new ArrayList<String>();
        int rank = 1;
        while (!queue.isEmpty() && rank <= 8) {
            Product p = queue.poll();
            System.out.println(rank + ". " + p);
            System.out.println("      Helps with: " + p.getConcernText() + "  |  Score: " + String.format("%.1f", p.getScore()));
            for (int i = 0; i < p.getConcerns().size(); i++) {
                if (!covered.contains(p.getConcerns().get(i))) {
                    covered.add(p.getConcerns().get(i));
                }
            }
            rank++;
        }

        // a concern may not appear in the top products, so tell the user
        for (int i = 0; i < profile.getConcerns().size(); i++) {
            String concern = profile.getConcerns().get(i);
            if (!covered.contains(concern)) {
                System.out.println("\nTip: no top product above is for " + concern
                        + ". Search '" + concern + "' (option 6) to see products for it.");
            }
        }
        System.out.println("\nNote the product ID and use 'Add to Cart' to buy.");
    }
}
