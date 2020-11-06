package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attraction.Attraction;

/**
 * Panel containing the list of attractions.
 */
public class AttractionListPanel extends UiPart<Region> {
    private static final String FXML = "AttractionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttractionListPanel.class);

    @FXML
    private ListView<Attraction> attractionListView;

    /**
     * Creates a {@code AttractionListPanel} with the given {@code ObservableList}.
     */
    public AttractionListPanel(ObservableList<Attraction> attractionList) {
        super(FXML);
        attractionListView.setItems(attractionList);
        attractionListView.setCellFactory(listView -> new AttractionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Attraction} using a {@code AttractionCard}.
     */
    class AttractionListViewCell extends ListCell<Attraction> {
        @Override
        protected void updateItem(Attraction attraction, boolean empty) {
            super.updateItem(attraction, empty);

            if (empty || attractionListView == null) {
                setGraphic(null);
                setText(null);
            } else {
                switch (attraction.getNumOfFilledFields()) {
                case 0:
                    setGraphic(new Attraction0FieldCard(attraction, getIndex() + 1).getRoot());
                    break;
                case 1:
                    setGraphic(new Attraction1FieldCard(attraction, getIndex() + 1).getRoot());
                    break;
                case 2:
                    setGraphic(new Attraction2FieldCard(attraction, getIndex() + 1).getRoot());
                    break;
                case 3:
                    setGraphic(new Attraction3FieldCard(attraction, getIndex() + 1).getRoot());
                    break;
                case 4:
                    setGraphic(new Attraction4FieldCard(attraction, getIndex() + 1).getRoot());
                    break;
                }

            }
        }
    }

}
