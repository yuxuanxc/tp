package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;


/**
 * Adds an attraction to the attractions list in TrackPad.
 */
public class AddItineraryAttractionCommand extends Command {

    // todo copy test case from AddCommand
    public static final String COMMAND_WORD = "add-itinerary-attraction";
    public static final String MESSAGE_ADD_ATTRACTION_SUCCESS = "Added Attraction: %1$s to Itinerary: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the itinerary.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction identified by the index number used"
            + " in the displayed attraction list to the itinerary identified by the the index number used in the"
            + " displayed itinerary list.\n "
            + "Parameters: " + PREFIX_ATTRACTION + "ATTRACTION_NAME " + PREFIX_START_TIME + "START_TIME "
            + PREFIX_END_TIME + "END_TIME " + PREFIX_DAY_VISITING + "DAY_VISITING "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ATTRACTION + "Singapore Zoo " + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1600 " + PREFIX_DAY_VISITING + "3";

    private final String attractionName;
    private final ItineraryTime startTime;
    private final ItineraryTime endTime;
    private final Index dayVisited;

    /**
     * Creates an AddCommand to add the specified {@code Attraction}
     */
    public AddItineraryAttractionCommand(String attractionName, ItineraryTime startTime, ItineraryTime endTime,
                                         Index dayVisited) {
        assert attractionName != null;
        assert startTime != null;
        assert endTime != null;
        assert dayVisited != null;
        this.attractionName = attractionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayVisited = dayVisited;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Attraction attractionToAdd = null;
        // decide a location for looping
        for (Attraction a : model.getFilteredAttractionList()) {
            if (a.getName().equals(attractionName)) {
                attractionToAdd = a;
                break;
            }
        }

        if (attractionToAdd == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_NAME_GIVEN);
        }

        ItineraryAttraction itineraryAttractionToAdd = new ItineraryAttraction(attractionToAdd, startTime, endTime,
                dayVisited.getZeroBased());

        // todo decide if the looping should be done here.
        for (Day d : model.getCurrentItinerary().getDays()) {
            for (ItineraryAttraction ia : d.getItineraryAttractions()) {
                if (ia.isSameItineraryAttraction(itineraryAttractionToAdd)) {
                    throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
                }
            }
        }

        model.getCurrentItinerary().addItineraryAttraction(itineraryAttractionToAdd,
                itineraryAttractionToAdd.getDayVisiting());
        return new CommandResult(String.format(MESSAGE_ADD_ATTRACTION_SUCCESS, itineraryAttractionToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItineraryAttractionCommand // instanceof handles nulls
                && attractionName.equals(((AddItineraryAttractionCommand) other).attractionName)
                && startTime.equals(((AddItineraryAttractionCommand) other).startTime)
                && endTime.equals(((AddItineraryAttractionCommand) other).endTime)
                && dayVisited.equals(((AddItineraryAttractionCommand) other).dayVisited));
    }
}
