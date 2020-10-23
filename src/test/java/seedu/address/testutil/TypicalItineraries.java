package seedu.address.testutil;

import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.LOUVRE;
import static seedu.address.testutil.TypicalAttractions.NIGHT_SAFARI;
import static seedu.address.testutil.TypicalAttractions.NOTRE_DAME;
import static seedu.address.testutil.TypicalAttractions.RIVER_SAFARI;
import static seedu.address.testutil.TypicalAttractions.SINGAPORE_ZOO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ItineraryList;
import seedu.address.model.itinerary.Itinerary;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in tests.
 */
public class TypicalItineraries {

    //todo add more itineraries
    public static final Itinerary SINGAPORE_ZOOS = new ItineraryBuilder().withName("Singapore Zoos")
            .withDescription("Rediscover our wildlife parks!")
            .withStartDate("06-07-2019")
            .withEndDate("07-07-2019")
            .withBudget("500")
            // todo might need more typical attractions from different countries
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(SINGAPORE_ZOO)
                    .withStartTime("1000")
                    .withEndTime("1700")
                    .build(), 1)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(NIGHT_SAFARI)
                    .withStartTime("1900")
                    .withEndTime("2300")
                    .build(), 1)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(RIVER_SAFARI)
                    .withStartTime("1200")
                    .withEndTime("1800")
                    .build(), 2)
            .build();

    public static final Itinerary PARIS_TRIP = new ItineraryBuilder().withName("Paris Trip")
            .withDescription("Visit the City of Light")
            .withStartDate("21-12-2020")
            .withEndDate("26-12-2020")
            .withBudget()
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(EIFFEL_TOWER)
                    .withStartTime("0900")
                    .withEndTime("1500")
                    .build(), 1)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(LOUVRE)
                    .withStartTime("1100")
                    .withEndTime("1800")
                    .build(), 2)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withAttraction(NOTRE_DAME)
                    .withStartTime("1200")
                    .withEndTime("1500")
                    .build(), 3)
            .build();

    private TypicalItineraries() {} // prevents instantiation

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
        return new ArrayList<>(Arrays.asList(SINGAPORE_ZOOS, PARIS_TRIP));
    }
}
