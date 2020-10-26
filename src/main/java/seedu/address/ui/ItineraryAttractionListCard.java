package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Visited;
import seedu.address.model.itinerary.ItineraryAttraction;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code ItineraryAttraction}.
 */
public class ItineraryAttractionListCard extends UiPart<Region> {

    private static final String FXML = "AttractionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final ItineraryAttraction itineraryAttraction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label description;
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
    private Label openingHours;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItineraryAttractionCode} with the given {@code ItineraryAttraction} and index to display.
     */
    public ItineraryAttractionListCard(ItineraryAttraction itineraryAttraction, int displayedIndex) {
        super(FXML);
        this.itineraryAttraction = itineraryAttraction;
        id.setText(displayedIndex + ". ");
        name.setText(itineraryAttraction.getName().fullName);
        locale.setText("\uD83C\uDF0E " + itineraryAttraction.getLocation().value);
        startTime.setText(itineraryAttraction.getStartTime().toString());
        endTime.setText(itineraryAttraction.getEndTime().toString());

        //optional fields
        description.setText(itineraryAttraction.getDescription().value);
        if (itineraryAttraction.getPhone().value.isEmpty()) {
            phone.setText("📞 " + "⛔");
        } else {
            phone.setText("📞 " + itineraryAttraction.getPhone().value);
        }
        if (itineraryAttraction.getAddress().value.isEmpty()) {
            address.setText("\uD83C\uDFE0 " + "⛔");
        } else {
            address.setText("\uD83C\uDFE0 " + itineraryAttraction.getAddress().value);
        }
        if (itineraryAttraction.getEmail().value.isEmpty()) {
            email.setText("\uD83D\uDCE7 " + "⛔");
        } else {
            email.setText("\uD83D\uDCE7 " + itineraryAttraction.getEmail().value);
        }
        if (itineraryAttraction.getOpeningHours().value.isEmpty()) {
            openingHours.setText("\uD83D\uDD56 " + "⛔");
        } else {
            openingHours.setText("\uD83D\uDD56 " + itineraryAttraction.getOpeningHours().value);
        }
        if (itineraryAttraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(itineraryAttraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }
        if (itineraryAttraction.getRating().toString() != "") {
            Label rating = new Label(itineraryAttraction.getRating().toString());
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }
        if (itineraryAttraction.getVisited().toString() != "") {
            Label visited = new Label(itineraryAttraction.getVisited().toString());
            if (itineraryAttraction.getVisited().equals(new Visited("TRUE"))) {
                visited.setText(itineraryAttraction.getVisited().toString());
            } else {
                visited.setText("Not Visited");
            }
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }
        itineraryAttraction.getTags().stream()
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
        if (!(other instanceof ItineraryAttractionListCard)) {
            return false;
        }

        // state check
        ItineraryAttractionListCard card = (ItineraryAttractionListCard) other;
        return id.getText().equals(card.id.getText())
                && itineraryAttraction.equals(card.itineraryAttraction);
    }
}
