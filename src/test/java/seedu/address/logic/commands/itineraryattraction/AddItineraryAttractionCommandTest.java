package seedu.address.logic.commands.itineraryattraction;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.testutil.ItineraryAttractionBuilder;

public class AddItineraryAttractionCommandTest {

    @Test
    public void constructor_nullItineraryAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItineraryAttractionCommand(null, null, null, null));
    }

    /*@Test
    public void execute_duplicateItineraryAttraction_throwsCommandException() {
        ItineraryTime startTime = new ItineraryTime("1200");
        ItineraryTime endTime = new ItineraryTime("1300");
        ItineraryAttraction validItineraryAttraction =
                new ItineraryAttractionBuilder().withStartTime(startTime).withEndTime(endTime).build();
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(Index.fromOneBased(1),
                startTime, endTime, Index.fromOneBased(2));

                // todo wait for itinerary builder
        ModelStub modelStub = new ModelStubWithItinerary(validItineraryAttraction, 1);
        assertThrows(CommandException.class,
                AddItineraryAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION, () -> addIaCommand.execute(modelStub));
    }*/

    @Test
    public void execute_invalidAttractionIndex_throwsCommandException() {
        ItineraryAttraction validItineraryAttraction = new ItineraryAttractionBuilder().build();
        ItineraryTime startTime = new ItineraryTime("1200");
        ItineraryTime endTime = new ItineraryTime("1300");
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(Index.fromOneBased(9),
                startTime, endTime, Index.fromOneBased(2));

        ModelStubWithAttractionFilteredList model =
                new ModelStubWithAttractionFilteredList(new FilteredList<>(FXCollections.observableArrayList()));

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, () ->
                addIaCommand.execute(model));

    }

    @Test
    public void equals() {

        // test itinerary attraction command equals
        ItineraryTime startTime = new ItineraryTime("1200");
        ItineraryTime endTime = new ItineraryTime("1300");
        Index index = Index.fromOneBased(9);
        Index day = Index.fromOneBased(2);
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        AddItineraryAttractionCommand addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal index
        index = Index.fromOneBased(1);
        addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal day, set addIaCommand index to 1
        day = Index.fromOneBased(1);
        addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        startTime = new ItineraryTime("1159");
        addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal end time
        endTime = new ItineraryTime("1359");
        addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // tests itinerary attraction class
        ItineraryAttraction ia1 = new ItineraryAttractionBuilder().withStartTime(new ItineraryTime("1200")).build();
        ItineraryAttraction ia2 = new ItineraryAttractionBuilder().withStartTime(new ItineraryTime("1200")).build();

        // same object -> returns true
        assertEquals(ia1, ia1);
        assertEquals(ia2, ia2);

        // same values -> returns true
        assertEquals(ia1, ia2);

        // different types -> returns false
        assertNotEquals(ia1, 1);

        // null -> returns false
        assertNotEquals(ia1, null);
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
        public Itinerary getCurrentItinerary() {
            throw new AssertionError("This methods should not be called.");
        }
    }

    /**
     * A Model stub that contains a single attraction.
     */
    private class ModelStubWithAttraction extends ModelStub {

        private final Attraction attraction;

        ModelStubWithAttraction(Attraction attraction) {
            requireNonNull(attraction);
            this.attraction = attraction;
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            requireNonNull(attraction);
            return this.attraction.isSameAttraction(attraction);
        }

    }

    /**
     * A Model stub that contains a empty filtered list of Attraction type.
     */
    private class ModelStubWithAttractionFilteredList extends ModelStub {
        private final FilteredList<Attraction> filteredAttractions;

        ModelStubWithAttractionFilteredList(FilteredList<Attraction> filteredAttractions) {
            requireNonNull(filteredAttractions);
            this.filteredAttractions = filteredAttractions;
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            return filteredAttractions;
        }

    }

    ///**
    // * A Model stub that contains a single Itinerary.
    // */
    //private class ModelStubWithItinerary extends ModelStub {
    //    private final Itinerary itinerary;
    //    ModelStubWithItinerary(ItineraryAttraction attraction, int day) {
    //        requireNonNull(attraction);
    //        this.itinerary = new ItineraryBuilder(); // build a itinerary and put it into model
    //        this.itinerary.addItineraryAttraction(attraction, day);
    //    }
    //}
}
