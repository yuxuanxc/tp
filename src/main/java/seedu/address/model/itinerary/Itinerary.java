package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Represents an Itinerary in TrackPad.
 */
public class Itinerary implements Item {
    private String name;
    private final UniqueItemList<Item> itineraryItems;

    /**
     * Name must be present and not null.
     */
    public Itinerary(String name) {
        requireAllNonNull(name);
        this.name = name;
        this.itineraryItems = new UniqueItemList<>();
    }

    public String getName() {
        return name;
    }

    public ObservableList<Item> getItineraryItems() {
        return itineraryItems.asUnmodifiableObservableList();
    }

    /**
     * Replaces the contents of the itinerary list with {@code iteneraryItems}.
     * {@code items} must not contain duplicate itinerary items.
     */
    public void setItineraryItems(List<Item> items) {
        this.itineraryItems.setItems(items);
    }

    /**
     * Returns true if an item in the itinerary list with the same identity as {@code item} exists.
     */
    public boolean hasItineraryItem(Item item) {
        requireNonNull(item);
        return itineraryItems.contains(item);
    }

    /**
     * Adds an item to the itinerary.
     */
    public void addItineraryItem(Item toAdd) {
        itineraryItems.add(toAdd);
    }

    /**
     * Replaces the given itinerary item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the itinerary.
     * The itinerary item identity of {@code editedItem} must not be the same as another existing
     * itinerary item in the trackPad.
     */
    public void setItineraryItem(Item target, Item editedItem) {
        requireNonNull(editedItem);

        itineraryItems.setItem(target, editedItem);
    }

    /**
     * Removes the equivalent item from the itinerary.
     * The attraction must exist in the list.
     */
    public void removeItineraryItem(Item toRemove) {
        itineraryItems.remove(toRemove);
    }

    @Override
    public boolean isSame(Item otherItem) {
        return false;
    }

    @Override
    public String toString() {
        return itineraryItems.asUnmodifiableObservableList().size() + " items in the itinerary";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Itinerary // instanceof handles nulls
                && itineraryItems.equals(((Itinerary) other).itineraryItems));
    }

    @Override
    public int hashCode() {
        return itineraryItems.hashCode();
    }
}
