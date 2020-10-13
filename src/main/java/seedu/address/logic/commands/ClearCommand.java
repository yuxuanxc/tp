package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AttractionList;
import seedu.address.model.Model;

/**
 * Clears all the attractions in the attractions list in TrackPad.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TrackPad's attractions has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAttractionList(new AttractionList());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
