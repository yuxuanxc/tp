package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Visited;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * An UI component that displays information of a {@code ItineraryAttraction}.
 */
public class ItineraryAttractionListCard extends UiPart<Region> {

    private static final String FXML = "ItineraryAttractionListCard.fxml";

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
    // Compulsory Fields
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label startEndTime;
    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;

    // Optional Fields
    @FXML
    private Label description;
    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private Label field3;
    @FXML
    private Label field4;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ItineraryAttractionCode} with the given {@code ItineraryAttraction} and index to display.
     */
    public ItineraryAttractionListCard(ItineraryAttraction itineraryAttraction) {
        super(FXML);
        this.itineraryAttraction = itineraryAttraction.getItineraryAttraction();

        id.setText(itineraryAttraction.getIndex() + ". ");

        name.setText(itineraryAttraction.getName().fullName);
        name.setWrapText(true);

        locale.setText("\uD83C\uDF0E " + itineraryAttraction.getLocation().value);
        locale.setWrapText(true);

        startEndTime.setText("\uD83D\uDD56 " + itineraryAttraction.getStartTime().toString()
                + "-"
                + itineraryAttraction.getEndTime().toString());
        startEndTime.setWrapText(true);

        //optional fields
        description.setText(itineraryAttraction.getDescription().value);
        description.setWrapText(true);

        int fieldsFilled = 0;

        if (!itineraryAttraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + itineraryAttraction.getAddress().value;
            field1.setText(address);
            field1.setWrapText(true);
            fieldsFilled++;
        }

        if (!itineraryAttraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + itineraryAttraction.getPhone().value;
            if (fieldsFilled == 0) {
                field1.setText(phone);
            } else {
                field2.setText(phone);
                field2.setWrapText(true);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + itineraryAttraction.getEmail().value;
            if (fieldsFilled == 0) {
                field1.setText(email);
            } else if (fieldsFilled == 1) {
                field2.setText(email);
            } else {
                field3.setText(email);
                field3.setWrapText(true);
            }
            fieldsFilled++;
        }

        if (!itineraryAttraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83C\uDE3A " + itineraryAttraction.getOpeningHours().value;
            if (fieldsFilled == 0) {
                field1.setText(openingHours);
            } else if (fieldsFilled == 1) {
                field2.setText(openingHours);
            } else if (fieldsFilled == 2) {
                field3.setText(openingHours);
            } else {
                field4.setText(openingHours);
                field4.setWrapText(true);
            }
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
