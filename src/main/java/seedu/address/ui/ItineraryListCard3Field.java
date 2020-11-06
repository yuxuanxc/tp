package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.itinerary.Itinerary;

public class ItineraryListCard3Field extends ItineraryListCard {

    private static final String FXML = "ItineraryListCard3Field.fxml";

    public final Itinerary itinerary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startEndDate;
    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private Label field3;


    /**
     * Creates a {@code ItineraryCode} with the given {@code Itinerary} and index to display.
     */
    public ItineraryListCard3Field(Itinerary itinerary, int displayedIndex) {
        super(itinerary, displayedIndex, FXML);
        this.itinerary = itinerary;

        id.setText(displayedIndex + ". ");

        name.setText(itinerary.getName().fullName);
        name.setWrapText(true);

        startEndDate.setText("\uD83D\uDCC6 " + itinerary.getStartDate().value
                + " to "
                + itinerary.getEndDate().value);

        if (!itinerary.getLocations().isEmpty()) {
            field1.setText("\uD83C\uDF0E " + itinerary.getLocations());
        }


        if (!itinerary.getBudget().value.isEmpty()) {
            field2.setText("\uD83D\uDCB5 " + itinerary.getBudget().toString());
        }

        if (!itinerary.getDescription().value.isEmpty()) {
            field3.setText(itinerary.getDescription().value);
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
        field3.setWrapText(true);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryListCard3Field)) {
            return false;
        }

        // state check
        ItineraryListCard3Field card = (ItineraryListCard3Field) other;
        return id.getText().equals(card.id.getText())
                && itinerary.equals(card.itinerary);
    }

}

