package seedu.address.model.itinerary;

/**
 * Wrapper to hold the time and perform time related functions.
 */
public class ItineraryTime {
    private static final String TIME_REGEX = "([01][0-9]|2[0-3])[0-5][0-9]";
    public static final String VALIDATION_REGEX = TIME_REGEX + "-" + TIME_REGEX;

    private final int time;

    public ItineraryTime(String time) {
        this.time = Integer.parseInt(time);
    }

    /**
     * Returns if a given string is an valid time.
     */
    public static boolean isValidItineraryTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given start time is earlier than end time.
     */
    public boolean isValidStartTime(ItineraryTime endTime){
        return this.time < endTime.time;
    }

    /**
     * Returns true if the time is earlier than time given.
     */
    public boolean isEarlierThan(ItineraryTime time) {
        return this.time < time.time;
    }

    /**
     * Returns ture if the time is later than the time given.
     */
    public boolean isLaterThan(ItineraryTime time) {
        return this.time > time.time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryTime // instanceof handles nulls
                && time == (((ItineraryTime) other).time)); // state check
    }

    @Override
    public String toString() {
        return String.valueOf(time);
    }

    @Override
    public int hashCode() {
        return time * 103;
    }
}
