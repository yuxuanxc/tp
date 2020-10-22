package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Itinerary's budget in TrackPad.
 */
public class Budget {

    public static final String MESSAGE_CONSTRAINTS =
            "Budget should only be a non-negative number, and if it contains decimal values it should have"
                    + " two decimal digits";
    public static final String VALIDATION_REGEX = "^?[0-9]+(\\.[0-9]{2})?$";

    public final String value;

    /**
     * Constructs a {@code Budget}.
     *
     * @param budget A valid budget number.
     */
    public Budget(String budget) {
        requireNonNull(budget);
        checkArgument(isValidBudget(budget), MESSAGE_CONSTRAINTS);
        value = budget;
    }

    /**
     * Constructs a {@code Budget} without value.
     */
    public Budget() {
        value = "";
    }

    /**
     * Returns if a given string is an valid budget.
     */
    public static boolean isValidBudget(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "$" + value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Budget// instanceof handles nulls
                && value.equals(((Budget) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
