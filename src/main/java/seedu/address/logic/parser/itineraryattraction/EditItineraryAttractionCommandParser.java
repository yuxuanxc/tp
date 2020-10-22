package seedu.address.logic.parser.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditItineraryAttractionCommand object
 */
public class EditItineraryAttractionCommandParser implements Parser<EditItineraryAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditItineraryAttractionCommand
     * and returns an EditItineraryAttractionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditItineraryAttractionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_TIME, PREFIX_END_TIME,
                PREFIX_DAY_VISITING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditItineraryAttractionCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_VISITING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditItineraryAttractionCommand.MESSAGE_USAGE));
        }

        Index dayVisiting = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DAY_VISITING).get());

        EditItineraryAttractionDescriptor editItiAttrDesc = new EditItineraryAttractionDescriptor();

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editItiAttrDesc.setStartTime(ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editItiAttrDesc.setEndTime(ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }

        if (!editItiAttrDesc.isAnyFieldEdited()) {
            throw new ParseException(EditItineraryAttractionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItineraryAttractionCommand(index, dayVisiting, editItiAttrDesc);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
