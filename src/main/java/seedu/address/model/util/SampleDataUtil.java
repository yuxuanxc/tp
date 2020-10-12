package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyTrackPad;
import seedu.address.model.TrackPad;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Description;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code TrackPad} with sample data.
 */
public class SampleDataUtil {
    public static Attraction[] getSampleAttractions() {
        return new Attraction[] {
            new Attraction(new Name("Jurong Bird Park"), new Phone("94351253"), new Email("birdpark@example.com"),
                    new Address("2 Jurong Hill"),
                    new Description("The park offers a haven for close to 3500 birds across 400 species."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.5"), getTagSet("animals")),
            new Attraction(new Name("Night Safari"), new Phone("98765432"), new Email("nightsafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("The world's first nocturnal zoo."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.2"), getTagSet("animals", "night")),
            new Attraction(new Name("River Safari"), new Phone("93210283"), new Email("riversafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("A river-themed zoo and aquarium in Singapore."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.4"), getTagSet("animals", "panda")),
            new Attraction(new Name("Universal Studios Singapore"), new Phone("65482651"), new Email("uss@example.com"),
                    new Address("8 Sentosa Gateway, 098269"),
                    new Description("A world-renowned theme park with thrilling rides."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1400-2100"), new PriceRange("HIGH"),
                    new Rating("4.6"), getTagSet("activities")),
            new Attraction(new Name("Snow City"), new Phone("65602306"), new Email("snowcity@example.com"),
                    new Address("21 Jurong Town Hall Rd, 609433"),
                    new Description("A winter wonderland amidst the tropical Singapore climate."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1700"), new PriceRange("LOW"),
                    new Rating("3.6"), getTagSet("activities")),
            new Attraction(new Name("Trick Eye Museum"), new Phone("67952371"), new Email("trickeye@example.com"),
                    new Address("26 Sentosa Gateway, 098138"),
                    new Description("Unique museum with a large array of 3-dimensional artwork for interactive optical illusions."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("LOW"),
                    new Rating("4.2"), getTagSet("activities")),
            new Attraction(new Name("Singapore Zoo"), new Phone("62693411"), new Email("riversafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("Singapore Zoo's world-famous \"Open Concept” offers the opportunity to experience and be inspired by the wonders of nature."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("0830-1800"), new PriceRange("MEDIUM"),
                    new Rating("4.6"), getTagSet("animals", "tiger")),
            new Attraction(new Name("Botanic Gardens"), new Phone("92492021"), new Email("botanicgardens@example.com"),
                    new Address("1 Cluny Rd"),
                    new Description("The only tropical garden to be honoured as a UNESCO World Heritage Site."),
                    new Location("Singapore, Singapore"),
                    new OpeningHours("1000-1800"), new PriceRange("LOW"),
                    new Rating("4.7"), getTagSet("nature"))
        };
    }

    public static ReadOnlyTrackPad getSampleTrackPad() {
        TrackPad sampleTp = new TrackPad();
        for (Attraction sampleAttraction : getSampleAttractions()) {
            sampleTp.addAttraction(sampleAttraction);
        }
        return sampleTp;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
