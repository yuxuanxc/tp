package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Description;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Attraction}.
 */
class JsonAdaptedAttraction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attraction's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String description;
    private final String location;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAttraction} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAttraction(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("description") String description,
            @JsonProperty("location") String location, @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
        this.location = location;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Attraction} into this class for Jackson use.
     */
    public JsonAdaptedAttraction(Attraction source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        description = source.getDescription().value;
        location = source.getLocation().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Attraction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attraction.
     */
    public Attraction toModelType() throws IllegalValueException {
        final Name modelName;
        final Phone modelPhone;
        final Email modelEmail;
        final Address modelAddress;
        final Description modelDescription;
        final Location modelLocation;
        final List<Tag> attractionTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            attractionTags.add(tag.toModelType());
        }

        // Name is not optional
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        } else if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        } else {
            modelName = new Name(name);
        }

        // Phone is optional
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        } else if (phone.equals("")) {
            modelPhone = new Phone();
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }

        // Email is optional
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        } else if (email.equals("")) {
            modelEmail = new Email();
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }

        // Address is optional
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        } else if (address.equals("")) {
            modelAddress = new Address();
        } else if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        } else {
            modelAddress = new Address(address);
        }

        // Description is optional
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        } else if (description.equals("")) {
            modelDescription = new Description();
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        // Location is not optional
        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        } else {
            modelLocation = new Location(location);
        }

        final Set<Tag> modelTags = new HashSet<>(attractionTags);
        return new Attraction(modelName, modelPhone, modelEmail, modelAddress, modelDescription,
                modelLocation, modelTags);
    }
}
