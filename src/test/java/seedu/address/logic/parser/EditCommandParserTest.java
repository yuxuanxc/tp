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
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SIGHTSEEING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ATTRACTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ATTRACTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ATTRACTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_EIFFEL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_EIFFEL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_EIFFEL, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_MBS + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Attraction} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_ACTIVITY + TAG_EMPTY + TAG_DESC_SIGHTSEEING, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_ACTIVITY + TAG_DESC_SIGHTSEEING, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_EIFFEL
                + VALID_PHONE_EIFFEL + VALID_LOCATION_EIFFEL, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ATTRACTION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MBS + TAG_DESC_SIGHTSEEING
                + EMAIL_DESC_EIFFEL + ADDRESS_DESC_EIFFEL + NAME_DESC_EIFFEL + LOCATION_DESC_EIFFEL + TAG_DESC_ACTIVITY;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_EIFFEL)
                .withPhone(VALID_PHONE_MBS).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
                .withLocation(VALID_LOCATION_EIFFEL).withTags(VALID_TAG_SIGHTSEEING, VALID_TAG_ACTIVITY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ATTRACTION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_MBS + EMAIL_DESC_EIFFEL;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                .withEmail(VALID_EMAIL_EIFFEL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_ATTRACTION;
        String userInput = targetIndex.getOneBased() + NAME_DESC_EIFFEL;
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_EIFFEL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_EIFFEL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withEmail(VALID_EMAIL_EIFFEL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withAddress(VALID_ADDRESS_EIFFEL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_EIFFEL;
        descriptor = new EditAttractionDescriptorBuilder().withLocation(VALID_LOCATION_EIFFEL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_ACTIVITY;
        descriptor = new EditAttractionDescriptorBuilder().withTags(VALID_TAG_ACTIVITY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ATTRACTION;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                + LOCATION_DESC_EIFFEL + TAG_DESC_ACTIVITY + PHONE_DESC_EIFFEL + ADDRESS_DESC_EIFFEL + EMAIL_DESC_EIFFEL
                + TAG_DESC_ACTIVITY + PHONE_DESC_MBS + ADDRESS_DESC_MBS + EMAIL_DESC_MBS + LOCATION_DESC_MBS
                + TAG_DESC_SIGHTSEEING;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS)
                .withEmail(VALID_EMAIL_MBS).withAddress(VALID_ADDRESS_MBS)
                .withLocation(VALID_LOCATION_MBS).withTags(VALID_TAG_ACTIVITY, VALID_TAG_SIGHTSEEING).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ATTRACTION;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_MBS;
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_MBS + INVALID_PHONE_DESC + ADDRESS_DESC_MBS
                + PHONE_DESC_MBS + LOCATION_DESC_MBS;
        descriptor = new EditAttractionDescriptorBuilder().withPhone(VALID_PHONE_MBS).withEmail(VALID_EMAIL_MBS)
                .withAddress(VALID_ADDRESS_MBS).withLocation(VALID_LOCATION_MBS).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_ATTRACTION;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
