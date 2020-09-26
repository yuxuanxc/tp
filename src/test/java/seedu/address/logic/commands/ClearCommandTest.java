package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalTrackPad;

import org.junit.jupiter.api.Test;

import seedu.address.model.TrackPad;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTrackPad_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTrackPad_success() {
        Model model = new ModelManager(getTypicalTrackPad(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTrackPad(), new UserPrefs());
        expectedModel.setTrackPad(new TrackPad());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
