package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Jackson-friendly version of {@link ItineraryAttraction}.
 */
class JsonAdaptedItineraryAttraction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary attraction's %s field is missing!";

    private final JsonAdaptedAttraction attraction;
    // todo add more fields specific to itinerary attraction

    /**
     * Constructs a {@code JsonAdaptedItineraryAttraction} with the given itinerary attraction details.
     */
    @JsonCreator
    public JsonAdaptedItineraryAttraction(@JsonProperty("attraction") JsonAdaptedAttraction attraction) {
        this.attraction = attraction;
        // todo add more fields specific to itinerary attraction
    }

    /**
     * Converts a given {@code ItineraryAttraction} into this class for Jackson use.
     */
    public JsonAdaptedItineraryAttraction(ItineraryAttraction source) {
        attraction = new JsonAdaptedAttraction(source.getAttraction());
        // todo add more fields specific to itinerary attraction
    }

    /**
     * Converts this Jackson-friendly adapted itinerary attraction object into the model's {@code ItineraryAttraction}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted itinerary attraction.
     */
    public ItineraryAttraction toModelType() throws IllegalValueException {
        // todo add more fields specific to itinerary attraction

        // Attraction is not optional
        if (attraction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Attraction.class.getSimpleName()));
        } else {
            // todo make these not null next time
            return new ItineraryAttraction(attraction.toModelType(), null, null);
        }
    }
}
