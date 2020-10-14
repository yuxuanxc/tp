package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.NewiCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Name;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Parses input arguments and creates a new NewiCommand object
 */
public class NewiCommandParser implements Parser<NewiCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the NewiCommand
     * and returns an NewiCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewiCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewiCommand.MESSAGE_USAGE));
        }

        // Name is not optional
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        Itinerary itinerary = new Itinerary(name, new ArrayList<ItineraryAttraction>());

        return new NewiCommand(itinerary);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
