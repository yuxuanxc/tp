package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ItineraryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItineraryDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ItineraryDate(invalidDate));
    }

    @Test
    public void isValidItineraryDate() {
        // null date
        assertThrows(NullPointerException.class, () -> ItineraryDate.isValidDate(null));

        assertFalse(ItineraryDate.isValidDate("")); // empty string
        assertFalse(ItineraryDate.isValidDate(" ")); //spaces only

        assertTrue(ItineraryDate.isValidDate("31-11-2022"));
    }

}
