package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddiCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddiCommandParser implements Parser<AddiCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddiCommand
     * and returns an AddiCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddiCommand parse(String args) throws ParseException {
        try {
            // todo might need to find a better place for splitting the input indexes. Maybe ParserUtil?
            String[] indexes = args.trim().split(" ");
            Index attractionIndex = ParserUtil.parseIndex(indexes[0]);
            Index itineraryIndex = ParserUtil.parseIndex(indexes[1]);

            return new AddiCommand(attractionIndex, itineraryIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddiCommand.MESSAGE_USAGE), pe);
        }
    }
}
