package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SIGHTSEEING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.MBS;
import static seedu.address.testutil.TypicalAttractions.getTypicalTrackPad;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.testutil.AttractionBuilder;

public class TrackPadTest {

    private final TrackPad trackPad = new TrackPad();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), trackPad.getAttractionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackPad.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTrackPad_replacesData() {
        TrackPad newData = getTypicalTrackPad();
        trackPad.resetData(newData);
        assertEquals(newData, trackPad);
    }

    @Test
    public void resetData_withDuplicateAttractions_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Attraction editedMbs = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING)
                .build();
        List<Attraction> newAttractions = Arrays.asList(MBS, editedMbs);
        TrackPadStub newData = new TrackPadStub(newAttractions);

        assertThrows(DuplicateAttractionException.class, () -> trackPad.resetData(newData));
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> trackPad.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInTrackPad_returnsFalse() {
        assertFalse(trackPad.hasAttraction(MBS));
    }

    @Test
    public void hasAttraction_attractionInTrackPad_returnsTrue() {
        trackPad.addAttraction(MBS);
        assertTrue(trackPad.hasAttraction(MBS));
    }

    @Test
    public void hasAttraction_attractionWithSameIdentityFieldsInTrackPad_returnsTrue() {
        trackPad.addAttraction(MBS);
        Attraction editedMbs = new AttractionBuilder(MBS).withAddress(VALID_ADDRESS_MBS).withTags(VALID_TAG_SIGHTSEEING)
                .build();
        assertTrue(trackPad.hasAttraction(editedMbs));
    }

    @Test
    public void getAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> trackPad.getAttractionList().remove(0));
    }

    /**
     * A stub ReadOnlyTrackPad whose persons list can violate interface constraints.
     */
    private static class TrackPadStub implements ReadOnlyTrackPad {
        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        TrackPadStub(Collection<Attraction> persons) {
            this.attractions.setAll(persons);
        }

        @Override
        public ObservableList<Attraction> getAttractionList() {
            return attractions;
        }
    }

}
