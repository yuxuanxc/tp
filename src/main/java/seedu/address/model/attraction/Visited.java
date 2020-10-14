package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Attraction's price range in TrackPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidVisited(String)} (String)}
 */
public class Visited {

    public static final String MESSAGE_CONSTRAINTS = "Visited should be of the format "
            + "TRUE or FALSE";
    public static final String VALIDATION_REGEX = "\\bTRUE\\b|\\bFALSE\\b";

    public final String value;

    /**
     * Constructs an {@code IsVisited}.
     *
     * @param visited A valid price range.
     */
    public Visited(String visited) {
        requireNonNull(visited);
        checkArgument(isValidVisited(visited), MESSAGE_CONSTRAINTS);
        value = visited;
    }

    /**
     * Constructs an {@code IsVisited} without value.
     */
    public Visited() {
        value = "";
    }

    /**
     * Returns if a given string is a valid price range.
     */
    public static boolean isValidVisited(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value.equals("TRUE")) {
            return "Visited";
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Visited // instanceof handles nulls
                && value.equals(((Visited) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
