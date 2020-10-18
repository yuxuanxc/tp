//package seedu.address.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import seedu.address.commons.core.Messages;
//import seedu.address.commons.core.index.Index;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.attraction.Attraction;
//import seedu.address.model.itinerary.Itinerary;
//import seedu.address.model.itinerary.ItineraryAttraction;
//
///**
// * Adds an attraction identified using it's displayed index from the attractions list to an itinerary identified
// * using it's displayed index from the itinerary list in TrackPad.
// */
//public class AddiCommand extends Command {
//
//    public static final String COMMAND_WORD = "addi";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction identified by the index number used"
//            + " in the displayed attraction list to the itinerary identified by the the index number used in the"
//            + " displayed itinerary list.\n "
//            + "Parameters: ATTRACTIONINDEX ITINERARYINDEX (must be a positive integer) "
//            + "Example: " + COMMAND_WORD + " 1 1";
//
//    public static final String MESSAGE_ADD_ATTRACTION_SUCCESS = "Added Attraction: %1$s to Itinerary: %1$s";
//    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the itinerary.";
//
//    private final Index attractionIndex;
//    private final Index itineraryIndex;
//
//    /**
//     * @param attractionIndex of the attraction in the filtered attraction list to add
//     * @param itineraryIndex  of the itinerary in the filtered itinerary list to add to
//     */
//    public AddiCommand(Index attractionIndex, Index itineraryIndex) {
//        this.attractionIndex = attractionIndex;
//        this.itineraryIndex = itineraryIndex;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<Attraction> lastShownAttractionList = model.getFilteredAttractionList();
//        List<Itinerary> lastShownItineraryList = model.getFilteredItineraryList();
//
//        if (attractionIndex.getZeroBased() >= lastShownAttractionList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
//        }
//
//        if (itineraryIndex.getZeroBased() >= lastShownItineraryList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
//        }
//
//        ItineraryAttraction itineraryAttractionToAdd =
//                new ItineraryAttraction(lastShownAttractionList.get(attractionIndex.getZeroBased()),
//                        null, null, -1);
//        Itinerary itineraryToAddTo = lastShownItineraryList.get(itineraryIndex.getZeroBased());
//
//        if (itineraryToAddTo.contains(itineraryAttractionToAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
//        }
//
//        itineraryToAddTo.addItineraryAttraction(itineraryAttractionToAdd);
//        return new CommandResult(String.format(MESSAGE_ADD_ATTRACTION_SUCCESS, itineraryAttractionToAdd));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof AddiCommand // instanceof handles nulls
//                && attractionIndex.equals(((AddiCommand) other).attractionIndex))
//                && itineraryIndex.equals(((AddiCommand) other).itineraryIndex);
//    }
//
//}
