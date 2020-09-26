package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackPad;

/**
 * Represents a storage for {@link seedu.address.model.TrackPad}.
 */
public interface TrackPadStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackPadFilePath();

    /**
     * Returns TrackPad data as a {@link ReadOnlyTrackPad}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackPad> readTrackPad() throws DataConversionException, IOException;

    /**
     * @see #getTrackPadFilePath()
     */
    Optional<ReadOnlyTrackPad> readTrackPad(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackPad} to the storage.
     * @param trackPad cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackPad(ReadOnlyTrackPad trackPad) throws IOException;

    /**
     * @see #saveTrackPad(ReadOnlyTrackPad)
     */
    void saveTrackPad(ReadOnlyTrackPad trackPad, Path filePath) throws IOException;

}
