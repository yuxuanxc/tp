package seedu.address.model.itinerary;

/**
 * Wrapper to hold the time and perform time related functions.
 */
public class ItineraryTime {
    private static final String TIME_REGEX = "([01][0-9]|2[0-3])[0-5][0-9]";
    public static final String VALIDATION_REGEX = TIME_REGEX + "-" + TIME_REGEX;

    private final String time;

    public ItineraryTime(String time) {
        this.time = time;
    }

    /**
     * Returns if a given string is an valid time.
     */
    public static boolean isValidItineraryTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isWithInTime(ItineraryTime startTime, ItineraryTime endTime) {
        // todo compare if the time is within the start/end time
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryTime // instanceof handles nulls
                && time.equals(((ItineraryTime) other).time)); // state check
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
