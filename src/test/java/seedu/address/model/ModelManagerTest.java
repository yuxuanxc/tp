package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.SUNTEC;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.NameContainsKeywordsPredicate;
import seedu.address.testutil.TrackPadBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TrackPad(), new TrackPad(modelManager.getTrackPad()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackPadFilePath(Paths.get("track/pad/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackPadFilePath(Paths.get("new/track/pad/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTrackPadFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackPadFilePath(null));
    }

    @Test
    public void setTrackPadFilePath_validPath_setsTrackPadFilePath() {
        Path path = Paths.get("track/pad/file/path");
        modelManager.setTrackPadFilePath(path);
        assertEquals(path, modelManager.getTrackPadFilePath());
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInTrackPad_returnsFalse() {
        assertFalse(modelManager.hasAttraction(MBS));
    }

    @Test
    public void hasAttraction_attractionInTrackPad_returnsTrue() {
        modelManager.addAttraction(MBS);
        assertTrue(modelManager.hasAttraction(MBS));
    }

    @Test
    public void getFilteredAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAttractionList().remove(0));
    }

    @Test
    public void equals() {
        TrackPad trackPad = new TrackPadBuilder().withAttraction(MBS).withAttraction(SUNTEC).build();
        TrackPad differentTrackPad = new TrackPad();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackPad, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackPad, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackPad, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = MBS.getName().fullName.split("\\s+");
        modelManager.updateFilteredAttractionList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(trackPad, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackPadFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackPad, differentUserPrefs)));
    }
}
