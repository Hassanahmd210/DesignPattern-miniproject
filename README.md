# Quick Expense Splitter

## Overview
The **Quick Expense Splitter** app is designed to simplify shared expenses among friends by:
- Automatically notifying them of new expenses.
- Calculating individual shares for group outings or activities.
- Streamlining expense management with a user-friendly interface.

This application effectively uses design patterns to handle group expenses, automate notifications, manage expense records, and simplify user interactions. It is ideal for group-based activities and studies on design patterns.

---

## Core Features and Design Patterns

### 1. Observer Pattern
- **Purpose**: Automatically notifies friends when a new expense is added.
- **How it works**: Each friend is an observer who receives updates whenever a new expense is added.
- **Why Observer Pattern**: Eliminates the need to manually inform each friend. Registered observers (friends) receive notifications automatically.

### 2. Composite Pattern
- **Purpose**: Manages multiple expenses in a structured way.
- **How it works**: 
  - Individual expenses (e.g., "Dinner," "Movie Tickets") are represented as `SimpleExpense` objects.
  - Multiple expenses can be combined into a `CompositeExpense` to represent a group or outing.
- **Why Composite Pattern**: Enables seamless handling of single and grouped expenses, making it easy to add, group, and calculate totals.

### 3. State Pattern
- **Purpose**: Manages different phases of expense handling.
- **How it works**: 
  - The app transitions between states such as `Adding Expense`, `Reviewing Split`, and `Settled`.
  - Actions and outputs depend on the current state.
  - Example: After adding a new expense, the app transitions to the `Adding Expense` state, and upon review, it moves to the `Settled` state.
- **Why State Pattern**: Provides a clear structure for each phase of expense management, simplifying actions based on the app's current status.

### 4. Facade Pattern
- **Purpose**: Simplifies expense management through a single `ExpenseManager` interface.
- **How it works**:
  - The `ExpenseManager` combines functionalities such as managing friends, adding expenses, notifying observers, and calculating splits.
  - Users interact with a single interface, while the underlying complexity is managed behind the scenes.
- **Why Facade Pattern**: Hides complex interactions, making the application more user-friendly.

---

## Benefits of Design Patterns
- **Observer Pattern**: Automated notifications ensure all users are informed in real-time.
- **Composite Pattern**: Flexible organization of individual and grouped expenses.
- **State Pattern**: Clear management of different phases of the expense lifecycle.
- **Facade Pattern**: Simplified user interaction for a seamless experience.

---

## Conclusion
The **Quick Expense Splitter** app demonstrates the power of design patterns in:
- Enhancing application efficiency.
- Simplifying the user experience.
- Providing a robust framework for real-world expense management scenarios.

This project is an excellent example of how thoughtful pattern selection can improve both usability and maintainability in software development.

---
