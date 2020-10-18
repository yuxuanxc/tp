package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.ItineraryAttraction;


/**
 * Adds an attraction to the attractions list in TrackPad.
 */
public class AddItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "add itinerary attraction";

    // todo copy test case from AddCommand
    // todo update the usage message
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction identified by the index number used"
            + " in the displayed attraction list to the itinerary identified by the the index number used in the"
            + " displayed itinerary list.\n "
            + "Parameters: ATTRACTIONINDEX ITINERARYINDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 1";

    public static final String MESSAGE_ADD_ATTRACTION_SUCCESS = "Added Attraction: %1$s to Itinerary: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the itinerary.";

    private final ItineraryAttraction toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Attraction}
     */
    public AddItineraryAttractionCommand(ItineraryAttraction itineraryAttraction) {
        requireNonNull(itineraryAttraction);
        toAdd = itineraryAttraction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getCurrentItinerary().contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.getCurrentItinerary().addItineraryAttraction(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_ATTRACTION_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItineraryAttractionCommand // instanceof handles nulls
                && toAdd.equals(((AddItineraryAttractionCommand) other).toAdd));
    }
}
