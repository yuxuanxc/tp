package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.model.itinerary.Itinerary;

/**
 * A utility class for Itinerary.
 */
public class ItineraryUtil {

    /**
     * Returns an add itinerary command string for adding the {@code itinerary}.
     */
    public static String getAddItineraryCommand(Itinerary itinerary) {
        return AddItineraryCommand.COMMAND_WORD + " " + getItineraryDetails(itinerary);
    }

    /**
     * Returns the part of command string for the given {@code itinerary}'s details.
     */
    public static String getItineraryDetails(Itinerary itinerary) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + itinerary.getName().fullName + " ");
        sb.append(PREFIX_DESCRIPTION + itinerary.getDescription().value + " ");
        sb.append(PREFIX_START_DATE + itinerary.getStartDate().toString() + " ");
        sb.append(PREFIX_END_DATE + itinerary.getEndDate().toString() + " ");
        //todo add more depending on final implementation
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditItineraryDescriptor}'s details.
     */
    // todo add when edit itinerary command is up
    //public static String getEditItineraryDescriptorDetails(EditCommand.EditDescriptor descriptor) {
    //}
}
