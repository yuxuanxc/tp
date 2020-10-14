package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.ItineraryList;
import seedu.address.model.itinerary.Itinerary;

public class TypicalItineraries {
    // todo make typical itineraries
    /**
     * Returns an {@code ItineraryList} with all the typical itineraries.
     */
    public static ItineraryList getTypicalItineraryList() {
        ItineraryList il = new ItineraryList();
        for (Itinerary itinerary : getTypicalItineraries()) {
            il.addItinerary(itinerary);
        }
        return il;
    }

    public static List<Itinerary> getTypicalItineraries() {
        return new ArrayList<>();
    }
}
