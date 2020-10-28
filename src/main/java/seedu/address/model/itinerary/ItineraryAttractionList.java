package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyItineraryAttractionList;

public class ItineraryAttractionList implements ReadOnlyItineraryAttractionList {

    private Itinerary currentItinerary;
    private final ObservableList<ItineraryAttraction> internalList = FXCollections.observableArrayList();
//    private final ObservableList<ItineraryAttraction> internalUnmodifiableList =
//            FXCollections.unmodifiableObservableList(internalList);

    public ItineraryAttractionList(Itinerary currentItinerary) {
        this.currentItinerary = currentItinerary;
        setItineraryAttractionList(currentItinerary);
    }

    public void setItineraryAttractionList(Itinerary currentItinerary) {
        requireNonNull(currentItinerary);
        for (Day day : currentItinerary.getDays()) {
            for (ItineraryAttraction itineraryAttraction : day.getItineraryAttractions())
                internalList.add(itineraryAttraction);
        }
    }

    public ObservableList<ItineraryAttraction> getItineraryAttractionList() {
        return internalList;
    }
}
