package seedu.address.model.itinerary;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Itinerary}'s {@code Name}, ***@code Description*** (not working yet),
 *
 * {@code StartTime}, {@code EndTime}, {@code itineraryAttractions}
 * matches any of the keywords given.
 */
public class ItineraryContainsKeywordsPredicate implements Predicate<Itinerary> {
    private final List<String> keywords;

    public ItineraryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Itinerary itinerary) {
        if (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(itinerary.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(itinerary.getDescription().value, keyword)
                        // todo change if itinerary date becomes a wrapper class instead of just Localdate
                        || StringUtil.containsWordIgnoreCase(itinerary.getStartDate().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(itinerary.getEndDate().toString(), keyword)
                )
        ) {
            return true;
        } else {
            // todo Also checks through itinerary attractions, remove if not needed (not tested yet)
            for (ItineraryAttraction ia : itinerary.getItineraryAttractions()) {
                if (new ItineraryAttractionContainsKeywordsPredicate(keywords).test(ia)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItineraryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
