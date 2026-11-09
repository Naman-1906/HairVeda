import java.util.ArrayList;

public class ProductData {
    private ArrayList<Product> products = new ArrayList<Product>();

    public ProductData() {
        loadProducts();
    }

    //  comma separated string
    private void addProduct(int id, String name, String category, double price, double rating,
                            String hairType, String scalpType, String concerns) {
        ArrayList<String> concernList = new ArrayList<String>();
        String[] parts = concerns.split(",");
        for (int i = 0; i < parts.length; i++) {
            concernList.add(parts[i]);
        }
        products.add(new Product(id, name, category, price, rating, hairType, scalpType, concernList));
    }

    private void loadProducts() {
        addProduct(1, "Neem Anti-Dandruff Shampoo", "Shampoo", 249, 4.3, "all", "oily", "dandruff");
        addProduct(2, "Tea Tree Scalp Cleanser", "Shampoo", 329, 4.1, "all", "oily", "dandruff");
        addProduct(3, "Aloe Vera Moisture Shampoo", "Shampoo", 279, 4.0, "all", "dry", "dryness");
        addProduct(4, "Shikakai Reetha Gentle Shampoo", "Shampoo", 199, 4.2, "all", "normal", "hairfall");
        addProduct(5, "Onion Hair Fall Control Shampoo", "Shampoo", 299, 4.4, "all", "all", "hairfall");
        addProduct(6, "Argan Moisturizing Conditioner", "Conditioner", 349, 4.5, "all", "all", "dryness,frizz");
        addProduct(7, "Hibiscus Smoothing Conditioner", "Conditioner", 299, 4.0, "curly", "all", "frizz,dryness");
        addProduct(8, "Coconut Milk Deep Conditioner", "Conditioner", 379, 4.2, "wavy", "dry", "dryness,damage");
        addProduct(9, "Anti-Frizz Smoothing Serum", "Serum", 399, 4.3, "curly", "all", "frizz");
        addProduct(10, "Silk Shine Serum", "Serum", 449, 4.1, "straight", "all", "frizz");
        addProduct(11, "Bhringraj Hair Oil", "Oil", 259, 4.6, "all", "all", "hairfall");
        addProduct(12, "Amla Strengthening Oil", "Oil", 219, 4.4, "all", "normal", "hairfall,damage");
        addProduct(13, "Cold Pressed Coconut Oil", "Oil", 189, 4.2, "all", "dry", "dryness");
        addProduct(14, "Neem Tulsi Scalp Oil", "Oil", 239, 4.0, "all", "oily", "dandruff");
        addProduct(15, "Brahmi Repair Hair Mask", "Mask", 499, 4.5, "all", "all", "damage,dryness");
        addProduct(16, "Fenugreek Hair Mask", "Mask", 349, 4.0, "wavy", "all", "hairfall");
        addProduct(17, "Curl Defining Cream", "Styling", 429, 4.3, "curly", "all", "frizz");
        addProduct(18, "Heat Protect Spray", "Styling", 379, 4.1, "straight", "all", "damage");
        addProduct(19, "Rosemary Hair Growth Serum", "Serum", 549, 4.2, "all", "all", "hairfall");
        addProduct(20, "Apple Cider Scalp Rinse", "Rinse", 299, 3.9, "all", "oily", "dandruff");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return products.get(i);
            }
        }
        return null;
    }

    public void showAllProducts() {
        System.out.println("\n--- All Products ---");
        showList(products);
    }

    // searches in name, category and concerns
    public ArrayList<Product> searchProducts(String keyword) {
        ArrayList<Product> result = new ArrayList<Product>();
        keyword = keyword.toLowerCase();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getName().toLowerCase().contains(keyword)
                    || p.getCategory().toLowerCase().contains(keyword)
                    || p.getConcernText().contains(keyword)) {
                result.add(p);
            }
        }
        return result;
    }

    public void showList(ArrayList<Product> list) {
        if (list.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            Product p = list.get(i);
            System.out.println(p);
            System.out.println("      Helps with: " + p.getConcernText());
        }
    }
}
