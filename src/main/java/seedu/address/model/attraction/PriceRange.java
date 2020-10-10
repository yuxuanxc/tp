package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Attraction's price range in TrackPad.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriceRange(String)} (String)}
 */
public class PriceRange {

    public static final String MESSAGE_CONSTRAINTS = "Price Range should be of the format "
            + "LOW, MEDIUM, or HIGH";
    public static final String VALIDATION_REGEX = "\\bLOW\\b|\\bMEDIUM\\b|\\bHIGH\\b";

    public final String value;

    /**
     * Constructs an {@code PriceRange}.
     *
     * @param priceRange A valid price range.
     */
    public PriceRange(String priceRange) {
        requireNonNull(priceRange);
        checkArgument(isValidPriceRange(priceRange), MESSAGE_CONSTRAINTS);
        value = priceRange;
    }

    /**
     * Constructs an {@code PriceRange} without value.
     */
    public PriceRange() {
        value = "";
    }

    /**
     * Returns if a given string is a valid price range.
     */
    public static boolean isValidPriceRange(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (value.equals("LOW")) {
            return "$";
        } else if (value.equals("MEDIUM")) {
            return "$$";
        } else if (value.equals("HIGH")) {
            return "$$$";
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof  PriceRange // instanceof handles nulls
                && value.equals(((PriceRange) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
