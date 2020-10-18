package seedu.address.logic.parser.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;


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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTRACTION, PREFIX_START_TIME,
                PREFIX_END_TIME, PREFIX_DAY_VISITING);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditItineraryAttractionCommand.MESSAGE_USAGE), pe);
        }

        EditItineraryAttractionDescriptor editItiAttrDesc = new EditItineraryAttractionDescriptor();

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editItiAttrDesc.setStartTime(ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editItiAttrDesc.setEndTime(ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_END_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DAY_VISITING).isPresent()) {
            editItiAttrDesc.setDayVisiting(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DAY_VISITING)
                    .get()).getZeroBased());
        }


        if (!editItiAttrDesc.isAnyFieldEdited()) {
            throw new ParseException(EditItineraryAttractionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItineraryAttractionCommand(index, editItiAttrDesc);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
