package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TrackPad;
import seedu.address.model.ReadOnlyTrackPad;
import seedu.address.model.attraction.Attraction;

/**
 * An Immutable TrackPad that is serializable to JSON format.
 */
@JsonRootName(value = "trackpad")
class JsonSerializableTrackPad {

    public static final String MESSAGE_DUPLICATE_ATTRACTION = "Attractions list contains duplicate attraction(s).";

    private final List<JsonAdaptedAttraction> attractions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackPad} with the given attractions.
     */
    @JsonCreator
    public JsonSerializableTrackPad(@JsonProperty("attractions") List<JsonAdaptedAttraction> attractions) {
        this.attractions.addAll(attractions);
    }

    /**
     * Converts a given {@code ReadOnlyTrackPad} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackPad}.
     */
    public JsonSerializableTrackPad(ReadOnlyTrackPad source) {
        attractions.addAll(source.getAttractionList().stream().map(JsonAdaptedAttraction::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackpad into the model's {@code TrackPad} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackPad toModelType() throws IllegalValueException {
        TrackPad trackpad = new TrackPad();
        for (JsonAdaptedAttraction jsonAdaptedAttraction : attractions) {
            Attraction attraction = jsonAdaptedAttraction.toModelType();
            if (trackpad.hasAttraction(attraction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ATTRACTION);
            }
            trackpad.addAttraction(attraction);
        }
        return trackpad;
    }

}
