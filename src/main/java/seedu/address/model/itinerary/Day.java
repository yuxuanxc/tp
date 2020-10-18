package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.itinerary.exceptions.DuplicateItineraryAttractionException;
import seedu.address.model.itinerary.exceptions.ItineraryAttractionNotFoundException;

/**
 * Represents an Itinerary's day in TrackPad.
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS = "Days should be a positive number, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";
    public final String value;
    private final List<ItineraryAttraction> itineraryAttractions;

    /**
     * Constructs an empty {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        value = day;
        this.itineraryAttractions = new ArrayList<>();
    }

    /**
     * Constructs a {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day, List<ItineraryAttraction> itineraryAttractions) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        value = day;
        this.itineraryAttractions = itineraryAttractions;
    }

    public List<ItineraryAttraction> getItineraryAttractions() {
        return itineraryAttractions;
    }

    public void addItineraryAttraction(ItineraryAttraction toAdd) {
        itineraryAttractions.add(toAdd);
    }

    public void deleteItineraryAttraction(int index) {
        itineraryAttractions.remove(index);
    }

    /**
     * Edits the specified itinerary attraction.
     */
    public void editItineraryAttraction(ItineraryAttraction target, ItineraryAttraction editedItineraryAttraction) {
        int index = itineraryAttractions.indexOf(target);
        if (index == -1) {
            throw new ItineraryAttractionNotFoundException();
        }

        if (!target.isSameItineraryAttraction(editedItineraryAttraction) && contains(editedItineraryAttraction)) {
            throw new DuplicateItineraryAttractionException();
        }

        itineraryAttractions.set(index, editedItineraryAttraction);

    }

    public boolean contains(ItineraryAttraction itineraryAttraction) {
        return itineraryAttractions.contains(itineraryAttraction);
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)
                && itineraryAttractions.equals(((Day) other).itineraryAttractions)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, itineraryAttractions);
    }
}
