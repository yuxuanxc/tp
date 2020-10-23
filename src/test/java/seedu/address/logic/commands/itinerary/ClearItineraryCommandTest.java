package seedu.address.logic.commands.itinerary;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearItineraryCommandTest {

    @Test
    public void execute_emptyTrackPad_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearItineraryCommand(), model, ClearItineraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyItineraryList_success() {
        Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        expectedModel.setItineraryList(new ItineraryList());

        assertCommandSuccess(new ClearItineraryCommand(), model, ClearItineraryCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
