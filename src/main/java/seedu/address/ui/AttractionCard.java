package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Visited;

/**
 * An UI component that displays information of a {@code Attraction}.
 */
public class AttractionCard extends UiPart<Region> {

    private static final String FXML = "AttractionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Attraction attraction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;
    @FXML
    private Label description;
    @FXML
    private Label openingHours;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public AttractionCard(Attraction attraction, int displayedIndex) {
        super(FXML);
        this.attraction = attraction;
        id.setText(displayedIndex + ". ");
        name.setText(attraction.getName().fullName);
        phone.setText(attraction.getPhone().value);
        address.setText(attraction.getAddress().value);
        email.setText(attraction.getEmail().value);
        locale.setText(attraction.getLocation().value);
        description.setText(attraction.getDescription().value);
        openingHours.setText("Opening Hours: " + attraction.getOpeningHours().value);
        if (attraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(attraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }
        if (attraction.getRating().toString() != "") {
            Label rating = new Label(attraction.getRating().toString());
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }
        if (attraction.getVisited().toString() != "") {
            Label visited = new Label(attraction.getVisited().toString());
            if (attraction.getVisited().equals(new Visited("TRUE"))) {
                visited.setText(attraction.getVisited().toString());
            } else {
                visited.setText("Not Visited");
            }
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }
        attraction.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
