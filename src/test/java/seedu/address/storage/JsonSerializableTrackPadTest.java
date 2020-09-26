package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TrackPad;
import seedu.address.testutil.TypicalAttractions;

public class JsonSerializableTrackPadTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTrackPadTest");
    private static final Path TYPICAL_ATTRACTIONS_FILE = TEST_DATA_FOLDER.resolve("typicalAttractionsTrackPad.json");
    private static final Path INVALID_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("invalidAttractionTrackPad.json");
    private static final Path DUPLICATE_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("duplicateAttractionTrackPad.json");

    @Test
    public void toModelType_typicalAttractionsFile_success() throws Exception {
        JsonSerializableTrackPad dataFromFile = JsonUtil.readJsonFile(TYPICAL_ATTRACTIONS_FILE,
                JsonSerializableTrackPad.class).get();
        TrackPad trackPadFromFile = dataFromFile.toModelType();
        TrackPad typicalAttractionsTrackPad = TypicalAttractions.getTypicalTrackPad();
        assertEquals(trackPadFromFile, typicalAttractionsTrackPad);
    }

    @Test
    public void toModelType_invalidAttractionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackPad dataFromFile = JsonUtil.readJsonFile(INVALID_ATTRACTION_FILE,
                JsonSerializableTrackPad.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAttractions_throwsIllegalValueException() throws Exception {
        JsonSerializableTrackPad dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ATTRACTION_FILE,
                JsonSerializableTrackPad.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTrackPad.MESSAGE_DUPLICATE_ATTRACTION,
                dataFromFile::toModelType);
    }

}
