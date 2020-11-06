package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * An UI component that displays information of a {@code ItineraryAttraction}.
 */
public class ItineraryAttractionCard extends UiPart<Region> {

    public final ItineraryAttraction itineraryAttraction;

    @FXML
    private Label id;
    @FXML
    private Label startEndTime;

    /**
     * Creates a {@code ItineraryAttractionCode} with the given {@code ItineraryAttraction} and index to display.
     */
    public ItineraryAttractionCard(ItineraryAttraction itineraryAttraction, String fxml) {
        super(fxml);
        this.itineraryAttraction = itineraryAttraction.getItineraryAttraction();

        id.setText(itineraryAttraction.getIndex() + ". ");

        startEndTime.setText("\uD83D\uDD56 " + itineraryAttraction.getStartTime().toString()
                + "-"
                + itineraryAttraction.getEndTime().toString());
        startEndTime.setWrapText(true);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryAttractionCard)) {
            return false;
        }

        // state check
        ItineraryAttractionCard card = (ItineraryAttractionCard) other;
        return id.getText().equals(card.id.getText())
                && itineraryAttraction.equals(card.itineraryAttraction)
                && startEndTime.getText().equals(card.startEndTime.getText());
    }
}
