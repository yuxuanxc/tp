package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attraction.Attraction;

/**
 * Represents the in-memory model of the trackPad data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TrackPad trackPad;
    private final UserPrefs userPrefs;
    private final FilteredList<Attraction> filteredAttractions;

    /**
     * Initializes a ModelManager with the given trackPad and userPrefs.
     */
    public ModelManager(ReadOnlyTrackPad trackPad, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(trackPad, userPrefs);

        logger.fine("Initializing with trackPad: " + trackPad + " and user prefs " + userPrefs);

        this.trackPad = new TrackPad(trackPad);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAttractions = new FilteredList<>(this.trackPad.getAttractionList());
    }

    public ModelManager() {
        this(new TrackPad(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackPadFilePath() {
        return userPrefs.getTrackPadFilePath();
    }

    @Override
    public void setTrackPadFilePath(Path trackPadFilePath) {
        requireNonNull(trackPadFilePath);
        userPrefs.setTrackPadFilePath(trackPadFilePath);
    }

    //=========== TrackPad ================================================================================

    @Override
    public void setTrackPad(ReadOnlyTrackPad trackPad) {
        this.trackPad.resetData(trackPad);
    }

    @Override
    public ReadOnlyTrackPad getTrackPad() {
        return trackPad;
    }

    @Override
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return trackPad.hasAttraction(attraction);
    }

    @Override
    public void deleteAttraction(Attraction target) {
        trackPad.removeAttraction(target);
    }

    @Override
    public void addAttraction(Attraction attraction) {
        trackPad.addAttraction(attraction);
        updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
    }

    @Override
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireAllNonNull(target, editedAttraction);

        trackPad.setAttraction(target, editedAttraction);
    }

    //=========== Filtered Attraction List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Attraction} backed by the internal list of
     * {@code versionedTrackPad}
     */
    @Override
    public ObservableList<Attraction> getFilteredAttractionList() {
        return filteredAttractions;
    }

    @Override
    public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
        requireNonNull(predicate);
        filteredAttractions.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return trackPad.equals(other.trackPad)
                && userPrefs.equals(other.userPrefs)
                && filteredAttractions.equals(other.filteredAttractions);
    }

}
