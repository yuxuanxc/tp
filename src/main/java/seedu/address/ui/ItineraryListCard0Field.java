package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard0Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard0Field.fxml";

    public final Itinerary itinerary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startEndDate;


    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard0Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);
        this.itinerary = itinerary;

        id.setText(displayedIndex + ". ");

        name.setText(itinerary.getName().fullName);
        name.setWrapText(true);

        startEndDate.setText("\uD83D\uDCC6 " + itinerary.getStartDate().value
                + " to "
                + itinerary.getEndDate().value);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryListCard0Field)) {
            return false;
        }

        // state check
        ItineraryListCard0Field card = (ItineraryListCard0Field) other;
        return id.getText().equals(card.id.getText())
                && itinerary.equals(card.itinerary);
    }

}

