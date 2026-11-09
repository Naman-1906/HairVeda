public class Billing {
    private Cart cart;
    private double discountPercent = 0;

    public Billing(Cart cart) {
        this.cart = cart;
    }

    // coupon code: HAIR10 gives 10% off
    public boolean applyCoupon(String code) {
        if (code.equalsIgnoreCase("HAIR10")) {
            discountPercent = 10;
            return true;
        }
        return false;
    }

    public double getDiscountAmount() {
        return cart.getSubtotal() * discountPercent / 100;
    }

    // free delivery if the amount after discount is Rs.499 or more
    public double getDeliveryCharge() {
        double amount = cart.getSubtotal() - getDiscountAmount();
        if (amount >= 499) {
            return 0;
        }
        return 49;
    }

    public double getTotal() {
        return cart.getSubtotal() - getDiscountAmount() + getDeliveryCharge();
    }

    public void showBill(String customerName) {
        System.out.println("\n=========== HAIRVEDA BILL ===========");
        System.out.println("Customer: " + customerName);
        System.out.println("-------------------------------------");
        for (int i = 0; i < cart.getItems().size(); i++) {
            CartItem item = cart.getItems().get(i);
            System.out.printf("%s x %d = Rs.%.2f%n",
                    item.getProduct().getName(), item.getQuantity(), item.getTotalPrice());
        }
        System.out.println("-------------------------------------");
        System.out.printf("%-16s: Rs.%.2f%n", "Subtotal", cart.getSubtotal());
        System.out.printf("%-16s: - Rs.%.2f%n", "Discount (" + (int) discountPercent + "%)", getDiscountAmount());
        System.out.printf("%-16s: Rs.%.2f%n", "Delivery charge", getDeliveryCharge());
        System.out.println("-------------------------------------");
        System.out.printf("%-16s: Rs.%.2f%n", "TOTAL TO PAY", getTotal());
        System.out.println("=====================================");
    }
}
