package seedu.address.model.itinerary;

import java.util.Objects;

/**
 * Child class of an attraction which additionally stores start time and end time
 * Is-a attraction specific to a particular Itinerary.
 */
public class ItineraryAttractionPlaceholder extends ItineraryAttraction {
    private final int day;

    /**
     * Constructs a Itinerary attraction.
     *
     * @param itineraryAttraction
     * @param day Day of visit.
     */
    public ItineraryAttractionPlaceholder(ItineraryAttraction itineraryAttraction, int day) {
        super(itineraryAttraction.getAttraction(), itineraryAttraction.getStartTime(),
                itineraryAttraction.getEndTime());
        this.day = day;
    }

    public int getDay() {
        return day;
    }
    /**
     * Returns true if both itinerary attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two itinerary attractions.
     * Attraction, startTime, endTime, dayVisiting must be the same to return true.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItineraryAttractionPlaceholder)) {
            return false;
        }

        ItineraryAttractionPlaceholder otherItineraryAttraction = (ItineraryAttractionPlaceholder) other;
        // calls equals of Attraction and checks the timing.
        return super.equals(otherItineraryAttraction)
                && isSameTiming(otherItineraryAttraction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this, day);
    }

    @Override
    public String toString() {
        return "Day " + day;
    }
}
