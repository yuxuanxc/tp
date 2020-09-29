package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAttraction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.EIFFEL_TOWER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Phone;

public class JsonAdaptedAttractionTest {
    private static final String INVALID_NAME = "Sing@poreZ00";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = EIFFEL_TOWER.getName().toString();
    private static final String VALID_PHONE = EIFFEL_TOWER.getPhone().toString();
    private static final String VALID_EMAIL = EIFFEL_TOWER.getEmail().toString();
    private static final String VALID_ADDRESS = EIFFEL_TOWER.getAddress().toString();
    private static final String VALID_LOCATION = EIFFEL_TOWER.getLocation().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = EIFFEL_TOWER.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAttractionDetails_returnsAttraction() throws Exception {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(EIFFEL_TOWER);
        assertEquals(EIFFEL_TOWER, attraction.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_LOCATION,
                        VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_LOCATION,
                        VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(VALID_NAME,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_LOCATION,
                        VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(VALID_NAME,
                VALID_PHONE, null, VALID_ADDRESS, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_LOCATION,
                        VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_LOCATION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_LOCATION,
                        VALID_TAGS);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_LOCATION,
                        invalidTags);
        assertThrows(IllegalValueException.class, attraction::toModelType);
    }

}
