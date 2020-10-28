package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Panel containing the list of itineraries.
 */
public class ItineraryListPanel extends UiPart<Region> {
    private static final String FXML = "ItineraryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryListPanel.class);

    @FXML
    private ListView<Itinerary> itineraryListView;

    @FXML
    private ListView<ItineraryAttraction> itineraryAttractionListView;

    @FXML
    private TabPane pane;

    private Tab itineraryTab;
    private Tab itineraryAttractionTab;

    /**
     * Creates a {@code ItineraryListPanel} with the given {@code ObservableList}.
     */
    public ItineraryListPanel(ObservableList<Itinerary> itineraryList,
                              ObservableList<ItineraryAttraction> itineraryAttractionList) {
        super(FXML);
        itineraryListView.setItems(itineraryList);
        itineraryListView.setCellFactory(listView -> new ItineraryListViewCell());
//        itineraryAttractionListView.setItems(itineraryAttractionList);
//        itineraryAttractionListView.setCellFactory(listView -> new ItineraryAttractionListViewCell());
//        pane.getSelectionModel().select(0);
//        this.itineraryTab = pane.getTabs().get(0);
//        this.itineraryAttractionTab = pane.getTabs().get(1);
    }

    public void changeTabView(int index) {
        pane.getSelectionModel().select(index);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Itinerary} using a {@code ItineraryCard}.
     */
    class ItineraryListViewCell extends ListCell<Itinerary> {
        @Override
        protected void updateItem(Itinerary itinerary, boolean empty) {
            super.updateItem(itinerary, empty);

            if (empty || itineraryListView == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItineraryListCard(itinerary, getIndex() + 1).getRoot());
            }
        }
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
            } else {
                setGraphic(new ItineraryAttractionListCard(itineraryAttraction, getIndex() + 1).getRoot());
            }
        }
    }

}
