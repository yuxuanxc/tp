package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.SUNTEC;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttractionBuilder;

public class AttractionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Attraction attraction = new AttractionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> attraction.getTags().remove(0));
    }

    @Test
    public void isSameAttraction() {
        // same object -> returns true
        assertTrue(MBS.isSameAttraction(MBS));

        // null -> returns false
        assertFalse(MBS.isSameAttraction(null));

        // different phone and email -> returns false
        Attraction editedMbs = new AttractionBuilder(MBS).withPhone(VALID_PHONE_EIFFEL).withEmail(VALID_EMAIL_EIFFEL)
                .build();
        assertFalse(MBS.isSameAttraction(editedMbs));

        // different name -> returns false
        editedMbs = new AttractionBuilder(MBS).withName(VALID_NAME_EIFFEL).build();
        assertFalse(MBS.isSameAttraction(editedMbs));

        // same name, same phone, different attributes -> returns true
        editedMbs = new AttractionBuilder(MBS).withEmail(VALID_EMAIL_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
                .withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMbs));

        // same name, same email, different attributes -> returns true
        editedMbs = new AttractionBuilder(MBS).withPhone(VALID_PHONE_EIFFEL).withAddress(VALID_ADDRESS_EIFFEL)
                .withTags(VALID_TAG_SIGHTSEEING).build();
        assertTrue(MBS.isSameAttraction(editedMbs));

        // same name, same phone, same email, different attributes -> returns true
        editedMbs = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_EIFFEL).withTags(VALID_TAG_SIGHTSEEING)
                .build();
        assertTrue(MBS.isSameAttraction(editedMbs));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attraction mbsCopy = new AttractionBuilder(MBS).build();
        assertTrue(MBS.equals(mbsCopy));

        // same object -> returns true
        assertTrue(MBS.equals(MBS));

        // null -> returns false
        assertFalse(MBS.equals(null));

        // different type -> returns false
        assertFalse(MBS.equals(5));

        // different person -> returns false
        assertFalse(MBS.equals(SUNTEC));

        // different name -> returns false
        Attraction editedSuntec = new AttractionBuilder(SUNTEC).withName(VALID_NAME_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different phone -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withPhone(VALID_PHONE_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different email -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withEmail(VALID_EMAIL_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different address -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withAddress(VALID_ADDRESS_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different location -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withLocation(VALID_LOCATION_EIFFEL).build();
        assertFalse(SUNTEC.equals(editedSuntec));

        // different tags -> returns false
        editedSuntec = new AttractionBuilder(SUNTEC).withTags(VALID_TAG_SIGHTSEEING).build();
        assertFalse(SUNTEC.equals(editedSuntec));
    }
}
