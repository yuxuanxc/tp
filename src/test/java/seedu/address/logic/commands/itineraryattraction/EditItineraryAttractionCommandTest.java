package seedu.address.logic.commands.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.itineraryattraction.EditItineraryAttractionCommand.EditItineraryAttractionDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.EditItineraryAttractionDescriptorBuilder;
import seedu.address.testutil.ItineraryAttractionBuilder;
import seedu.address.testutil.ItineraryBuilder;

public class EditItineraryAttractionCommandTest {
    private final EditItineraryAttractionDescriptor descriptor = new EditItineraryAttractionDescriptorBuilder().build();

    @Test
    public void execute_itineraryNotSelected_throwsCommandException() {
        ModelStubNoItinerarySelected model = new ModelStubNoItinerarySelected();
        EditItineraryAttractionCommand editIaCommand = new EditItineraryAttractionCommand(
                INDEX_FIRST, INDEX_FIRST, descriptor);

        assertThrows(CommandException.class, MESSAGE_ITINERARY_NOT_SELECTED, () -> editIaCommand.execute(model));
    }

    @Test
    public void execute_invalidIaIndex_throwsCommandException() {
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(
                new ItineraryAttractionBuilder().build(), INDEX_FIRST).build();
        ModelStubWithItinerarySelected model = new ModelStubWithItinerarySelected(itinerary);
        EditItineraryAttractionCommand editIaCommand = new EditItineraryAttractionCommand(
                INDEX_SECOND, INDEX_FIRST, descriptor);

        // tests with 1 ItineraryAttraction in a day
        assertThrows(CommandException.class, MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, ()
            -> editIaCommand.execute(model));


        Itinerary itinerary2 = new ItineraryBuilder().build();
        ModelStubWithItinerarySelected model2 = new ModelStubWithItinerarySelected(itinerary2);
        EditItineraryAttractionCommand editIaCommand2 = new EditItineraryAttractionCommand(
                INDEX_FIRST, INDEX_FIRST, descriptor);

        // tests with no ItineraryAttraction in a day
        assertThrows(CommandException.class, MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, ()
            -> editIaCommand2.execute(model2));
    }

    @Test
    public void execute_duplicateItineraryAttraction_throwsCommandException() {
        Attraction attraction = new AttractionBuilder().build();
        ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().withAttraction(attraction).build();
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction, INDEX_FIRST).build();
        ModelStubWithItinerarySelected model = new ModelStubWithItinerarySelected(itinerary);
        EditItineraryAttractionCommand editIaCommand = new EditItineraryAttractionCommand(
                INDEX_FIRST, INDEX_FIRST, descriptor);

        assertThrows(CommandException.class, EditItineraryAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION, ()
            -> editIaCommand.execute(model));
    }

    @Test
    public void execute_fieldSpecified_success() {
        Index index = INDEX_FIRST;
        Index day = INDEX_THIRD;

        // original
        //        ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
        //        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction, day)
        //        .build();
        //        EditItineraryAttractionDescriptor descriptor =
        //                new EditItineraryAttractionDescriptorBuilder().withPhone("12312").build();
        //        EditItineraryAttractionCommand editIaCommand = new EditItineraryAttractionCommand(index, day,
        //        descriptor);
        //        ModelStubWithItinerarySelected model = new ModelStubWithItinerarySelected(itinerary);
        //
        //        // expected
        //        ItineraryAttraction expectedIa = new ItineraryAttractionBuilder().withPhone("12312").build();
        //        Itinerary expectedI = new ItineraryBuilder().withItineraryAttraction(expectedIa, day).build();
        //        ModelStubWithItinerarySelected expectedModel = new ModelStubWithItinerarySelected(expectedI);
        //
        //        String expectedMessage = String.format(EditItineraryAttractionCommand
        //        .MESSAGE_EDIT_ATTRACTION_SUCCESS, expectedIa);

        //        ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
        //        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction).build();
        //        Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs
        //        ());
        //        model.setCurrentItinerary(itinerary);
        //
        //        ItineraryAttraction editedIa = new ItineraryAttractionBuilder().build();
        //        EditItineraryAttractionDescriptor descriptor = new EditItineraryAttractionDescriptorBuilder
        //        (editedIa).build();
        //        EditItineraryAttractionCommand editIaCommand = new EditItineraryAttractionCommand(index, day,
        //        descriptor);
        //
        //        String expectedMessage = String.format(EditItineraryAttractionCommand
        //        .MESSAGE_EDIT_ATTRACTION_SUCCESS, editedIa);
        //
        //        Model expectedModel = new ModelManager(new AttractionList(model.getAttractionList()),
        //                new ItineraryList(model.getItineraryList()),
        //                new UserPrefs());
        //        Itinerary itinerary =
        //        expectedModel.setItinerary();


        //        assertCommandSuccess(editIaCommand, model, expectedMessage, expectedModel);
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

        @Override
        public ReadOnlyItineraryAttractionList getItineraryAttractionList() {
            throw new AssertionError("This methods should not be called.");
        }

        @Override
        public ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList() {
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

    /**
     * A Model stub that returns a itinerary selected.
     */
    private class ModelStubWithItinerarySelected extends ModelStub {
        private final Itinerary itinerary;

        public ModelStubWithItinerarySelected(Itinerary itinerary) {
            this.itinerary = itinerary;
        }

        @Override
        public Itinerary getCurrentItinerary() {
            return itinerary;
        }
    }
}
