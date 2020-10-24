package seedu.address.testutil;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;

/**
 * A utility class to help with building Itinerary Attraction objects.
 * Depends on AttractionBuilder class.
 */
public class ItineraryAttractionBuilder {
    public static final Attraction DEFAULT_ATTRACTION = new AttractionBuilder().build();
    public static final String DEFAULT_START_TIME = "1300";
    public static final String DEFAULT_END_TIME = "1500";

    private Attraction attraction;
    private ItineraryTime startTime;
    private ItineraryTime endTime;


    /**
     * Creates a {@code ItineraryAttractionBuilder} with the default details.
     */
    public ItineraryAttractionBuilder() {
        attraction = DEFAULT_ATTRACTION;
        startTime = new ItineraryTime(DEFAULT_START_TIME);
        endTime = new ItineraryTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the ItineraryAttractionBuilder with the data of {@code iaToCopy}.
     */
    public ItineraryAttractionBuilder(ItineraryAttraction iaToCopy) {
        attraction = iaToCopy.getAttraction();
        startTime = iaToCopy.getStartTime();
        endTime = iaToCopy.getEndTime();
    }

    /**
     * Sets the {@code Attraction} of the {@code ItineraryAttraction} that we are building.
     */
    public ItineraryAttractionBuilder withAttraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code ItineraryAttraction} that we are building.
     */
    public ItineraryAttractionBuilder withStartTime(String startTime) {
        this.startTime = new ItineraryTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code ItineraryAttraction} that we are building.
     */
    public ItineraryAttractionBuilder withEndTime(String endTime) {
        this.endTime = new ItineraryTime(endTime);
        return this;
    }

    /**
     * Initializes a new itinerary attraction.
     *
     * @return a new Attraction.
     */
    public ItineraryAttraction build() {
        return new ItineraryAttraction(attraction, startTime, endTime);
    }

}
