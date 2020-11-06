package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Attraction;

/**
 * An UI component that displays information of a {@code Attraction}.
 */
public class AttractionCard extends UiPart<Region> {

    public final Attraction attraction;

    @FXML
    private Label id;

    /**
     * Creates a {@code AttractionCode} with the given {@code FXML}.
     */
    public AttractionCard(Attraction attraction, int displayedIndex, String FXML) {
        super(FXML);
        this.attraction = attraction;
        id.setText(displayedIndex + ". ");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttractionCard)) {
            return false;
        }

        // state check
        AttractionCard card = (AttractionCard) other;
        return id.getText().equals(card.id.getText())
                && attraction.equals(card.attraction);
    }
}
