package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    // todo make the syntax look nicer and more coherent

    // Prefix for Attraction
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_OPENING_HOURS = new Prefix("op/");
    public static final Prefix PREFIX_PRICE_RANGE = new Prefix("pr/");
    public static final Prefix PREFIX_RATING = new Prefix("r/");
    public static final Prefix PREFIX_VISITED = new Prefix("v/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STARTDATE = new Prefix("sd/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("ed/");

    // Prefix for ItineraryAttraction related
    public static final Prefix PREFIX_ATTRACTION = new Prefix("attr/");
    public static final Prefix PREFIX_START_TIME = new Prefix("st/");
    public static final Prefix PREFIX_END_TIME = new Prefix("et/");
    public static final Prefix PREFIX_DAY_VISITING = new Prefix("day/");

    // Prefix for Itinerary related


}
