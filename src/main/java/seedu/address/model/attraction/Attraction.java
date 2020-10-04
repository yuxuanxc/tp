package seedu.address.model.attraction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Attraction in TrackPad.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attraction {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Description description;
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Attraction(Name name, Phone phone, Email email, Address address, Description description,
                      Location location, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, description, location, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
        this.location = location;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Description getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both attractions of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two attractions.
     */
    public boolean isSameAttraction(Attraction otherAttraction) {
        if (otherAttraction == this) {
            return true;
        }

        return otherAttraction != null
                && otherAttraction.getName().equals(getName())
                && (otherAttraction.getPhone().equals(getPhone()) || otherAttraction.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two attractions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Attraction)) {
            return false;
        }

        Attraction otherAttraction = (Attraction) other;
        return otherAttraction.getName().equals(getName())
                && otherAttraction.getPhone().equals(getPhone())
                && otherAttraction.getEmail().equals(getEmail())
                && otherAttraction.getAddress().equals(getAddress())
                && otherAttraction.getDescription().equals(getDescription())
                && otherAttraction.getLocation().equals(getLocation())
                && otherAttraction.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, description, location, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Description: ")
                .append(getDescription())
                .append(" Location: ")
                .append(getLocation())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
