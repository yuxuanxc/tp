package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * Adds an attraction to the attractions list in TrackPad.
 */
public class AddItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "add-itinerary-attraction";
    public static final String MESSAGE_ADD_ATTRACTION_SUCCESS = "Added Attraction: %1$s to Itinerary: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the itinerary.";
    public static final String MESSAGE_INVALID_START_TIME = "The start time cannot be later than end time.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction identified by the index in "
            + "attraction list to the selected itinerary.\n "
            + "Parameters: INDEX " + PREFIX_START_TIME + "START_TIME " + PREFIX_END_TIME + "END_TIME "
            + PREFIX_DAY_VISITING + "DAY_VISITING "
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1600 " + PREFIX_DAY_VISITING + "3";

    private final Index index;
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;
    private final Index dayVisited;

    /**
     * Creates an AddAttractionCommand to add the specified {@code Attraction}
     */
    public AddItineraryAttractionCommand(Index index, ItineraryTime startTime, ItineraryTime endTime,
                                         Index dayVisited) {
        requireNonNull(index);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(dayVisited);
        this.index = index;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayVisited = dayVisited;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentItinerary() == null) {
            throw new CommandException(MESSAGE_ITINERARY_NOT_SELECTED);
        }

        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToAdd = lastShownList.get(index.getZeroBased());
        ItineraryAttraction itineraryAttractionToAdd = new ItineraryAttraction(attractionToAdd, startTime, endTime);

        if (model.getCurrentItinerary().getDay(dayVisited).contains(itineraryAttractionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.getCurrentItinerary().addItineraryAttraction(itineraryAttractionToAdd, dayVisited);
        return new CommandResult(String.format(MESSAGE_ADD_ATTRACTION_SUCCESS, itineraryAttractionToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItineraryAttractionCommand // instanceof handles nulls
                && index.equals(((AddItineraryAttractionCommand) other).index)
                && startTime.equals(((AddItineraryAttractionCommand) other).startTime)
                && endTime.equals(((AddItineraryAttractionCommand) other).endTime)
                && dayVisited.equals(((AddItineraryAttractionCommand) other).dayVisited));
    }
}
