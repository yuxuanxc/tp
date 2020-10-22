package seedu.address.testutil;

import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

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
    public ItineraryAttractionBuilder withStartTime(ItineraryTime startTime){
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withPhone() {
        this.phone = new Phone();
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withAddress() {
        this.address = new Address();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withOpeningHours(String openingHours) {
        this.openingHours = new OpeningHours(openingHours);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withOpeningHours() {
        this.openingHours = new OpeningHours();
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withPriceRange(String priceRange) {
        this.priceRange = new PriceRange(priceRange);
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withPriceRange() {
        this.priceRange = new PriceRange();
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withRating() {
        this.rating = new Rating();
        return this;
    }
    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withVisited(String visited) {
        this.visited = new Visited(visited);
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withVisited() {
        this.visited = new Visited();
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Initializes a new attraction.
     *
     * @return a new Attraction.
     */
    public Attraction build() {
        return new Attraction(name, phone, email, address, description, location,
                openingHours, priceRange, rating, visited, tags);
    }

}
