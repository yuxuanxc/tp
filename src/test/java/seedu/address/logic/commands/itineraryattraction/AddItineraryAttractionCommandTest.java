//package seedu.address.logic.commands.itineraryattraction;
//
//import javafx.collections.ObservableList;
//import org.junit.jupiter.api.Test;
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.ReadOnlyItineraryList;
//import seedu.address.model.ReadOnlyUserPrefs;
//import seedu.address.model.itinerary.ItineraryAttraction;
//import seedu.address.model.itinerary.Itinerary;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.function.Predicate;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//
//public class AddItineraryItineraryAttractionCommandTest {
//
//    @Test
//    public void constructor_nullItineraryAttraction_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddItineraryAttractionCommand(null));
//    }
//
//    @Test
//    public void execute_itineraryAttractionAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingItineraryAttractionAdded modelStub = new ModelStubAcceptingItineraryAttractionAdded();
//        ItineraryAttraction validItineraryAttraction = new ItineraryAttractionBuilder().build();
//
//        CommandResult commandResult = new AddItineraryAttractionCommand(validItineraryAttraction).execute(modelStub);
//
//        assertEquals(String.format(AddItineraryAttractionCommand.MESSAGE_ADD_ATTRACTION_SUCCESS, validItineraryAttraction), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validItineraryAttraction), modelStub.itineraryAttractionsAdded);
//    }
//
//    @Test
//    public void execute_duplicateItineraryAttraction_throwsCommandException() {
//        ItineraryAttraction validItineraryAttraction = new ItineraryAttractionBuilder().build();
//        AddItineraryAttractionCommand addCommand = new AddItineraryAttractionCommand(validItineraryAttraction);
//        ModelStub modelStub = new ModelStubWithItineraryAttraction(validItineraryAttraction);
//
//        assertThrows(CommandException.class,
//                AddItineraryAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION, () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        ItineraryAttraction singaporeZoo = new ItineraryAttractionBuilder().withName("Singapore Zoo").build();
//        ItineraryAttraction nightSafari = new ItineraryAttractionBuilder().withName("Night Safari").build();
//        AddItineraryAttractionCommand addSingaporeZooCommand = new AddItineraryAttractionCommand(singaporeZoo);
//        AddItineraryAttractionCommand addNightSafariCommand = new AddItineraryAttractionCommand(nightSafari);
//
//        // same object -> returns true
//        assertTrue(addSingaporeZooCommand.equals(addSingaporeZooCommand));
//
//        // same values -> returns true
//        AddItineraryAttractionCommand addSingaporeZooCommandCopy = new AddItineraryAttractionCommand(singaporeZoo);
//        assertTrue(addSingaporeZooCommand.equals(addSingaporeZooCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addSingaporeZooCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addSingaporeZooCommand.equals(null));
//
//        // different itineraryAttraction -> returns false
//        assertFalse(addSingaporeZooCommand.equals(addNightSafariCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getItineraryAttractionListFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItineraryAttractionListFilePath(Path itineraryAttractionListFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getItineraryListFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItineraryListFilePath(Path itineraryListFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItineraryAttractionList(ReadOnlyItineraryAttractionList newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyItineraryAttractionList getItineraryAttractionList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteItineraryAttraction(ItineraryAttraction target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItineraryAttraction(ItineraryAttraction target, ItineraryAttraction editedItineraryAttraction) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredItineraryAttractionList(Predicate<ItineraryAttraction> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addItinerary(Itinerary itinerary) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItineraryList(ReadOnlyItineraryList newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyItineraryList getItineraryList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasItinerary(Itinerary itinerary) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteItinerary(Itinerary target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setItinerary(Itinerary target, Itinerary editedItinerary) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Itinerary> getFilteredItineraryList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Itinerary getCurrentItinerary() {
//            throw new AssertionError("This methods should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single itineraryAttraction.
//     */
//    private class ModelStubWithItineraryAttraction extends ModelStub {
//        private final ItineraryAttraction itineraryAttraction;
//
//        ModelStubWithItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            requireNonNull(itineraryAttraction);
//            this.itineraryAttraction = itineraryAttraction;
//        }
//
//        @Override
//        public boolean hasItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            requireNonNull(itineraryAttraction);
//            return this.itineraryAttraction.isSameItineraryAttraction(itineraryAttraction);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the itineraryAttraction being added.
//     */
//    private class ModelStubAcceptingItineraryAttractionAdded extends ModelStub {
//        final ArrayList<ItineraryAttraction> itineraryAttractionsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            requireNonNull(itineraryAttraction);
//            return itineraryAttractionsAdded.stream().anyMatch(itineraryAttraction::isSameItineraryAttraction);
//        }
//
//        @Override
//        public void addItineraryAttraction(ItineraryAttraction itineraryAttraction) {
//            requireNonNull(itineraryAttraction);
//            itineraryAttractionsAdded.add(itineraryAttraction);
//        }
//
//        @Override
//        public ReadOnlyItineraryAttractionList getItineraryAttractionList() {
//            return new ItineraryAttractionList();
//        }
//    }
//
//}
