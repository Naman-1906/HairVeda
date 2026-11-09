import java.util.ArrayList;

public class Cart {
    // Keep one entry per product and store its item count in the CartItem.
    private ArrayList<CartItem> items = new ArrayList<CartItem>();

    private CartItem findItem(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                return items.get(i);
            }
        }
        return null;
    }

    public void addProduct(Product product, int quantity) {
        CartItem item = findItem(product.getId());
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public boolean removeProduct(int productId) {
        CartItem item = findItem(productId);
        if (item == null) {
            return false;
        }
        items.remove(item);
        return true;
    }

    public boolean updateQuantity(int productId, int quantity) {
        CartItem item = findItem(productId);
        if (item == null) {
            return false;
        }
        item.setQuantity(quantity);
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double total = 0;
        // Each item total already includes its quantity.
        for (int i = 0; i < items.size(); i++) {
            total = total + items.get(i).getTotalPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n--- Your Cart ---");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            System.out.printf("%d. [ID %d] %s | Rs.%.2f x %d = Rs.%.2f%n",
                    i + 1, item.getProduct().getId(), item.getProduct().getName(),
                    item.getProduct().getPrice(), item.getQuantity(), item.getTotalPrice());
        }
        System.out.printf("Subtotal: Rs.%.2f%n", getSubtotal());
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
}
