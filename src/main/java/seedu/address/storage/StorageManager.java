package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackPad;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TrackPad data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackPadStorage trackPadStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TrackPadStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackPadStorage trackPadStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.trackPadStorage = trackPadStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TrackPad methods ==============================

    @Override
    public Path getTrackPadFilePath() {
        return trackPadStorage.getTrackPadFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackPad> readTrackPad() throws DataConversionException, IOException {
        return readTrackPad(trackPadStorage.getTrackPadFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackPad> readTrackPad(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackPadStorage.readTrackPad(filePath);
    }

    @Override
    public void saveTrackPad(ReadOnlyTrackPad trackPad) throws IOException {
        saveTrackPad(trackPad, trackPadStorage.getTrackPadFilePath());
    }

    @Override
    public void saveTrackPad(ReadOnlyTrackPad trackPad, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackPadStorage.saveTrackPad(trackPad, filePath);
    }

}
