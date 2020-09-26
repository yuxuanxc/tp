package seedu.address.testutil;

import seedu.address.model.TrackPad;
import seedu.address.model.attraction.Attraction;

/**
 * A utility class to help with building TrackPad objects.
 * Example usage: <br>
 *     {@code TrackPad tp = new TrackPadBuilder().withAttraction("USS", "MBS").build();}
 */
public class TrackPadBuilder {

    private TrackPad trackPad;

    public TrackPadBuilder() {
        trackPad = new TrackPad();
    }

    public TrackPadBuilder(TrackPad trackPad) {
        this.trackPad = trackPad;
    }

    /**
     * Adds a new {@code Attraction} to the {@code TrackPad} that we are building.
     */
    public TrackPadBuilder withAttraction(Attraction attraction) {
        trackPad.addAttraction(attraction);
        return this;
    }

    public TrackPad build() {
        return trackPad;
    }
}
