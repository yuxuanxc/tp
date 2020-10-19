package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.ItineraryAttraction;


/**
 * Deletes an attraction identified using it's displayed index from the attractions in current itinerary.
 */
public class DeleteItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "delete itinerary attraction";

    // todo update usage message
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the attraction identified by the index number used in the current itinerary.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ATTRACTION_SUCCESS = "Deleted attraction: %1$s";

    private final String attractionName;

    public DeleteItineraryAttractionCommand(String attractionName) {
        this.attractionName = attractionName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ItineraryAttraction itineraryAttractionToDelete = null;
        int targetIndex = 0;

        // todo decide if the looping should be done here.
        for (Day d : model.getCurrentItinerary().getDays()) {
            for (ItineraryAttraction ia : d.getItineraryAttractions()) {
                if (ia.getName().fullName.equals(attractionName)) {
                    itineraryAttractionToDelete = ia;
                    targetIndex++;
                    break;
                }
            }
            // change this index nonsense later
            targetIndex = 0;
        }

        if (itineraryAttractionToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_NAME_GIVEN);
        }

        model.getCurrentItinerary().deleteItineraryAttraction(targetIndex,
                itineraryAttractionToDelete.getDayVisiting());
        return new CommandResult(String.format(MESSAGE_DELETE_ATTRACTION_SUCCESS, itineraryAttractionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItineraryAttractionCommand // instanceof handles nulls
                && attractionName.equals(((DeleteItineraryAttractionCommand) other).attractionName)); // state check
    }
}
