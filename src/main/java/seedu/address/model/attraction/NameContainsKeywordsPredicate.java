package seedu.address.model.attraction;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Attraction}'s {@code Name, @code Address, ***@code Description*** (not working yet),
 *
 * @code Email, @code Location, @code OpeningHours, @code Phone, @code PriceRange, @code Rating}
 * matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Attraction> {
    // todo refactor the name of this class since it is no longer only looking for the name.
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Attraction attraction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(attraction.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getAddress().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getDescription().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getEmail().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getLocation().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getOpeningHours().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getPhone().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getPriceRange().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getRating().value, keyword)
                        || attraction.getTags().stream().anyMatch(tag -> tag.tagName.equals(keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
