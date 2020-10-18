package seedu.address.model.itinerary;

import java.util.Objects;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Name;

/**
 * Wrapper class to contain attributes of an attraction specific to a particular Itinerary.
 */
public class ItineraryAttraction {
    // todo decide if inheritance or dependency for this attraction
    private final Attraction attraction;
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;
    private final int dayVisiting;
    // todo maybe extend from Attraction directly.
    // todo add a check to make sure the start time and end time don't clash with existing itinerary attractions.
    // todo put new commands into ItineraryAttractionCommands
    // todo put command parser into ItineraryAttractionCommandParser
    // todo add attraction to itinerary
    // todo edit attraction from itinerary
    // todo delete attraction from itinerary

    /**
     * Constructs a Itinerary attraction.
     *
     * @param attraction attraction to visit.
     * @param startTime time to visit the attraction.
     * @param endTime time to leave the attraction.
     * @param dayVisiting day in itinerary to visit the attraction.
     */
    public ItineraryAttraction(Attraction attraction, ItineraryTime startTime, ItineraryTime endTime, int dayVisiting) {
        this.attraction = attraction;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayVisiting = dayVisiting;
    }

    /**
     * Returns name of the attraction in this itinerary attraction.
     */
    public Name getName() {
        return attraction.getName();
    }


    /**
     * @return the attraction stored in this class.
     */
    public Attraction getAttraction() {
        return attraction;
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
     * Returns true if both ItineraryAttraction has the same visiting day.
     */
    public boolean isSameVisitingDay(ItineraryAttraction otherItineraryAttraction) {
        return dayVisiting == otherItineraryAttraction.dayVisiting;
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
                && isSameTiming(otherItineraryAttraction)
                && isSameVisitingDay(otherItineraryAttraction);
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
