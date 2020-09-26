package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyTrackPad;

/**
 * A class to access TrackPad data stored as a json file on the hard disk.
 */
public class JsonTrackPadStorage implements TrackPadStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackPadStorage.class);

    private Path filePath;

    public JsonTrackPadStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackPadFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrackPad> readTrackPad() throws DataConversionException {
        return readTrackPad(filePath);
    }

    /**
     * Similar to {@link #readTrackPad()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrackPad> readTrackPad(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackPad> jsonTrackPad = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrackPad.class);
        if (!jsonTrackPad.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackPad.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackPad(ReadOnlyTrackPad trackpad) throws IOException {
        saveTrackPad(trackpad, filePath);
    }

    /**
     * Similar to {@link #saveTrackPad(ReadOnlyTrackPad)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrackPad(ReadOnlyTrackPad trackpad, Path filePath) throws IOException {
        requireNonNull(trackpad);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackPad(trackpad), filePath);
    }

}
