package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;

import seedu.address.commons.core.index.Index;
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

    public static final String COMMAND_WORD = "delete-itinerary-attraction";
    public static final String MESSAGE_DELETE_ATTRACTION_SUCCESS = "Deleted attraction: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the attraction identified by the index of"
            + "attraction in the selected itinerary.\nParameters: INDEX " + PREFIX_DAY_VISITING + "DAY VISITING \n"
            + "Example: " + COMMAND_WORD + " 1" + PREFIX_DAY_VISITING + "2";


    private final Index index;
    private final Index dayVisiting;

    /**
     * @param index of the attraction in the selected itinerary
     * @param dayVisiting the attraction
     */
    public DeleteItineraryAttractionCommand(Index index, Index dayVisiting) {
        this.index = index;
        this.dayVisiting = dayVisiting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Day day = model.getCurrentItinerary().getDay(dayVisiting);
        ItineraryAttraction itineraryAttractionToDelete = day.getItineraryAttractions().get(index.getZeroBased());

        model.getCurrentItinerary().deleteItineraryAttraction(index, dayVisiting);

        return new CommandResult(String.format(MESSAGE_DELETE_ATTRACTION_SUCCESS, itineraryAttractionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteItineraryAttractionCommand // instanceof handles nulls
                && index.equals(((DeleteItineraryAttractionCommand) other).index)); // state check
    }
}
