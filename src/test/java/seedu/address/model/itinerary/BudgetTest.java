package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BudgetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Budget(null));
    }

    @Test
    public void constructor_invalidBudget_throwsIllegalArgumentException() {
        String invalidBudget = "";
        assertThrows(IllegalArgumentException.class, () -> new Budget(invalidBudget));
    }

    @Test
    public void isValidBudget() {
        // null budget
        assertThrows(NullPointerException.class, () -> Budget.isValidBudget(null));

        // blank budget
        assertFalse(Budget.isValidBudget("")); // empty string
        assertFalse(Budget.isValidBudget(" ")); // spaces only

        // invalid budget
        assertFalse(Budget.isValidBudget("-1")); // non-positive value
        assertFalse(Budget.isValidBudget("twenty")); // non-numeric value
        assertFalse(Budget.isValidBudget("2E.34")); // non-numeric value
        assertFalse(Budget.isValidBudget("$23")); // non-numeric value
        assertFalse(Budget.isValidBudget("1.2")); // not enough decimal places
        assertFalse(Budget.isValidBudget("23.345")); // too many decimal places

        // valid budget
        assertTrue(Budget.isValidBudget("0")); // zero value
        assertTrue(Budget.isValidBudget("1660")); // non-decimal value
        assertTrue(Budget.isValidBudget("2000.57")); // decimal value
    }
}
