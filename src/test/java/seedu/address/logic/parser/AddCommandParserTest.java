package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.MBS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AttractionBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Attraction expectedAttraction = new AttractionBuilder(MBS).withTags(VALID_TAG_ACTIVITY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_EIFFEL + NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_EIFFEL + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_EIFFEL + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_EIFFEL
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple locations - last location accepted
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_EIFFEL + LOCATION_DESC_MBS + TAG_DESC_ACTIVITY, new AddCommand(expectedAttraction));

        // multiple tags - all accepted
        Attraction expectedAttractionMultipleTags = new AttractionBuilder(MBS)
                .withTags(VALID_TAG_ACTIVITY, VALID_TAG_SIGHTSEEING).build();
        assertParseSuccess(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY,
                new AddCommand(expectedAttractionMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Attraction expectedAttraction = new AttractionBuilder(EIFFEL_TOWER).withTags().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + ADDRESS_DESC_EIFFEL + LOCATION_DESC_EIFFEL, new AddCommand(expectedAttraction));

        // No address field
        Attraction expectedAttractionNoAddress = new AttractionBuilder(EIFFEL_TOWER).withAddress().build();
        assertParseSuccess(parser, NAME_DESC_EIFFEL + PHONE_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                        + LOCATION_DESC_EIFFEL + TAG_DESC_ACTIVITY, new AddCommand(expectedAttractionNoAddress));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_MBS + VALID_PHONE_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + VALID_EMAIL_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS, expectedMessage);

        // todo delete check for missing address prefix since address is no longer compulsory
        // missing address prefix
        // assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + VALID_ADDRESS_MBS
        //         + LOCATION_DESC_MBS, expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + VALID_LOCATION_MBS, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_MBS + VALID_PHONE_MBS + VALID_EMAIL_MBS + VALID_ADDRESS_MBS
                + VALID_LOCATION_MBS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_MBS + INVALID_PHONE_DESC + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + INVALID_EMAIL_DESC + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + INVALID_ADDRESS_DESC
                + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY, Address.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + INVALID_LOCATION_DESC + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY, Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS + ADDRESS_DESC_MBS
                + LOCATION_DESC_MBS + INVALID_TAG_DESC + VALID_TAG_ACTIVITY, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_MBS + EMAIL_DESC_MBS + INVALID_ADDRESS_DESC
                + LOCATION_DESC_MBS, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_MBS + PHONE_DESC_MBS + EMAIL_DESC_MBS
                + ADDRESS_DESC_MBS + LOCATION_DESC_MBS + TAG_DESC_SIGHTSEEING + TAG_DESC_ACTIVITY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
