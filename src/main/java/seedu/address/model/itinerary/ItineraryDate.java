package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class ItineraryDate {

    private static final String MESSAGE_CONSTRAINTS = "";
    private static final DateTimeFormatter DTF = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd-MM-yyyy")
            .toFormatter();

    public final String value;

    /**
     * Constructs a {@code ItineraryDate}.
     *
     * @param date A valid ItineraryDate number.
     */
    public ItineraryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Constructs a {@code Budget} without value.
     */
    public ItineraryDate() {
        value = "";
    }

    /**
     * Returns if a given string is an valid budget.
     */
    public static boolean isValidDate(String test) {
        try {

            System.out.println( LocalDate.parse(test, DTF));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryDate// instanceof handles nulls
                && value.equals(((ItineraryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
