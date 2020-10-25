package seedu.address.logic.parser.itineraryattraction;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.AddItineraryAttractionCommand;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItineraryAttractionBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPENING_HOURS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_VISITED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_HOURS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_RANGE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_VISITING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VISITED_DESC_MBS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddItineraryAttractionCommandParserTest {
    private AddItineraryAttractionCommandParser parser = new AddItineraryAttractionCommandParser();

    // todo really bad and messy method, simplify this afterwards.
    private final AddItineraryAttractionCommand expectedAddIaCommand = new AddItineraryAttractionCommand(
            Index.fromOneBased(Integer.parseInt(VALID_INDEX_MBS)),
            new ItineraryTime(VALID_START_TIME_MBS),
            new ItineraryTime(VALID_END_TIME_MBS),
            Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_MBS)));


    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);

        // multiple start time - last start time accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_EIFFEL
                        + START_TIME_DESC_EIFFEL // should not be accepted, else will be diff from expected
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS
                        + START_TIME_DESC_MBS,
                expectedAddIaCommand);

        // multiple end time - last end time accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_EIFFEL
                        + DAY_VISITING_DESC_MBS
                        + END_TIME_DESC_MBS,
                expectedAddIaCommand);

        // multiple day visiting - last day accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + DAY_VISITING_DESC_EIFFEL
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);
    }

//    @Test
//    public void parse_optionalFieldsMissing_success() {
//        // zero tags
//        ItineraryAttraction expectedItineraryAttraction =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withTags().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL
//                        + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL,
//                new AddItineraryAttractionCommand(expectedItineraryAttraction));
//
//        // No phone number given
//        ItineraryAttraction expectedItineraryAttractionNoPhone =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withPhone().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoPhone));
//
//        // No email given
//        ItineraryAttraction expectedItineraryAttractionNoEmail =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withEmail().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL
//                        + OPENING_HOURS_DESC_EIFFEL + PRICE_RANGE_DESC_EIFFEL
//                        + RATING_DESC_EIFFEL + VISITED_DESC_EIFFEL + TAG_DESC_ACTIVITY,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoEmail));
//
//        // No address given
//        ItineraryAttraction expectedItineraryAttractionNoAddress =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withAddress().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + VISITED_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoAddress));
//
//        // No description given
//        ItineraryAttraction expectedItineraryAttractionNoDescription =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withDescription().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
//                        + VISITED_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoDescription));
//
//        // No opening hours given
//        ItineraryAttraction expectedItineraryAttractionNoOpeningHours =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withOpeningHours().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + ADDRESS_DESC_EIFFEL
//                        + VISITED_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoOpeningHours));
//
//        // No price range given
//        ItineraryAttraction expectedItineraryAttractionNoPriceRange =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withPriceRange().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + VISITED_DESC_EIFFEL
//                        + RATING_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoPriceRange));
//
//        // No rating given
//        ItineraryAttraction expectedItineraryAttractionNoRating =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withRating().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + VISITED_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,
//
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoRating));
//
//        // No visited given
//        ItineraryAttraction expectedItineraryAttractionNoVisited =
//                new ItineraryAttractionBuilder(EIFFEL_TOWER).withVisited().build();
//        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
//                        + DESCRIPTION_DESC_EIFFEL + LOCATION_DESC_EIFFEL + OPENING_HOURS_DESC_EIFFEL
//                        + RATING_DESC_EIFFEL
//                        + PRICE_RANGE_DESC_EIFFEL + TAG_DESC_ACTIVITY + ADDRESS_DESC_EIFFEL,
//                new AddItineraryAttractionCommand(expectedItineraryAttractionNoVisited));
//    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddItineraryAttractionCommand.MESSAGE_USAGE);

        // missing start time prefix
        assertParseFailure(parser,
                VALID_START_TIME_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser,
                START_TIME_DESC_MBS
                        + VALID_END_TIME_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedMessage);

        // missing day visiting prefix
        assertParseFailure(parser,
                START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + VALID_DAY_VISITING_MBS,
                expectedMessage);

        // all prefix missing
        assertParseFailure(parser,
                VALID_START_TIME_MBS
                        + VALID_END_TIME_MBS
                        + VALID_DAY_VISITING_MBS,
                expectedMessage);

    }

//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Name.MESSAGE_CONSTRAINTS);
//
//        // invalid phone
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + INVALID_PHONE_DESC
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Phone.MESSAGE_CONSTRAINTS);
//
//        // invalid email
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + INVALID_EMAIL_DESC
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Email.MESSAGE_CONSTRAINTS);
//
//        // invalid address
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + INVALID_ADDRESS_DESC
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Address.MESSAGE_CONSTRAINTS);
//
//        // invalid location
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + INVALID_LOCATION_DESC
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Location.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + INVALID_TAG_DESC
//                        + VALID_TAG_ACTIVITY,
//                Tag.MESSAGE_CONSTRAINTS);
//
//        // invalid Description
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + INVALID_DESCRIPTION_DESC
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Description.MESSAGE_CONSTRAINTS);
//
//        // invalid Opening hours
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + INVALID_OPENING_HOURS_DESC
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                OpeningHours.MESSAGE_CONSTRAINTS);
//
//        // invalid Price Range
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + INVALID_PRICE_RANGE_DESC
//                        + RATING_DESC_MBS
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                PriceRange.MESSAGE_CONSTRAINTS);
//
//        // invalid Rating
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + INVALID_RATING_DESC
//                        + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Rating.MESSAGE_CONSTRAINTS);
//
//        // invalid Visited
//        assertParseFailure(parser, NAME_DESC_MBS
//                        + ADDRESS_DESC_MBS
//                        + DESCRIPTION_DESC_MBS
//                        + EMAIL_DESC_MBS
//                        + LOCATION_DESC_MBS
//                        + OPENING_HOURS_DESC_MBS
//                        + PHONE_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS
//                        + RATING_DESC_MBS
//                        + INVALID_VISITED_DESC
//                        + TAG_DESC_SIGHTSEEING
//                        + TAG_DESC_ACTIVITY,
//                Visited.MESSAGE_CONSTRAINTS);
//
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser,
//                INVALID_NAME_DESC
//                        + PHONE_DESC_MBS + EMAIL_DESC_MBS + INVALID_ADDRESS_DESC
//                        + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS + VISITED_DESC_MBS, Name.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
//                        + ADDRESS_DESC_MBS + DESCRIPTION_DESC_MBS + LOCATION_DESC_MBS + OPENING_HOURS_DESC_MBS
//                        + PRICE_RANGE_DESC_MBS + RATING_DESC_MBS + VISITED_DESC_MBS
//                        + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryAttractionCommand.MESSAGE_USAGE));
//    }
}
