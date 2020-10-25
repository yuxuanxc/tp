package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_MBS;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.LOUVRE;
import static seedu.address.testutil.TypicalAttractions.NIGHT_SAFARI;
import static seedu.address.testutil.TypicalAttractions.NOTRE_DAME;
import static seedu.address.testutil.TypicalAttractions.RIVER_SAFARI;
import static seedu.address.testutil.TypicalAttractions.SINGAPORE_ZOO;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

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
                    .withName("Singapore Zoo")
                    .withPhone("95352563")
                    .withEmail("singaporezoo@example.com")
                    .withAddress("80 Mandai Lake Rd")
                    .withDescription()
                    .withLocation("Singapore, Singapore")
                    .withOpeningHours("1000-1800")
                    .withPriceRange()
                    .withRating()
                    .withVisited()
                    .withStartTime("1000")
                    .withEndTime("1700")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withName("Night Safari")
                    .withAddress("80 Mandai Lake Rd")
                    .withEmail("nightsafari@example.com")
                    .withPhone("98765432")
                    .withDescription("The world's first nocturnal zoo.")
                    .withLocation("Singapore, Singapore")
                    .withOpeningHours("1800-2300")
                    .withPriceRange()
                    .withRating()
                    .withVisited()
                    .withTags("animals", "night")
                    .withStartTime("1900")
                    .withEndTime("2300")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withName("River Safari")
                    .withPhone("87652533")
                    .withEmail("riversafari@example.com")
                    .withAddress("80 Mandai Lake Rd")
                    .withDescription("A river-themed zoo and aquarium in Singapore.")
                    .withLocation("Singapore, Singapore")
                    .withOpeningHours("1000-1800")
                    .withPriceRange()
                    .withRating()
                    .withVisited()
                    .withTags("panda")
                    .withStartTime("1200")
                    .withEndTime("1800")
                    .build(), INDEX_SECOND)
            .build();

    public static final Itinerary PARIS_TRIP = new ItineraryBuilder().withName("Paris Trip")
            .withDescription("Visit the City of Light")
            .withStartDate("21-12-2020")
            .withEndDate("26-12-2020")
            .withBudget()
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withName(VALID_NAME_EIFFEL)
                    .withPhone(VALID_PHONE_EIFFEL).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
                    .withDescription(VALID_DESCRIPTION_EIFFEL).withLocation(VALID_LOCATION_EIFFEL)
                    .withOpeningHours(VALID_OPENING_HOURS_EIFFEL).withPriceRange(VALID_PRICE_RANGE_EIFFEL)
                    .withRating(VALID_RATING_EIFFEL).withVisited(VALID_VISITED_EIFFEL).withTags(VALID_TAG_ACTIVITY)
                    .withStartTime("0900")
                    .withEndTime("1500")
                    .build(), INDEX_FIRST)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withName("Louvre Museum")
                    .withPhone("330140205317")
                    .withEmail("info@louvre.fr")
                    .withAddress("Rue de Rivoli, 75001")
                    .withDescription("The Louvre, or the Louvre Museum, is the world's largest art museum and a"
                            + " historic monument in Paris, France.")
                    .withLocation("Paris, France")
                    .withOpeningHours("0900-1800")
                    .withPriceRange("MEDIUM")
                    .withRating("4.7")
                    .withVisited("FALSE")
                    .withStartTime("1100")
                    .withEndTime("1800")
                    .build(), INDEX_SECOND)
            .withItineraryAttraction(new ItineraryAttractionBuilder()
                    .withName(VALID_NAME_MBS)
                    .withPhone(VALID_PHONE_MBS)
                    .withEmail(VALID_EMAIL_MBS)
                    .withAddress(VALID_ADDRESS_MBS)
                    .withDescription(VALID_DESCRIPTION_MBS)
                    .withLocation(VALID_LOCATION_MBS)
                    .withOpeningHours(VALID_OPENING_HOURS_MBS)
                    .withPriceRange(VALID_PRICE_RANGE_MBS)
                    .withRating(VALID_RATING_MBS)
                    .withVisited(VALID_VISITED_MBS)
                    .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY)
                    .withStartTime("1200")
                    .withEndTime("1500")
                    .build(), INDEX_THIRD)
            .build();

    private TypicalItineraries() {
    } // prevents instantiation

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
