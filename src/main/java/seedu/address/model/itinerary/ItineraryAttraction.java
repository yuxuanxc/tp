package seedu.address.model.itinerary;

import java.util.Objects;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.commons.Name;

/**
 * Wrapper class to contain attributes of an attraction specific to a particular Itinerary.
 */
public class ItineraryAttraction {
    // todo decide if inheritance or dependency for this attraction, maybe extend from Attraction directly.
    private final Attraction attraction;
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;

    /**
     * Constructs a Itinerary attraction.
     *
     * @param attraction attraction to visit.
     * @param startTime  time to visit the attraction.
     * @param endTime    time to leave the attraction.
     */
    public ItineraryAttraction(Attraction attraction, ItineraryTime startTime, ItineraryTime endTime) {
        this.attraction = attraction;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns name of the attraction in this itinerary attraction.
     */
    public Name getName() {
        return attraction.getName();
    }


    /**
     * Returns the attraction in this itinerary attraction.
     */
    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * Returns the start time of this itinerary attraction.
     */
    public ItineraryTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of this itinerary attraction.
     */
    public ItineraryTime getEndTime() {
        return endTime;
    }

    /**
     * Returns true if both ItineraryAttraction has the same start and end time.
     *
     * @param otherItineraryAttraction the ItineraryAttraction to compare with.
     */
    public boolean isSameTiming(ItineraryAttraction otherItineraryAttraction) {
        return this.startTime.equals(otherItineraryAttraction.startTime)
                && this.endTime.equals(otherItineraryAttraction.endTime);
    }

    /**
     * Returns true if both itinerary attractions are of the same name, have the same visiting timing.
     * This defines a weaker notion of equality between two itinerary attractions.
     *
     * @return otherItineraryAttraction the itineraryAttraction to compare with.
     */
    public boolean isSameItineraryAttraction(ItineraryAttraction otherItineraryAttraction) {
        if (otherItineraryAttraction == this) {
            return true;
        }

        return attraction.isSameAttraction(otherItineraryAttraction.getAttraction())
                && isSameTiming(otherItineraryAttraction);
    }

    /**
     * Returns false if timing does not clash with this itinerary attraction.
     */
    public boolean isTimingClash(ItineraryAttraction itineraryAttraction) {
        // Might make a mistake here with the ! due to De Morgan's law
        return !this.getStartTime().isEarlierThan(itineraryAttraction.getStartTime())
                && !this.getEndTime().isLaterThan(itineraryAttraction.getEndTime());
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

        if (!(other instanceof ItineraryAttraction)) {
            return false;
        }

        ItineraryAttraction otherItineraryAttraction = (ItineraryAttraction) other;
        return otherItineraryAttraction.getAttraction().equals(getAttraction())
                && isSameTiming(otherItineraryAttraction);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(attraction);
    }

    @Override
    public String toString() {
        return attraction.toString();
        // todo add more fields
    }
}
