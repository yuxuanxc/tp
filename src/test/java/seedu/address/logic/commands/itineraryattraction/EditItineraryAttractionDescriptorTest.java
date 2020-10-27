package seedu.address.logic.commands.itineraryattraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EIFFEL_IA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MBS_IA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_RANGE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VISITED_MBS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.testutil.EditItineraryAttractionDescriptorBuilder;

import java.nio.file.Path;
import java.util.function.Predicate;

public class EditItineraryAttractionDescriptorTest {
    // A itinerary time instance for testing exceptions
    private final ItineraryTime timeObject = new ItineraryTime("1000");

    @Test
    public void execute_itineraryNotSelected_throwsCommandException() {
        ModelStubNoItinerarySelected model = new ModelStubNoItinerarySelected();

        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, timeObject, timeObject, INDEX_FIRST);
        assertThrows(CommandException.class, MESSAGE_ITINERARY_NOT_SELECTED, () -> addIaCommand.execute(model));
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryAttractionDescriptor descriptorWithSameValues =
                new EditItineraryAttractionDescriptor(DESC_EIFFEL_IA);
        assertTrue(DESC_EIFFEL_IA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EIFFEL_IA.equals(DESC_EIFFEL_IA));

        // null -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(null));

        // different types -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(5));

        // different values -> returns false
        assertFalse(DESC_EIFFEL_IA.equals(DESC_MBS_IA));

        // different name -> returns false
        EditItineraryAttractionDescriptor editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withName(VALID_NAME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different phone -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withPhone(VALID_PHONE_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different email -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withEmail(VALID_EMAIL_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different address -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withAddress(VALID_ADDRESS_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different location -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withLocation(VALID_LOCATION_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different description -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withDescription(VALID_DESCRIPTION_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different opening hours -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withOpeningHours(VALID_OPENING_HOURS_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different price range -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withPriceRange(VALID_PRICE_RANGE_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different rating -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withRating(VALID_RATING_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different visited -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withVisited(VALID_VISITED_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different tags -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withTags(VALID_TAG_ACTIVITY).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different start time -> returns false
        editedEiffel = new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA)
                .withStartTime(VALID_START_TIME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

        // different end time -> returns false
        editedEiffel =
                new EditItineraryAttractionDescriptorBuilder(DESC_EIFFEL_IA).withEndTime(VALID_END_TIME_MBS).build();
        assertFalse(DESC_EIFFEL_IA.equals(editedEiffel));

    }


    /**
     * A default model stub that throws exception for all method calls.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAttractionListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttractionListFilePath(Path attractionListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getItineraryListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItineraryListFilePath(Path itineraryListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttractionList(ReadOnlyAttractionList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAttractionList getAttractionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttraction(Attraction target, Attraction editedAttraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markVisitedAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItineraryList(ReadOnlyItineraryList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyItineraryList getItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItinerary(Itinerary target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItinerary(Itinerary target, Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Itinerary> getFilteredItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Itinerary getCurrentItinerary() {
            throw new AssertionError("This methods should not be called.");
        }
    }

    /**
     * A Model stub that returns no itinerary selected.
     */
    private class ModelStubNoItinerarySelected extends ModelStub {
        @Override
        public Itinerary getCurrentItinerary() {
            return null;
        }
    }


}
