package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;
import static seedu.address.testutil.TypicalAttractions.JURONG_BIRD_PARK;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.getTypicalTrackPad;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackPad;
import seedu.address.model.TrackPad;

public class JsonTrackPadStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackPadStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTrackPad_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTrackPad(null));
    }

    private java.util.Optional<ReadOnlyTrackPad> readTrackPad(String filePath) throws Exception {
        return new JsonTrackPadStorage(Paths.get(filePath)).readTrackPad(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTrackPad("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTrackPad("notJsonFormatTrackPad.json"));
    }

    @Test
    public void readTrackPad_invalidAttractionTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrackPad("invalidAttractionTrackPad.json"));
    }

    @Test
    public void readTrackPad_invalidAndValidAttractionTrackPad_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTrackPad("invalidAndValidAttractionTrackPad.json"));
    }

    @Test
    public void readAndSaveTrackPad_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTrackPad.json");
        TrackPad original = getTypicalTrackPad();
        JsonTrackPadStorage jsonTrackPadStorage = new JsonTrackPadStorage(filePath);

        // Save in new file and read back
        jsonTrackPadStorage.saveTrackPad(original, filePath);
        ReadOnlyTrackPad readBack = jsonTrackPadStorage.readTrackPad(filePath).get();
        assertEquals(original, new TrackPad(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAttraction(MBS);
        original.removeAttraction(JURONG_BIRD_PARK);
        jsonTrackPadStorage.saveTrackPad(original, filePath);
        readBack = jsonTrackPadStorage.readTrackPad(filePath).get();
        assertEquals(original, new TrackPad(readBack));

        // Save and read without specifying file path
        original.addAttraction(EIFFEL_TOWER);
        jsonTrackPadStorage.saveTrackPad(original); // file path not specified
        readBack = jsonTrackPadStorage.readTrackPad().get(); // file path not specified
        assertEquals(original, new TrackPad(readBack));

    }

    @Test
    public void saveTrackPad_nullTrackPad_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackPad(null, "SomeFile.json"));
    }

    /**
     * Saves {@code trackPad} at the specified {@code filePath}.
     */
    private void saveTrackPad(ReadOnlyTrackPad trackPad, String filePath) {
        try {
            new JsonTrackPadStorage(Paths.get(filePath))
                    .saveTrackPad(trackPad, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTrackPad_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTrackPad(new TrackPad(), null));
    }
}
