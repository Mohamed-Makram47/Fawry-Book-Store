# 📚 Quantum Bookstore Challenge – Java OOP

A simple object-oriented bookstore management system written in **Java**, built as a solution for the **Fawry internship challenge**.

This project models different book types (physical, electronic, and showcase-only), allows adding/removing books, simulates purchases, and demonstrates core **OOP principles** like inheritance, abstraction, and polymorphism.

---

## ✅ Features

### 📘 Book Types (OOP with inheritance)
- `PaperBook`: has stock, can be shipped to a physical address.
- `EBook`: has a file type, sent via email.
- `ShowcaseBook`: for display only — cannot be purchased.

### 🛒 Inventory System
- Add books to the inventory.
- Remove outdated books by year.
- Prevent duplicate ISBNs.

### 💳 Purchase Flow
- Buy books by ISBN (with quantity).
- Simulates:
  - Shipping for paper books.
  - Email delivery for ebooks.
- Gracefully handles:
  - Out-of-stock cases.
  - Showcase books (not for sale).
  - Invalid ISBNs.

---
### Test Cases
![test cases](https://github.com/user-attachments/assets/341cccd8-c127-49f6-bf43-e03a79fefd03)
### Output

![output](https://github.com/user-attachments/assets/51327fd2-7477-4f02-b0cf-e91169dc5618)
