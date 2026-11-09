# 🌿 HairVeda
### Personalized Hair Care Recommendation System

<p align="center">
  A Java-based hair care assistant that analyzes hair concerns, recommends suitable products, and manages a shopping cart with billing.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java" />
  <img src="https://img.shields.io/badge/Project-DSA%20Mini%20Project-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/Status-Completed-success?style=flat-square" />
</p>

---

## 📌 About the Project

HairVeda is a console-based hair care recommendation system developed using Java. It helps users understand their hair concerns through a questionnaire and suggests suitable products based on their hair type, scalp type, and identified concerns.

The project combines basic hair diagnosis rules with data structures and algorithms to provide personalized recommendations and a simple shopping experience.

## ✨ Features

- **User Profile** – Create a profile with basic user details.
- **Hair Questionnaire** – Answer seven questions about hair and scalp conditions.
- **Hair Diagnosis** – Identify hair concerns using rule-based conditions.
- **Personalized Recommendations** – Rank suitable products based on matching scores.
- **Product Catalog** – Browse and search products by name, category, or concern.
- **Shopping Cart** – Add, remove, and update product quantities.
- **Billing System** – Generate bills with discount coupons and delivery charges.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Core application development |
| ArrayList | Store products, concerns, and cart items |
| Stack | Navigate backward in the questionnaire |
| PriorityQueue | Rank products based on recommendation scores |
| Comparator | Compare product recommendation scores |

## 🧠 How Recommendations Work

HairVeda uses a simple rule-based diagnosis and score-based product ranking system.

1. The user answers questions about hair type, scalp type, and hair concerns.
2. The system identifies concerns such as dandruff, hair fall, dryness, frizz, and damage.
3. Each product receives a score based on matching concerns, hair type, scalp type, and rating.
4. A `PriorityQueue` arranges products according to their recommendation scores.
5. The highest-ranked products are displayed first.

## 🛒 Shopping & Billing

- Add products to the shopping cart.
- Remove products or update quantities.
- View cart items and subtotal.
- Apply the `HAIR10` coupon for a 10% discount.
- Calculate delivery charges automatically.
- Generate the final bill.

## 📂 Project Structure

```text
HairVeda/
│
├── Main.java
├── User.java
├── HairProfile.java
├── Diagnosis.java
├── Recommendation.java
├── Product.java
├── ProductData.java
├── Cart.java
├── CartItem.java
├── Billing.java
├── README.md
└── .gitignore
```

## 🚀 Getting Started

### Prerequisites

- Java JDK 17 or later
- VS Code or any Java-supported IDE
- Git (optional)

### Installation & Execution

**1. Clone the repository**

```bash
git clone https://github.com/Naman-1906/HairVeda.git
```

**2. Navigate to the project folder**

```bash
cd HairVeda
```

**3. Compile the Java files**

```bash
javac *.java
```

**4. Run the application**

```bash
java Main
```

## 🎯 Learning Outcomes

Through this project, I practiced:

- Applying Java OOP concepts in a practical application.
- Implementing Stack, ArrayList, and PriorityQueue.
- Using Comparator for custom sorting.
- Working with object-based product and cart management.
- Implementing rule-based logic and score-based recommendations.
- Understanding basic billing and shopping cart workflows.

## 🔮 Future Improvements

- Add more hair care products and recommendation rules.
- Store user profiles and cart data permanently.
- Develop a graphical user interface.
- Improve recommendation accuracy with additional hair care information.

## 👨‍💻 Developer

**Naman Gupta**

B.Tech CSE Student

[GitHub Profile](https://github.com/Naman-1906)

---

<p align="center">
  Made with ☕ and Java
</p>
