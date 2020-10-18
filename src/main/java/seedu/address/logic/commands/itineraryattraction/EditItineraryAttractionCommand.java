package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * Edits the details of an existing attraction in the itinerary.
 */
public class EditItineraryAttractionCommand extends Command {

    public static final String COMMAND_WORD = "edit itinerary attraction";

    // todo update the usage message
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": FIXME";
    public static final String MESSAGE_EDIT_ATTRACTION_SUCCESS = "Edited Attraction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in Itinerary.";

    private final Index index;
    private final EditItineraryAttractionDescriptor editItineraryAttractionDescriptor;

    /**
     * @param index                             of the attraction in the filtered attraction list
     *                                          to edit
     * @param editItineraryAttractionDescriptor details to edit the attraction with
     */
    public EditItineraryAttractionCommand(Index index,
                                          EditItineraryAttractionDescriptor editItineraryAttractionDescriptor) {
        requireNonNull(index);
        requireNonNull(editItineraryAttractionDescriptor);

        this.index = index;
        this.editItineraryAttractionDescriptor =
                new EditItineraryAttractionDescriptor(editItineraryAttractionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<ItineraryAttraction> lastShownList = model.getCurrentItinerary().getItineraryAttractions();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        ItineraryAttraction itineraryAttractionToEdit = lastShownList.get(index.getZeroBased());
        ItineraryAttraction editedItineraryAttraction = createEditedItineraryAttraction(itineraryAttractionToEdit,
                editItineraryAttractionDescriptor);

        if (!itineraryAttractionToEdit.isSameItineraryAttraction(editedItineraryAttraction)
                && model.getCurrentItinerary().contains(editedItineraryAttraction)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.getCurrentItinerary().setItineraryAttraction(itineraryAttractionToEdit, editedItineraryAttraction);
        return new CommandResult(String.format(MESSAGE_EDIT_ATTRACTION_SUCCESS, editedItineraryAttraction));
    }

    /**
     * Creates and returns a {@code Attraction} with the details of {@code attractionToEdit}
     * edited with {@code editItineraryAttractionDescriptor}.
     */
    private static ItineraryAttraction createEditedItineraryAttraction(ItineraryAttraction itineraryAttractionToEdit,
                                                                       EditItineraryAttractionDescriptor
                                                                               editItiAttrDesc) {
        assert itineraryAttractionToEdit != null;

        ItineraryTime startTime = editItiAttrDesc.getStartTime().orElse(itineraryAttractionToEdit.getStartTime());
        ItineraryTime endTime = editItiAttrDesc.getEndTime().orElse(itineraryAttractionToEdit.getEndTime());
        int dayVisiting = editItiAttrDesc.getDayVisiting().orElse(itineraryAttractionToEdit.getDayVisiting());

        return new ItineraryAttraction(itineraryAttractionToEdit.getAttraction(), startTime, endTime, dayVisiting);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItineraryAttractionCommand)) {
            return false;
        }

        // state check
        EditItineraryAttractionCommand e = (EditItineraryAttractionCommand) other;
        return index.equals(e.index)
                && editItineraryAttractionDescriptor.equals(e.editItineraryAttractionDescriptor);
    }

    /**
     * Stores the details to edit the attraction with. Each non-empty field value will replace the
     * corresponding field value of the attraction.
     */
    public static class EditItineraryAttractionDescriptor {
        private ItineraryTime startTime;
        private ItineraryTime endTime;
        private int dayVisiting;

        public EditItineraryAttractionDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItineraryAttractionDescriptor(EditItineraryAttractionDescriptor toCopy) {
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setDayVisiting(toCopy.dayVisiting);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(startTime, endTime, dayVisiting);
        }

        public void setStartTime(ItineraryTime startTime) {
            this.startTime = startTime;
        }

        public Optional<ItineraryTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(ItineraryTime endTime) {
            this.endTime = endTime;
        }

        public Optional<ItineraryTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setDayVisiting(int dayVisiting) {
            this.dayVisiting = dayVisiting;
        }

        public Optional<Integer> getDayVisiting() {
            return Optional.ofNullable(dayVisiting);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItineraryAttractionDescriptor)) {
                return false;
            }

            // state check
            EditItineraryAttractionDescriptor e = (EditItineraryAttractionDescriptor) other;

            return getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime())
                    && getDayVisiting().equals(e.getDayVisiting());

        }
    }
}
