package seedu.address.logic.commands.itineraryattraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.itinerary.exceptions.ItineraryNotFoundException;
import seedu.address.testutil.ItineraryAttractionBuilder;

import javax.imageio.stream.IIOByteBuffer;


public class AddItineraryAttractionCommandTest {

    @Test
    public void constructor_nullItineraryAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItineraryAttractionCommand(null, null, null, null));
    }

//    @Test
//    public void execute_duplicatItineraryAttraction_throwsCommandException() {
//
//        // todo check if duplicate attraction error is thrown
//        ItineraryAttraction validItineraryAttraction = new ItineraryAttractionBuilder().build();
//        ItineraryTime startTime = new ItineraryTime("1200");
//        ItineraryTime endTime = new ItineraryTime("1300");
//
//        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(Index.fromOneBased(1),
//        startTime
//                , endTime, Index.fromOneBased(2));
//        ModelStub modelStub = new ModelStubWithItineraryAttraction(validItineraryAttraction);
//        assertThrows(CommandException.class,
//                AddItineraryAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION, () -> addIaCommand.execute(modelStub));
//    }

    @Test
    public void execute_invalidAttractionIndex_throwsCommandException() {
        ItineraryAttraction validItineraryAttraction = new ItineraryAttractionBuilder().build();
        ItineraryTime startTime = new ItineraryTime("1200");
        ItineraryTime endTime = new ItineraryTime("1300");
        AddItineraryAttractionCommand addIadCommand = new AddItineraryAttractionCommand(Index.fromOneBased(9),
                startTime, endTime, Index.fromOneBased(2));

        // todo create a model which has not enough attractions inside

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, () ->
                addIadCommand.execute(model));


    }

    @Test
    public void equals() {

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
}
