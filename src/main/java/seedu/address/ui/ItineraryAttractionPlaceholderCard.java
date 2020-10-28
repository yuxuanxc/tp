package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * An UI component that displays information of a {@code ItineraryAttractionPlaceholder}.
 */
public class ItineraryAttractionPlaceholderCard extends UiPart<Region> {

    private static final String FXML = "ItineraryAttractionPlaceholderCard.fxml";

    public final ItineraryAttraction itineraryAttractionPlaceholder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label day;

    /**
     * Creates a {@code ItineraryAttractionPlaceholderCode} with the given {@code ItineraryAttractionPlaceholder}.
     */
    public ItineraryAttractionPlaceholderCard(ItineraryAttraction itineraryAttractionPlaceholder) {
        super(FXML);
        this.itineraryAttractionPlaceholder = itineraryAttractionPlaceholder;
        day.setText(itineraryAttractionPlaceholder.toString());
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItineraryAttractionPlaceholderCard)) {
            return false;
        }

        // state check
        ItineraryAttractionPlaceholderCard card = (ItineraryAttractionPlaceholderCard) other;
        return day.getText().equals(card.day.getText())
                && itineraryAttractionPlaceholder.equals(card.itineraryAttractionPlaceholder);
    }
}
