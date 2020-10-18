package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddItineraryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;

/**
 * Parses input arguments and creates a new AddItineraryCommand object
 */
public class AddItineraryCommandParser implements Parser<AddItineraryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItineraryCommand
     * and returns an AddItineraryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItineraryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_STARTDATE,
                        PREFIX_ENDDATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STARTDATE, PREFIX_ENDDATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryCommand.MESSAGE_USAGE));
        }

        // Name is not optional
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        // Description is optional
        Description description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = new Description();
        }

        // Start date is not optional
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_STARTDATE).get());

        // End date is not optional
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ENDDATE).get());

        Itinerary itinerary = new Itinerary(name, description, startDate, endDate, new ArrayList<Day>());

        return new AddItineraryCommand(itinerary);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
