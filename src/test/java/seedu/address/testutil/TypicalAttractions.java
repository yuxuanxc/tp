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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TrackPad;
import seedu.address.model.attraction.Attraction;

/**
 * A utility class containing a list of {@code Attraction} objects to be used in tests.
 */
public class TypicalAttractions {

    public static final Attraction JURONG_BIRD_PARK = new AttractionBuilder().withName("JurongBirdPark")
            .withAddress("2 Jurong Hill")
            .withEmail("birdpark@example.com")
            .withPhone("94351253")
            .withDescription("The park offers a haven for close to 3500 birds across 400 species.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .withTags("animals")
            .build();
    public static final Attraction NIGHT_SAFARI = new AttractionBuilder().withName("Night Safari")
            .withAddress("80 Mandai Lake Rd")
            .withEmail("nightsafari@example.com")
            .withPhone("98765432")
            .withDescription("The world's first nocturnal zoo.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1800-2300")
            .withPriceRange()
            .withRating()
            .withTags("animals", "night")
            .build();
    public static final Attraction SINGAPORE_ZOO = new AttractionBuilder().withName("Singapore Zoo")
            .withPhone("95352563")
            .withEmail("singaporezoo@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withDescription()
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .build();
    public static final Attraction RIVER_SAFARI = new AttractionBuilder().withName("River Safari")
            .withPhone("87652533")
            .withEmail("riversafari@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withDescription("A river-themed zoo and aquarium in Singapore.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-1800")
            .withPriceRange()
            .withRating()
            .withTags("panda")
            .build();
    public static final Attraction ORCHARD_ROAD = new AttractionBuilder().withName("Orchard Road")
            .withPhone("9482224")
            .withEmail("orchardroad@example.com")
            .withAddress("Orchard Road")
            .withDescription("Orchard Road is Singapore’s retail heart, with discount outlets, "
                    + "department stores and upscale boutiques, alongside luxury hotels.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .build();
    public static final Attraction BOTANIC_GARDENS = new AttractionBuilder().withName("Botanic Gardens")
            .withPhone("9482427")
            .withEmail("botanicgardens@example.com")
            .withAddress("1 Cluny Rd")
            .withDescription("The only tropical garden to be honoured as a UNESCO World Heritage Site.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .build();
    public static final Attraction JURONG_LAKE = new AttractionBuilder().withName("Jurong Lake")
            .withPhone("9482442")
            .withEmail("juronglake@example.com")
            .withAddress("Jurong")
            .withDescription("Sprawling park around a lake & swamp forest with a boardwalk, "
                    + "playgrounds, a dog run & water sports.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours()
            .withPriceRange()
            .withRating()
            .build();

    // Manually added
    public static final Attraction ESPLANADE = new AttractionBuilder().withName("Esplanade")
            .withPhone("8482424").withEmail("esplanade@example.com")
            .withAddress("1 Esplanade Dr")
            .withDescription("Esplanade – Theatres on the Bay is a performing arts centre "
                    + "located in Downtown Core near the mouth of the Singapore River.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-2200")
            .withPriceRange("HIGH")
            .withRating("4.0")
            .build();
    public static final Attraction SUNTEC = new AttractionBuilder().withName("Suntec City")
            .withPhone("8482131").withEmail("suntec@example.com")
            .withAddress("3 Temasek Blvd")
            .withDescription("Suntec City is a major mixed-use development located in Marina "
                    + "Centre, a subzone of the Downtown Core in Singapore.")
            .withLocation("Singapore, Singapore")
            .withOpeningHours("1000-2200")
            .withPriceRange("LOW")
            .withRating("4.5")
            .build();

    // Manually added - Attraction's details found in {@code CommandTestUtil}
    public static final Attraction EIFFEL_TOWER = new AttractionBuilder().withName(VALID_NAME_EIFFEL)
            .withPhone(VALID_PHONE_EIFFEL).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
            .withDescription(VALID_DESCRIPTION_EIFFEL).withLocation(VALID_LOCATION_EIFFEL)
            .withOpeningHours(VALID_OPENING_HOURS_EIFFEL).withPriceRange(VALID_PRICE_RANGE_EIFFEL)
            .withRating(VALID_RATING_EIFFEL).withTags(VALID_TAG_ACTIVITY).build();
    public static final Attraction MBS = new AttractionBuilder().withName(VALID_NAME_MBS).withPhone(VALID_PHONE_MBS)
            .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS)
            .withDescription(VALID_DESCRIPTION_MBS).withLocation(VALID_LOCATION_MBS)
            .withOpeningHours(VALID_OPENING_HOURS_MBS).withPriceRange(VALID_PRICE_RANGE_MBS)
            .withRating(VALID_RATING_MBS).withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAttractions() {} // prevents instantiation

    /**
     * Returns an {@code TrackPad} with all the typical attractions.
     */
    public static TrackPad getTypicalTrackPad() {
        TrackPad tp = new TrackPad();
        for (Attraction attraction : getTypicalAttractions()) {
            tp.addAttraction(attraction);
        }
        return tp;
    }

    public static List<Attraction> getTypicalAttractions() {
        return new ArrayList<>(Arrays.asList(JURONG_BIRD_PARK, NIGHT_SAFARI, SINGAPORE_ZOO, RIVER_SAFARI, ORCHARD_ROAD,
                BOTANIC_GARDENS, JURONG_LAKE));
    }
}
