# HairVeda - Personalized Hair Care Recommendation System

A console based Java project that asks the user a few questions about their hair,
finds the hair concerns using simple rules, and recommends products from a small
catalog. The user can also search products, add them to a cart and generate a bill.

Made as a Java DSA mini project. No external library, no build tool - only plain Java.

## How to run

Java 17 or 21 is needed. Open a terminal inside the HairVeda folder and run:

    javac *.java
    java Main

## Features (menu options)

1. Create User Profile
2. Hair Care Questionnaire (7 questions, can go back with 0)
3. Hair Diagnosis (hair type, scalp type, concerns + small tips)
4. Personalized Recommendations (top 8 ranked products)
5. View All Products
6. Search Products (by name, category or concern)
7. Add to Cart
8. Remove from Cart
9. Update Quantity
10. View Cart
11. Generate Bill (coupon + delivery charge)
12. Exit

Normal flow: 1 -> 2 -> 3 -> 4 -> 7 -> 10 -> 11.

## Data structures used

| Data structure | Where | Why |
|---|---|---|
| ArrayList | `ProductData` (catalog), `HairProfile` (concerns), `Cart` (cart items) | Stores a list of objects, easy to loop and search |
| PriorityQueue | `Recommendation.rankProducts()` | Product with the highest score always comes out first |
| Stack | `Diagnosis.askQuestions()` | Remembers answered questions so the user can go back (type 0) |

### How the Stack works
After each answer the question number is pushed on the stack. When the user types 0,
we pop the stack and go to that question again. If the stack is empty, the user is
on the first question and cannot go back.

### How the PriorityQueue works
Every product that matches at least one concern gets a score and is added to the queue.
A Comparator makes the higher score come first. Then we use `poll()` to take out
the best products one by one.

## Diagnosis (rule based)

The answers are checked with simple if-else rules.

- Flakes on scalp -> `dandruff`
- Hair fall -> `hairfall`
- Dry and rough hair -> `dryness`
- Frizzy hair -> `frizz`
- Split ends / damage -> `damage`
- Extra rule 1: dry scalp + dandruff -> also add `dryness`
- Extra rule 2: curly hair + dryness -> also add `frizz`

A user can have more than one concern.

## Scoring formula

| Condition | Points |
|---|---|
| Each matching concern | +10 |
| Hair type matches (product made for "all" types) | +5 (+2) |
| Scalp type matches (product made for "all" scalps) | +5 (+2) |
| Product rating | + rating (max 5) |

If a product matches no concern, its score is 0 and it is not recommended.

Example: user has curly hair, dry scalp, concerns = frizz and dryness.
Hibiscus Smoothing Conditioner (curly, all scalps, frizz + dryness, rating 4.0):
20 + 5 + 2 + 4.0 = 31.0

## Billing

- Coupon code `HAIR10` gives 10% discount.
- Delivery charge is Rs.49. It is free if the amount after discount is Rs.499 or more.

## Folder structure

    HairVeda/
    ├── Main.java            menu and user interaction
    ├── User.java            name, username, age and hair profile
    ├── HairProfile.java     hair type, scalp type, concerns, answers
    ├── Product.java         product details
    ├── ProductData.java     20 sample products, view and search
    ├── Diagnosis.java       questionnaire (Stack) and diagnosis rules
    ├── Recommendation.java  scoring and ranking (PriorityQueue)
    ├── CartItem.java        product + quantity
    ├── Cart.java            add, remove, update, display, subtotal
    ├── Billing.java         discount, delivery charge, final bill
    └── README.md

## Sample run (short)

    Hair type  : curly
    Scalp type : dry
    Concerns   : [dandruff, hairfall, dryness, frizz]

    1. ID 7 | Hibiscus Smoothing Conditioner | Conditioner | Rs.299.00 | Rating 4.0
          Helps with: frizz, dryness  |  Score: 31.0
    2. ID 6 | Argan Moisturizing Conditioner | Conditioner | Rs.349.00 | Rating 4.5
          Helps with: dryness, frizz  |  Score: 28.5

## Possible viva questions

- **Why PriorityQueue for recommendations?** Because we always want the highest score
  product first. Insertion is O(log n) and removing the best one is O(log n).
- **Why Stack for the questionnaire?** Going back means returning to the most recent
  question, which is last-in-first-out.
- **Why ArrayList for the cart?** The cart size is not fixed, and we need to loop
  through items and remove some of them.
- **What is the time complexity of search?** O(n), we check every product once.
- **What would you improve?** Save data in a file, add more products, and use a
  database.

## Limitations

- Data is not saved. Cart and profile are lost when the program closes.
- The catalog has only 20 sample products.
- Only one coupon code is supported.
