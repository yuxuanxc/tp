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
import seedu.address.model.attraction.Phone;
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
                    new Location("Singapore, Singapore"), getTagSet("animals")),
            new Attraction(new Name("Night Safari"), new Phone("98765432"), new Email("nightsafari@example.com"),
                new Address("80 Mandai Lake Rd"),
                    new Description("The world's first nocturnal zoo."),
                    new Location("Singapore, Singapore"),
                    getTagSet("animals", "night")),
            new Attraction(new Name("River Safari"), new Phone("93210283"), new Email("riversafari@example.com"),
                    new Address("80 Mandai Lake Rd"),
                    new Description("A river-themed zoo and aquarium in Singapore."),
                    new Location("Singapore, Singapore"), getTagSet("animals", "panda")),
            new Attraction(new Name("Orchard Road"), new Phone("91031282"), new Email("orchardroad@example.com"),
                    new Address("Orchard Road"),
                    new Description("Orchard Road is Singapore’s retail heart, with discount outlets, " +
                    "department stores and upscale boutiques, alongside luxury hotels."),
                    new Location("Singapore, Singapore"), getTagSet("shopping")),
            new Attraction(new Name("Botanic Gardens"), new Phone("92492021"), new Email("botanicgardens@example.com"),
                    new Address("1 Cluny Rd"),
                    new Description("The only tropical garden to be honoured as a UNESCO World Heritage Site."),
                    new Location("Singapore, Singapore"), getTagSet("nature")),
            new Attraction(new Name("Jurong Lake"), new Phone("92624417"), new Email("juronglake@example.com"),
                    new Address("Jurong"),
                    new Description("Sprawling park around a lake & swamp forest with a boardwalk, " +
                            "playgrounds, a dog run & water sports."),
                    new Location("Singapore, Singapore"), getTagSet("nature"))
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
