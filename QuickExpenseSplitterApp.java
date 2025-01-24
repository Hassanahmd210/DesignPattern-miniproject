import java.util.ArrayList;
import java.util.List;

// Observer Pattern - Friends are notified when a new expense is added
interface Observer {
    void update(String message);
}

class Friend implements Observer {
    private String name;

    public Friend(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }
}

// Composite Pattern - Allows grouping of multiple expenses in a single outing or event
abstract class ExpenseComponent {
    protected String description;
    protected double amount;

    public abstract double getAmount();

    public abstract String getDescription();
}

class SimpleExpense extends ExpenseComponent {
    public SimpleExpense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

class CompositeExpense extends ExpenseComponent {
    private List<ExpenseComponent> expenses = new ArrayList<>();

    public void addExpense(ExpenseComponent expense) {
        expenses.add(expense);
    }

    @Override
    public double getAmount() {
        return expenses.stream().mapToDouble(ExpenseComponent::getAmount).sum();
    }

    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder("Group Expense: ");
        for (ExpenseComponent expense : expenses) {
            desc.append(expense.getDescription()).append(", ");
        }
        return desc.toString().trim();
    }
}

// State Pattern - Represents different stages of expense handling
interface State {
    void handle();
}

class AddingExpenseState implements State {
    @Override
    public void handle() {
        System.out.println("Currently adding an expense...");
    }
}

class ReviewingSplitState implements State {
    @Override
    public void handle() {
        System.out.println("Reviewing and adjusting expense splits...");
    }
}

class SettledState implements State {
    @Override
    public void handle() {
        System.out.println("Expenses settled.");
    }
}

// Facade Pattern - Provides a simple interface for managing expenses
class ExpenseManager {
    private State state;
    private CompositeExpense compositeExpense = new CompositeExpense();
    private List<Observer> friends = new ArrayList<>();

    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    public void setState(State state) {
        this.state = state;
        state.handle();
    }

    public void addExpense(String description, double amount) {
        if (amount <= 0) {
            System.out.println("Error: Expense amount must be positive.");
            return;
        }
        compositeExpense.addExpense(new SimpleExpense(description, amount));
        System.out.println("Added expense: " + description + " - $" + amount);
        notifyFriends("New expense added: " + description + " - $" + amount);
        setState(new ReviewingSplitState());
    }

    public void reviewAndSplitExpense() {
        double total = compositeExpense.getAmount();
        int friendCount = friends.size();

        if (friendCount == 0) {
            System.out.println("Error: No friends added. Add friends before reviewing expenses.");
            return;
        }

        double splitAmount = total / friendCount;
        System.out.println("Total Expense: $" + total);
        System.out.println("Each friend owes: $" + splitAmount);

        setState(new SettledState());
    }

    private void notifyFriends(String message) {
        for (Observer friend : friends) {
            friend.update(message);
        }
    }
}

// Main Class
public class QuickExpenseSplitterApp {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();

        // Adding friends as observers
        Friend friendLaxmi = new Friend("Laxmikant");
        Friend friendMayank = new Friend("Mayank");
        expenseManager.addFriend(friendLaxmi);
        expenseManager.addFriend(friendMayank);

        // Set initial state
        expenseManager.setState(new AddingExpenseState());

        // Adding expenses
        expenseManager.addExpense("Dinner", 500);
        expenseManager.addExpense("Movie Tickets", 350);

        // Review and split expenses
        expenseManager.reviewAndSplitExpense();
    }
}
