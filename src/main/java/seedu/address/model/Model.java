package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.Attraction;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Attraction> PREDICATE_SHOW_ALL_ATTRACTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' track pad file path.
     */
    Path getTrackPadFilePath();

    /**
     * Sets the user prefs' track pad file path.
     */
    void setTrackPadFilePath(Path trackPadFilePath);

    /**
     * Replaces track pad data with the data in {@code trackPad}.
     */
    void setTrackPad(ReadOnlyTrackPad trackPad);

    /** Returns the TrackPad */
    ReadOnlyTrackPad getTrackPad();

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the track pad.
     */
    boolean hasAttraction(Attraction attraction);

    /**
     * Deletes the given attraction.
     * The attraction must exist in the track pad.
     */
    void deleteAttraction(Attraction target);

    /**
     * Adds the given attraction.
     * {@code attraction} must not already exist in the track pad.
     */
    void addAttraction(Attraction attraction);

    /**
     * Replaces the given attraction {@code target} with {@code editedAttraction}.
     * {@code target} must exist in the track pad.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in 
     * the track pad.
     */
    void setAttraction(Attraction target, Attraction editedAttraction);

    /** Returns an unmodifiable view of the filtered attraction list */
    ObservableList<Attraction> getFilteredAttractionList();

    /**
     * Updates the filter of the filtered attraction list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAttractionList(Predicate<Attraction> predicate);
}
