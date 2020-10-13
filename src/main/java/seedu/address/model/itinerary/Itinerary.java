package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.exceptions.AttractionNotFoundException;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;

/**
 * Represents an Itinerary in TrackPad.
 */
public class Itinerary {
    //todo add more itinerary fields
    // todo currently takes Name of Attraction
    private Name name;
    private final List<ItineraryAttraction> itineraryAttractions = new ArrayList<>();

    /**
     * Name must be present and not null.
     */
    public Itinerary(Name name, List<ItineraryAttraction> itineraryAttractions) {
        requireAllNonNull(name);
        this.name = name;
        this.itineraryAttractions.addAll(itineraryAttractions);
    }

    public Name getName() {
        return name;
    }

    public List<ItineraryAttraction> getItineraryAttractions() {
        return itineraryAttractions;
    }

    /**
     * Adds an itinerary attraction to the itinerary.
     */
    public void addItineraryAttraction(ItineraryAttraction toAdd) {
        requireNonNull(toAdd);
        itineraryAttractions.add(toAdd);
    }

    /**
     * Removes the equivalent itinerary attraction from the itinerary.
     * The itinerary attraction must exist in the list.
     */
    public void removeItineraryItem(ItineraryAttraction toRemove) {
        itineraryAttractions.remove(toRemove);
    }

    /**
     * Returns true if an itineraryAttraction in the itinerary list with the same identity as
     * {@code itineraryAttraction} exists.
     */
    public boolean contains(ItineraryAttraction itineraryAttraction) {
        requireNonNull(itineraryAttraction);
        return itineraryAttractions.contains(itineraryAttraction);
    }

    /**
     * Replaces the contents of the itinerary list with {@code iteneraryItems}.
     * {@code itineraryAttractions} must not contain duplicate itineraryAttractions.
     */
    public void setItineraryAttractions(List<ItineraryAttraction> itineraryAttractions) {
        this.itineraryAttractions.clear();
        this.itineraryAttractions.addAll(itineraryAttractions);
    }

    /**
     * Replaces the given itinerary item {@code target} in the list with {@code editedItineraryAttraction}.
     * {@code target} must exist in the itinerary.
     * The itinerary item identity of {@code editedItineraryAttraction} must not be the same as another existing
     * itinerary item in the itinerary.
     */
    public void setItineraryAttraction(ItineraryAttraction target, ItineraryAttraction editedItineraryAttraction) {
        requireNonNull(editedItineraryAttraction);
        int index = itineraryAttractions.indexOf(target);
        if (index == -1) {
            throw new AttractionNotFoundException();
        }

        if (!target.isSameItineraryAttraction(editedItineraryAttraction) && contains(editedItineraryAttraction)) {
            throw new DuplicateAttractionException();
        }

        itineraryAttractions.set(index, editedItineraryAttraction);
    }

    /**
     * Returns true if both itineraries of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two itineraries.
     */
    public boolean isSameItinerary(Itinerary otherItinerary) {
        if (otherItinerary == this) {
            return true;
        }

        return otherItinerary != null
                && otherItinerary.getName().equals(getName());
        //|| or other fields to be added e.g. description (See Attraction)
    }

    /**
     * Returns true if both itineraries have the same identity and data fields.
     * This defines a stronger notion of equality between two itineraries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return otherItinerary.getName().equals(getName());
        // && and other fields to be added e.g. description (See Attraction)
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, itineraryAttractions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }
}
