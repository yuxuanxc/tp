package seedu.address.testutil;

import java.time.LocalDate;


import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;

/**
 * A utility class to help with building Itinerary objects.
 */
public class ItineraryBuilder {
    public static final String DEFAULT_NAME = "Singapore Trip";
    public static final String DEFAULT_DESCRIPTION = "Explore the wonders of Singapore!";
    public static final String DEFAULT_START_DATE = "12-12-2020";
    public static final String DEFAULT_END_DATE = "12-15-2020";
    public static final String DEFAULT_BUDGET = "100";

    private Name name;
    private Description description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Budget budget;

    /**
     * Creates a {@code ItineraryBuilder} with the default details.
     */
    public ItineraryBuilder() throws ParseException {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        try {
            //todo remove when wrapper class for date is up
            startDate = ParserUtil.parseDate(DEFAULT_START_DATE);
            endDate = ParserUtil.parseDate(DEFAULT_END_DATE);
        } catch (ParseException e) {
            throw e;
        }
        budget = new Budget(DEFAULT_BUDGET);
    }

    /**
     * Sets the {@code Name} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Itinerary} that we are building to empty string.
     */
    public ItineraryBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withBudget(String budget) {
        this.budget = new Budget(budget);
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Itinerary} that we are building to empty string.
     */
    public ItineraryBuilder withBudget() {
        this.budget = new Budget();
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withStartDate(String startDate) {
        //todo when date wrapper is up
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withEndDate(String endDate) {
        //todo when date wrapper is up
        return this;
    }
}
