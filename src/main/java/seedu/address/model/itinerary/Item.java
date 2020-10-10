package seedu.address.model.itinerary;

import java.util.Objects;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Name;
import seedu.address.model.item.Item;

/**
 * Represents an item in an Itinerary.
 */
public class Item implements Item {
    private Attraction attraction;
    //todo add start time, end time, budget etc.

    public Item(Attraction attraction) {
        this.attraction = attraction;
    }

    public Name getName() {
        return attraction.getName();
    }

    public Attraction getAttraction() {
        return attraction;
    }

    /**
     * Returns true if both itinerary attractions are of the same name have at least one other identity field
     * that is the same.
     * This defines a weaker notion of equality between two itinerary attractions.
     */
    public boolean isSame(Item otherItem) {
        if (otherItem == this) {
            return true;
        } else if (otherItem instanceof Item) {
            Item item = (Item) otherItem;
            return item.getAttraction().isSame(attraction);
        } else {
            return false;
        }
    }

    /**
     * Returns true if both itinerary attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two itinerary attractions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getAttraction().equals(getAttraction());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(attraction);
    }

    @Override
    public String toString() {
        return attraction.toString();
    }
}
