package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.UniqueItineraryList;

/**
 * Wraps all data at trackPad's itinerary list level
 * Duplicates are not allowed (by .isSameItinerary comparison)
 */
public class ItineraryList implements ReadOnlyItineraryList {

    private static final Logger logger = LogsCenter.getLogger(ItineraryList.class);
    private final UniqueItineraryList itineraries;
    private Itinerary currentItinerary;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itineraries = new UniqueItineraryList();
    }

    public ItineraryList() {
    }

    /**
     * Creates an ItineraryList using the Itineraries in the {@code toBeCopied}
     */
    public ItineraryList(ReadOnlyItineraryList toBeCopied) {
        this();
        resetData(toBeCopied);
        // sets the selected itinerary as the first itinerary when this is first created.
        // may cause error if no itinerary is every created/ exist in json.
        // TODO REMOVE ME after select itinerary is implemented.
        setCurrentItinerary(itineraries.asUnmodifiableObservableList().get(0));
        logger.info("Initialising...\nCurrent Itinerary is set as: " + this.getCurrentItinerary());
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the itinerary list with {@code itineraries}.
     * {@code itineraries} must not contain duplicate itineraries.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries.setItineraries(itineraries);
    }

    /**
     * Resets the existing data of this {@code ItineraryList} with {@code newData}.
     */
    public void resetData(ReadOnlyItineraryList newData) {
        requireNonNull(newData);

        setItineraries(newData.getItineraryList());
    }

    //// itinerary-level operations

    /**
     * Returns true if an itinerary with the same identity as {@code itinerary} exists in the itinerary list.
     */
    public boolean hasItinerary(Itinerary itinerary) {
        requireNonNull(itinerary);
        return itineraries.contains(itinerary);
    }

    /**
     * Adds an itinerary to the itinerary list.
     * The itinerary must not already exist in the itinerary list.
     */
    public void addItinerary(Itinerary a) {
        itineraries.add(a);
        // sets the newly created itinerary to the selected itinerary.
        // todo REMOVE ME after select itinerary is implemented.
        setCurrentItinerary(a);
        logger.info("Current Itinerary is set as: " + this.getCurrentItinerary());
    }

    /**
     * Replaces the given itinerary {@code target} in the list with {@code editedItinerary}.
     * {@code target} must exist in the itinerary list.
     * The itinerary identity of {@code editedItinerary} must not be the same as another existing
     * itinerary in the itinerary list.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireNonNull(editedItinerary);

        itineraries.setItinerary(target, editedItinerary);
    }

    /**
     * Removes {@code key} from this {@code ItineraryList}.
     * {@code key} must exist in the itinerary list.
     */
    public void removeItinerary(Itinerary key) {
        itineraries.remove(key);
    }

    //// current Itinerary methods

    public void setCurrentItinerary(Itinerary currentItinerary) {
        this.currentItinerary = currentItinerary;
    }

    // todo throw error if current itinerary is not set
    public Itinerary getCurrentItinerary() {
        return this.currentItinerary;
    }

    //// util methods

    @Override
    public String toString() {
        return itineraries.asUnmodifiableObservableList().size() + " itineraries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Itinerary> getItineraryList() {
        return itineraries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryList // instanceof handles nulls
                && itineraries.equals(((ItineraryList) other).itineraries));
    }

    @Override
    public int hashCode() {
        return itineraries.hashCode();
    }
}
