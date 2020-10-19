package seedu.address.logic.parser.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.itineraryattraction.DeleteItineraryAttractionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteItineraryAttractionCommand object
 */
public class DeleteItineraryAttractionCommandParser implements Parser<DeleteItineraryAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteItineraryAttractionCommand
     * and returns a DeleteItineraryAttractionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItineraryAttractionCommand parse(String args) throws ParseException {
        try {
            String attractionName = ParserUtil.parseAttractionName(args);
            return new DeleteItineraryAttractionCommand(attractionName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE), pe);
        }
    }

}
