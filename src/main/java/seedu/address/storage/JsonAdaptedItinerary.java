package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Name;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedItineraryAttraction> itineraryAttractions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedItinerary} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("name") String name,
                                @JsonProperty("itineraryAttractions")
                                        List<JsonAdaptedItineraryAttraction> itineraryAttractions) {
        this.name = name;
        if (itineraryAttractions != null) {
            this.itineraryAttractions.addAll(itineraryAttractions);
        }
    }

    /**
     * Converts a given {@code Itinerary} into this class for Jackson use.
     */
    public JsonAdaptedItinerary(Itinerary source) {
        name = source.getName().toString();
        itineraryAttractions.addAll(source.getItineraryAttractions().stream()
                .map(JsonAdaptedItineraryAttraction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted itinerary object into the model's {@code Itinerary} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted itinerary.
     */
    public Itinerary toModelType() throws IllegalValueException {
        final Name modelName;
        final List<ItineraryAttraction> modelItineraryAttractions = new ArrayList<>();

        for (JsonAdaptedItineraryAttraction itineraryAttraction : itineraryAttractions) {
            modelItineraryAttractions.add(itineraryAttraction.toModelType());
        }

        // Name is not optional
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        } else if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        } else {
            modelName = new Name(name);
        }

        return new Itinerary(modelName, modelItineraryAttractions);
    }

}
