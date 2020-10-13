package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AttractionList;
import seedu.address.model.attraction.Attraction;

/**
 * A utility class containing a list of {@code Attraction} objects to be used in tests.
 */
public class TypicalAttractions {

    public static final Attraction JURONG_BIRD_PARK = new AttractionBuilder().withName("JurongBirdPark")
            .withAddress("2 Jurong Hill")
            .withEmail("birdpark@example.com")
            .withPhone("94351253")
            .withLocation("Singapore, Singapore")
            .withTags("animals")
            .build();
    public static final Attraction NIGHT_SAFARI = new AttractionBuilder().withName("Night Safari")
            .withAddress("80 Mandai Lake Rd")
            .withEmail("nightsafari@example.com")
            .withPhone("98765432")
            .withLocation("Singapore, Singapore")
            .withTags("animals", "night")
            .build();
    public static final Attraction SINGAPORE_ZOO = new AttractionBuilder().withName("Singapore Zoo")
            .withPhone("95352563")
            .withEmail("singaporezoo@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withLocation("Singapore, Singapore")
            .build();
    public static final Attraction RIVER_SAFARI = new AttractionBuilder().withName("River Safari")
            .withPhone("87652533")
            .withEmail("riversafari@example.com")
            .withAddress("80 Mandai Lake Rd")
            .withLocation("Singapore, Singapore")
            .withTags("panda")
            .build();
    public static final Attraction ORCHARD_ROAD = new AttractionBuilder().withName("Orchard Road")
            .withPhone("9482224")
            .withEmail("orchardroad@example.com")
            .withAddress("Orchard Road")
            .withLocation("Singapore, Singapore")
            .build();
    public static final Attraction BOTANIC_GARDENS = new AttractionBuilder().withName("Botanic Gardens")
            .withPhone("9482427")
            .withEmail("botanicgardens@example.com")
            .withAddress("1 Cluny Rd")
            .withLocation("Singapore, Singapore")
            .build();
    public static final Attraction JURONG_LAKE = new AttractionBuilder().withName("Jurong Lake")
            .withPhone("9482442")
            .withEmail("juronglake@example.com")
            .withAddress("Jurong")
            .withLocation("Singapore, Singapore")
            .build();

    // Manually added
    public static final Attraction ESPLANADE = new AttractionBuilder().withName("Esplanade").withPhone("8482424")
            .withEmail("esplanade@example.com").withAddress("1 Esplanade Dr").withLocation("Singapore, Singapore")
            .build();
    public static final Attraction SUNTEC = new AttractionBuilder().withName("Suntec City").withPhone("8482131")
            .withEmail("suntec@example.com").withAddress("3 Temasek Blvd").withLocation("Singapore, Singapore").build();

    // Manually added - Attraction's details found in {@code CommandTestUtil}
    public static final Attraction EIFFEL_TOWER = new AttractionBuilder().withName(VALID_NAME_EIFFEL)
            .withPhone(VALID_PHONE_EIFFEL).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
            .withLocation(VALID_LOCATION_EIFFEL).withTags(VALID_TAG_ACTIVITY).build();
    public static final Attraction MBS = new AttractionBuilder().withName(VALID_NAME_MBS).withPhone(VALID_PHONE_MBS)
            .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS).withLocation(VALID_LOCATION_MBS)
            .withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAttractions() {} // prevents instantiation

    /**
     * Returns an {@code AttractionList} with all the typical attractions.
     */
    public static AttractionList getTypicalAttractionList() {
        AttractionList al = new AttractionList();
        for (Attraction attraction : getTypicalAttractions()) {
            al.addAttraction(attraction);
        }
        return al;
    }

    public static List<Attraction> getTypicalAttractions() {
        return new ArrayList<>(Arrays.asList(JURONG_BIRD_PARK, NIGHT_SAFARI, SINGAPORE_ZOO, RIVER_SAFARI, ORCHARD_ROAD,
                BOTANIC_GARDENS, JURONG_LAKE));
    }
}
