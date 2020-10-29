package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryAttractionDayCounter;

/**
 * Panel containing the list of itinerary attractions.
 */
public class ItineraryAttractionListPanel extends UiPart<Region> {
    private static final String FXML = "ItineraryAttractionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryAttractionListPanel.class);
    private int indexCounter = 0;

    @FXML
    private ListView<ItineraryAttraction> itineraryAttractionListView;

    /**
     * Creates a {@code ItineraryAttractionListPanel} with the given {@code ObservableList}.
     */
    public ItineraryAttractionListPanel(ObservableList<ItineraryAttraction> itineraryAttractionList) {
        super(FXML);
        itineraryAttractionListView.setItems(itineraryAttractionList);
        itineraryAttractionListView.setCellFactory(listView -> new ItineraryAttractionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code ItineraryAttraction} using
     * a {@code ItineraryAttractionListCard}.
     */
    class ItineraryAttractionListViewCell extends ListCell<ItineraryAttraction> {
        @Override
        protected void updateItem(ItineraryAttraction itineraryAttraction, boolean empty) {
            super.updateItem(itineraryAttraction, empty);

            if (empty || itineraryAttractionListView == null) {
                setGraphic(null);
                setText(null);
            } else if (itineraryAttraction instanceof ItineraryAttractionDayCounter) {
                indexCounter = 0;
                // indexCounter++;
                setGraphic(new ItineraryAttractionDayCounterCard(itineraryAttraction).getRoot());
            } else {
                indexCounter++;
                System.out.println(indexCounter);
                setGraphic(new ItineraryAttractionListCard(itineraryAttraction, getIndex()).getRoot());
            }
        }
    }

}
