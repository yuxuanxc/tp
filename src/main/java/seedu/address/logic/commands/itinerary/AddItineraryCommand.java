package seedu.address.logic.commands.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Itinerary;

public class AddItineraryCommand extends Command {
    public static final String COMMAND_WORD = "add-itinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an itinerary to TrackPad. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] ";

    public static final String MESSAGE_SUCCESS = "New itinerary added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITINERARY = "This itinerary already exists in TrackPad";

    private final Itinerary toAdd;

    /**
     * Creates an AddItineraryCommand to add the specified {@code Itinerary}
     */
    public AddItineraryCommand(Itinerary itinerary) {
        requireNonNull(itinerary);
        toAdd = itinerary;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasItinerary(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITINERARY);
        }

        model.addItinerary(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddItineraryCommand // instanceof handles nulls
                && toAdd.equals(((AddItineraryCommand) other).toAdd));
    }
}
