import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    ProductData productData = new ProductData();
    Diagnosis diagnosis = new Diagnosis();
    Recommendation recommendation = new Recommendation();
    Cart cart = new Cart();
    User user = null;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    // reads a number between min and max, asks again if input is wrong
    public static int readNumber(Scanner sc, int min, int max) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                int number = Integer.parseInt(line);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.print("Please enter a number from " + min + " to " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    String readText() {
        String text = sc.nextLine().trim();
        while (text.isEmpty()) {
            System.out.print("This cannot be empty. Try again: ");
            text = sc.nextLine().trim();
        }
        return text;
    }

    void start() {
        System.out.println("=====================================");
        System.out.println("  HairVeda - Hair Care Recommender");
        System.out.println("=====================================");

        int choice = 0;
        while (choice != 12) {
            showMenu();
            choice = readNumber(sc, 1, 12);

            switch (choice) {
                case 1:
                    createProfile();
                    break;
                case 2:
                    startQuestionnaire();
                    break;
                case 3:
                    showDiagnosis();
                    break;
                case 4:
                    showRecommendations();
                    break;
                case 5:
                    productData.showAllProducts();
                    break;
                case 6:
                    searchProducts();
                    break;
                case 7:
                    addToCart();
                    break;
                case 8:
                    removeFromCart();
                    break;
                case 9:
                    updateQuantity();
                    break;
                case 10:
                    cart.displayCart();
                    break;
                case 11:
                    generateBill();
                    break;
                case 12:
                    System.out.println("Thank you for using HairVeda. Take care of your hair!");
                    break;
            }
        }
    }

    void showMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println(" 1. Create User Profile");
        System.out.println(" 2. Hair Care Questionnaire");
        System.out.println(" 3. Hair Diagnosis");
        System.out.println(" 4. Personalized Recommendations");
        System.out.println(" 5. View All Products");
        System.out.println(" 6. Search Products");
        System.out.println(" 7. Add to Cart");
        System.out.println(" 8. Remove from Cart");
        System.out.println(" 9. Update Quantity");
        System.out.println("10. View Cart");
        System.out.println("11. Generate Bill");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    boolean hasUser() {
        if (user == null) {
            System.out.println("Please create your profile first (option 1).");
            return false;
        }
        return true;
    }

    void createProfile() {
        System.out.print("Enter your name: ");
        String name = readText();
        System.out.print("Choose a username: ");
        String username = readText();
        System.out.print("Enter your age: ");
        int age = readNumber(sc, 10, 100);

        user = new User(name, username, age);
        System.out.println("Profile created. Welcome, " + user.getName() + "!");
    }

    void startQuestionnaire() {
        if (!hasUser()) {
            return;
        }
        diagnosis.askQuestions(sc, user.getHairProfile());
    }

    void showDiagnosis() {
        if (!hasUser()) {
            return;
        }
        HairProfile profile = user.getHairProfile();
        if (!profile.isQuestionnaireDone()) {
            System.out.println("Please complete the questionnaire first (option 2).");
            return;
        }
        diagnosis.diagnose(profile);
        diagnosis.showDiagnosis(profile);
    }

    void showRecommendations() {
        if (!hasUser()) {
            return;
        }
        HairProfile profile = user.getHairProfile();
        if (!profile.isDiagnosed()) {
            System.out.println("Please complete the diagnosis first (options 2 and 3).");
            return;
        }
        PriorityQueue<Product> ranked = recommendation.rankProducts(productData.getProducts(), profile);
        recommendation.showRecommendations(ranked, profile);
    }

    void searchProducts() {
        System.out.print("Enter name, category or concern to search (e.g. shampoo, dandruff): ");
        String keyword = readText();
        ArrayList<Product> result = productData.searchProducts(keyword);
        System.out.println("\n--- Search Results for '" + keyword + "' ---");
        productData.showList(result);
    }

    void addToCart() {
        System.out.print("Enter product ID to add: ");
        int id = readNumber(sc, 1, 1000);
        Product product = productData.getProductById(id);
        if (product == null) {
            System.out.println("No product found with ID " + id + ".");
            return;
        }
        System.out.print("Enter quantity (1 to 10): ");
        int quantity = readNumber(sc, 1, 10);
        cart.addProduct(product, quantity);
        System.out.println(quantity + " x " + product.getName() + " added to cart.");
    }

    void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        cart.displayCart();
        System.out.print("Enter product ID to remove: ");
        int id = readNumber(sc, 1, 1000);
        if (cart.removeProduct(id)) {
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("This product is not in your cart.");
        }
    }

    void updateQuantity() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        cart.displayCart();
        System.out.print("Enter product ID to update: ");
        int id = readNumber(sc, 1, 1000);
        System.out.print("Enter new quantity (1 to 10): ");
        int quantity = readNumber(sc, 1, 10);
        if (cart.updateQuantity(id, quantity)) {
            System.out.println("Quantity updated.");
        } else {
            System.out.println("This product is not in your cart.");
        }
    }

    void generateBill() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add some products first.");
            return;
        }
        Billing billing = new Billing(cart);

        System.out.print("Enter coupon code (or press Enter to skip): ");
        String code = sc.nextLine().trim();
        if (!code.isEmpty()) {
            if (billing.applyCoupon(code)) {
                System.out.println("Coupon applied!");
            } else {
                System.out.println("Invalid coupon code. No discount applied.");
            }
        }

        String customerName = "Guest";
        if (user != null) {
            customerName = user.getName();
        }
        billing.showBill(customerName);
    }
}
