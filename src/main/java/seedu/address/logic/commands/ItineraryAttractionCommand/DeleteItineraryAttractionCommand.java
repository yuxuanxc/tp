package seedu.address.logic.commands.ItineraryAttractionCommand;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.ItineraryAttraction;

import java.util.List;


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

    private final Index targetIndex;

    public DeleteItineraryAttractionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<ItineraryAttraction> lastShownList = model.getCurrentItinerary().getItineraryAttractions();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        ItineraryAttraction itineraryAttractionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.getCurrentItinerary().removeItineraryItem(itineraryAttractionToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_ATTRACTION_SUCCESS, itineraryAttractionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItineraryAttractionCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteItineraryAttractionCommand) other).targetIndex)); // state check
    }
}
