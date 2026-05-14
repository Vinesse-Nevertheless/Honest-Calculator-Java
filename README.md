# Honest Calculator 🧮

A Java-based CLI application that doesn't just do math—it judges how you use it. 

**Honest Calculator** is a Java-based CLI application designed to explore complex conditional logic, state machine management, and user-interaction flow. 

Unlike a standard calculator, this project implements an **"opinionated" interface** that evaluates user behavior—triggering specific feedback loops for "lazy" mathematical operations (like multiplying by 1) and requiring multi-stage confirmation when attempting to store low-value results in persistent memory. Developed through five iterative stages, it demonstrates a clear transition from procedural scripting to a decoupled, Object-Oriented architecture.


## 🌟 Key Features

- **Behavioral Analysis:** Includes a `LazinessEvaluator` that analyzes your equations and calls you out for "lazy" math (e.g., adding 0, multiplying by 1, or using single-digit numbers).
- **The Confirmation Gauntlet:** Implements a triple-confirmation loop for storing single-digit numbers, testing the user's persistence and patience.
- **Persistent Memory:** Uses a `HashMap` structure to store and recall results (`M`) across multiple calculation cycles.
- **Robust Validation:** Features comprehensive regex-based input filtering and division-by-zero recovery using `Double.isInfinite` checks.
- **Refactored Architecture:** Showcases a clean transition from monolithic code to a modular design using specialized helper classes.

---

## 🏗️ Architecture & Design Patterns

The project is structured to demonstrate **Separation of Concerns**, ensuring each class has a singular, well-defined responsibility:

| Class | Responsibility |
| :--- | :--- |
| **`Main`** | The Orchestrator. Manages the primary execution loop and top-level memory state. |
| **`Validator`** | Data Integrity. Handles regex filtering, type checking, and mathematical boundary checks. |
| **`Requester`** | I/O Management. Encapsulates all `Scanner` interactions and the "Confirmation Gauntlet" logic. |
| **`LazinessEvaluator`** | Logic-Gate System. Identifies "lazy" operations based on operand values and operators. |

---

## 🛠️ Technical Evolution

### Stage 1 & 2: Procedural Foundations
The initial focus was on building a "bouncer" for the application. This stage established the core sarcasm of the calculator and basic arithmetic operations, while introducing logic to catch "Division by Zero" errors before they crashed the program.

### Stage 3: OOP Refactoring & State
The code was decoupled into distinct classes. A `HashMap` was implemented to handle memory persistence, allowing the result of one calculation to be carried over to the next using the `M` variable.

### Stage 4 & 5: Complex Logic & High Precision
The engine was upgraded from `float` to `double` for improved accuracy. The "Honest" features were finalized:
- **`isOneDigit` Logic:** Mathematical checks to determine if a value is an integer between -10 and 10.
- **Nested Iteration:** A sophisticated loop system that forces users to confirm their intent three times before storing "trivial" data.

---

## 🚀 How to Run

1. Ensure you have **JDK 17** or higher installed.
2. Clone the repository:
   ```bash
   git clone [https://github.com/yourusername/honest-calculator.git](https://github.com/yourusername/honest-calculator.git)
   ```
3. Navigate to the project directory, compile, and run:
   ```bash
   javac honestcalculator/Main.java
   java honestcalculator.Main
   ```

## 📖 Usage Examples

### 1. The "Lazy" Operation & Storage
The calculator detects simple operations and triggers a confirmation flow if you try to store a single-digit result.

**Input:**
```text
Enter an equation
> 1 * 5
```
You are ... lazy ... very lazy
5.0
Do you want to store the result? (y / n):
> y
Are you sure? It is only one digit! (y / n)
> y
Don't be silly! It's just one number! Add to the memory? (y / n)
> y
Last chance! Do you really want to embarrass yourself? (y / n)
> y
Do you want to continue calculations? (y / n):

### 2. Using Memory (M)
Stored values can be recalled for future calculations.

Enter an equation
> M + 10

**Output:**
```text
15.0
Do you want to store the result? (y / n):
```

---

## 📝 Reflections
This project served as a deep dive into State Machine Management. While the mathematical requirements are standard, the true complexity lay in managing the program flow through various conditional "gates" without losing the current calculation state or the stored memory value. It highlights the importance of following strict specifications to create a robust, error-resistant CLI tool.

   
## License
This project is licensed under the [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/) License - see the LICENSE file for details.

![Java](https://img.shields.io/badge/language-Java-orange)
![License](https://img.shields.io/badge/license-CC%20BY--NC%204.0-blue)
![AI-No-Training](https://img.shields.io/badge/AI-No--Training-red)
