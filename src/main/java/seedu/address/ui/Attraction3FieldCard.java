package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.attraction.Attraction;

public class Attraction3FieldCard extends AttractionCard {

    private static final String FXML = "AttractionListCard3Field.fxml";

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
    private Label id;
    @FXML
    private Label name;

    @FXML
    // For JavaFX, rename location to locale as location is a reserved keyword in JavaFX
    private Label locale;

    //optional fields
    @FXML
    private Label field1;
    @FXML
    private Label field2;
    @FXML
    private Label field3;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public Attraction3FieldCard(Attraction attraction, int displayedIndex) {
        super(attraction, displayedIndex, FXML);

        this.attraction = attraction;

        id.setText(displayedIndex + ". ");

        name.setText(attraction.getName().fullName);
        name.setWrapText(true);

        locale.setText("\uD83C\uDF0E " + attraction.getLocation().value);
        locale.setWrapText(true);

        //optional fields

        int fieldsFilled = 0;

        if (!attraction.getAddress().value.isEmpty()) {
            String address = "\uD83C\uDFE0 " + attraction.getAddress().value;
            field1.setText(address);
            field1.setWrapText(true);
            fieldsFilled++;
        }

        if (!attraction.getPhone().value.isEmpty()) {
            String phone = "📞 " + attraction.getPhone().value;
            if (fieldsFilled == 0) {
                field1.setText(phone);
            } else {
                field2.setText(phone);
                field2.setWrapText(true);
            }
            fieldsFilled++;
        }

        if (!attraction.getEmail().value.isEmpty()) {
            String email = "\uD83D\uDCE7 " + attraction.getEmail().value;
            if (fieldsFilled == 0) {
                field1.setText(email);
            } else if (fieldsFilled == 1) {
                field2.setText(email);
            } else {
                field3.setText(email);
            }
            fieldsFilled++;
        }

        if (!attraction.getOpeningHours().value.isEmpty()) {
            String openingHours = "\uD83C\uDE3A " + attraction.getOpeningHours().value;
            if (fieldsFilled == 0) {
                field1.setText(openingHours);
            } else if (fieldsFilled == 1) {
                field2.setText(openingHours);
            } else {
                field3.setText(openingHours);
            }
            fieldsFilled++;
        }

        if (!attraction.getDescription().value.isEmpty()) {
            String description = attraction.getDescription().value;
            if (fieldsFilled == 0) {
                field1.setText(description);
            } else if (fieldsFilled == 1) {
                field2.setText(description);
            } else {
                field3.setText(description);
            }
        }

        field1.setWrapText(true);
        field2.setWrapText(true);
        field3.setWrapText(true);

        if (attraction.getPriceRange().toString() != "") {
            Label priceRange = new Label(attraction.getPriceRange().toString());
            priceRange.setStyle("-fx-background-color: #800;");
            tags.getChildren().add(priceRange);
        }

        if (attraction.getRating().toString() != "") {
            Label rating = new Label(attraction.getRating().toString() + "\u2605");
            rating.setStyle("-fx-background-color: #080;");
            tags.getChildren().add(rating);
        }

        if (attraction.getVisited().toString() != "") {
            Label visited = new Label(attraction.getVisited().toString());
            visited.setText(attraction.getVisited().toString());
            visited.setStyle("-fx-background-color: #9933ff;");
            tags.getChildren().add(visited);
        }

        attraction.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}
