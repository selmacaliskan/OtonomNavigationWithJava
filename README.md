# Autonomous Navigation Safety Module: SVM Margin Maximization

This repository contains a high-precision safety module designed for autonomous vehicle navigation. The system identifies two distinct classes of obstacles in a 2D plane and calculates the **Optimal Decision Boundary** to ensure maximum clearance.

## 📸 System Demonstration
Below is the visual output of the implemented algorithm. The black line represents the primary safety boundary, while the dashed lines illustrate the maximized "safety corridor" (margin).

[Safety Module Visualization]
<img width="974" height="800" alt="image" src="https://github.com/user-attachments/assets/2984610f-88a9-4c81-a30a-34da448f89d4" />

---

## 🎯 Project Objective
The goal is to move beyond simple linear separation. In autonomous driving, any line is not enough; we require the line that is **equidistant** and **at maximum distance** from the nearest obstacles. This project utilizes **Support Vector Machines (SVM)** to achieve this mathematical optimization.

## 🛠 Software Architecture
The project is built using a **Layered Architecture** to ensure maintainability and separation of concerns:
* **`navigation.model`**: Contains the `Point` data structure.
* **`navigation.logic`**: Contains the `SVMClassifier` which implements the hinge-loss gradient descent.
* **`navigation.ui`**: Handles the Java Swing visualization and coordinates-to-pixel scaling.

## 🧠 Why is this the "Optimum" Boundary?
The boundary found by this algorithm is considered the most secure because it minimizes the risk of collision in the presence of sensor noise or slight navigation deviations. By maximizing the **geometric margin**, we provide the vehicle with the largest possible buffer zone.

## 📈 Complexity Analysis
* **Training Complexity:** $O(\text{Iterations} \times \text{Data Points})$.
* **Operational (Inference) Complexity:** $O(1)$. Once trained, the system determines if a coordinate is safe using a single linear calculation, meeting real-time processing requirements.

## 🚀 Installation & Usage
1.  Clone the repository.
2.  Open the project in an IDE (IntelliJ IDEA recommended).
3.  Run `MainApp.java` to generate a random obstacle scenario and view the calculated safety corridor.

---

### 🎓 Academic Information
* **Institution:** Kırklareli University
* **Department:** Software Engineering
* **Developer:** Selma Çalışkan


---
© 2026 Selma Çalışkan - Academic Project
