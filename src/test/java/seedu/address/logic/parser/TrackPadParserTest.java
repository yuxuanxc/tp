package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.attraction.AddAttractionCommand;
import seedu.address.logic.commands.attraction.ClearAttractionCommand;
import seedu.address.logic.commands.attraction.DeleteAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand;
import seedu.address.logic.commands.attraction.EditAttractionCommand.EditAttractionDescriptor;
import seedu.address.logic.commands.attraction.FindAttractionCommand;
import seedu.address.logic.commands.attraction.ListAttractionCommand;
import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.logic.commands.itinerary.ClearItineraryCommand;
import seedu.address.logic.commands.itinerary.DeleteItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.logic.commands.itinerary.FindItineraryCommand;
import seedu.address.logic.commands.itinerary.ListItineraryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryContainsKeywordsPredicate;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.AttractionUtil;
import seedu.address.testutil.EditAttractionDescriptorBuilder;
import seedu.address.testutil.EditItineraryDescriptorBuilder;
import seedu.address.testutil.ItineraryBuilder;
import seedu.address.testutil.ItineraryUtil;

public class TrackPadParserTest {

    private final TrackPadParser parser = new TrackPadParser();

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    // =========== Attraction Commands =================================================================================

    @Test
    public void parseCommand_addAttraction() throws Exception {
        Attraction attraction = new AttractionBuilder().build();
        AddAttractionCommand command = (AddAttractionCommand) parser.parseCommand(
                AttractionUtil.getAddAttractionCommand(attraction));
        assertEquals(new AddAttractionCommand(attraction), command);
    }

    @Test
    public void parseCommand_clearAttraction() throws Exception {
        assertTrue(parser.parseCommand(ClearAttractionCommand.COMMAND_WORD) instanceof ClearAttractionCommand);
        assertTrue(parser.parseCommand(ClearAttractionCommand.COMMAND_WORD + " 3") instanceof ClearAttractionCommand);
    }

    @Test
    public void parseCommand_deleteAttraction() throws Exception {
        DeleteAttractionCommand command = (DeleteAttractionCommand) parser.parseCommand(
                DeleteAttractionCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteAttractionCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editAttraction() throws Exception {
        Attraction attraction = new AttractionBuilder().build();
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(attraction).build();
        EditAttractionCommand command = (EditAttractionCommand) parser.parseCommand(
                EditAttractionCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + AttractionUtil.getEditAttractionDescriptorDetails(descriptor));
        assertEquals(new EditAttractionCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_findAttraction() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindAttractionCommand command = (FindAttractionCommand) parser.parseCommand(
                FindAttractionCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindAttractionCommand(new AttractionContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listAttraction() throws Exception {
        assertTrue(parser.parseCommand(ListAttractionCommand.COMMAND_WORD) instanceof ListAttractionCommand);
        assertTrue(parser.parseCommand(ListAttractionCommand.COMMAND_WORD + " 3") instanceof ListAttractionCommand);
    }

    // =========== Itinerary Commands ==================================================================================

    @Test
    public void parseCommand_addItinerary() throws Exception {
        Itinerary itinerary = new ItineraryBuilder().build();
        AddItineraryCommand command = (AddItineraryCommand) parser.parseCommand(
                ItineraryUtil.getAddItineraryCommand(itinerary));
        assertEquals(new AddItineraryCommand(itinerary), command);
    }

    @Test
    public void parseCommand_clearItinerary() throws Exception {
        assertTrue(parser.parseCommand(ClearItineraryCommand.COMMAND_WORD) instanceof ClearItineraryCommand);
        assertTrue(parser.parseCommand(ClearItineraryCommand.COMMAND_WORD + " 3") instanceof ClearItineraryCommand);
    }

    @Test
    public void parseCommand_deleteItinerary() throws Exception {
        DeleteItineraryCommand command = (DeleteItineraryCommand) parser.parseCommand(
                DeleteItineraryCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteItineraryCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editItinerary() throws Exception {
        Itinerary itinerary = new ItineraryBuilder().build();
        EditItineraryDescriptor descriptor = new EditItineraryDescriptorBuilder(itinerary).build();
        EditItineraryCommand command = (EditItineraryCommand) parser.parseCommand(
                EditItineraryCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " "
                        + ItineraryUtil.getEditItineraryDescriptorDetails(descriptor));
        assertEquals(new EditItineraryCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_findItinerary() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindItineraryCommand command = (FindItineraryCommand) parser.parseCommand(
                FindItineraryCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindItineraryCommand(new ItineraryContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listItinerary() throws Exception {
        assertTrue(parser.parseCommand(ListItineraryCommand.COMMAND_WORD) instanceof ListItineraryCommand);
        assertTrue(parser.parseCommand(ListItineraryCommand.COMMAND_WORD + " 3") instanceof ListItineraryCommand);
    }
}
