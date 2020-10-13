package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttractionBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AttractionBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new AttractionBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new AttractionBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new AttractionBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertTrue(predicate.test(new AttractionBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Mandai"));
        assertTrue(predicate.test(new AttractionBuilder().withAddress("Mandai Road").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Road", "Mandai"));
        assertTrue(predicate.test(new AttractionBuilder().withAddress("Mandai Road").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Mandai", "Way"));
        assertTrue(predicate.test(new AttractionBuilder().withAddress("Mandai Road").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("ManDaI", "WaY"));
        assertTrue(predicate.test(new AttractionBuilder().withName("mAnDaI wAy").build()));
    }

    // todo find out why this new same test case won't work for description
    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new AttractionBuilder().withDescription("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Road", "Bob"));
        assertTrue(predicate.test(new AttractionBuilder().withDescription("Road Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Way"));
        assertTrue(predicate.test(new AttractionBuilder().withDescription("Alice Way").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "WaY"));
        assertTrue(predicate.test(new AttractionBuilder().withDescription("Alice WaY").build()));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("A@Bx"));
        assertTrue(predicate.test(new AttractionBuilder().withEmail("A@Bx").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("A", "Bob", "A@Bob"));
        assertTrue(predicate.test(new AttractionBuilder().withEmail("A@Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Way", "Alice@Way"));
        assertTrue(predicate.test(new AttractionBuilder().withEmail("Alice@Way").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "WaY", "Alice@WaY"));
        assertTrue(predicate.test(new AttractionBuilder().withEmail("Alice@WaY").build()));

        // Whole email should match
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "@Way", "Alice@Wa"));
        assertFalse(predicate.test(new AttractionBuilder().withEmail("AliCe@WAy").build()));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Singapore"));
        assertTrue(predicate.test(new AttractionBuilder().withLocation("Singapore, Singapore").build()));

        // Find command matches whole words, it would not be able to find France if it is "France,"
        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Paris", "France"));
        assertTrue(predicate.test(new AttractionBuilder().withLocation("France , Paris").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("USA", "Netherlands"));
        assertTrue(predicate.test(new AttractionBuilder().withLocation("Netherlands , Amsterdam").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("NethErlands", "AmStErDaM"));
        assertTrue(predicate.test(new AttractionBuilder().withLocation("NetheRlAnds, AmstErdam").build()));
    }

    @Test
    public void test_openingHoursContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("1200-1300"));
        assertTrue(predicate.test(new AttractionBuilder().withOpeningHours("1200-1300").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("1200-1300", "-1300"));
        assertTrue(predicate.test(new AttractionBuilder().withOpeningHours("1200-1300").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("123456789"));
        assertTrue(predicate.test(new AttractionBuilder().withPhone("123456789").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "6789"));
        assertTrue(predicate.test(new AttractionBuilder().withPhone("12345").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "654987"));
        assertTrue(predicate.test(new AttractionBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_priceRangeContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("LOW"));
        assertTrue(predicate.test(new AttractionBuilder().withPriceRange("LOW").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("MED", "MEDIUM"));
        assertTrue(predicate.test(new AttractionBuilder().withPriceRange("MEDIUM").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("HIGH", "MED"));
        assertTrue(predicate.test(new AttractionBuilder().withPriceRange("HIGH").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("loW", "MED"));
        assertTrue(predicate.test(new AttractionBuilder().withPriceRange("LOW").build()));
    }

    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("1.1"));
        assertTrue(predicate.test(new AttractionBuilder().withRating("1.1").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("2.1", "1.1"));
        assertTrue(predicate.test(new AttractionBuilder().withRating("2.1").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("1.1", "1.6"));
        assertTrue(predicate.test(new AttractionBuilder().withRating("1.1").build()));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("warm"));
        String[] tags = {"warm", "cold"};
        assertTrue(predicate.test(new AttractionBuilder().withTags(tags).build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("warm", "cold"));
        assertTrue(predicate.test(new AttractionBuilder().withTags(tags).build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("warm", "sexy"));
        assertTrue(predicate.test(new AttractionBuilder().withTags("sexy").build()));

        // Mixed-case keywords does not work for tags as tags are case sensitive
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("WaRM", "CoLD"));
        assertFalse(predicate.test(new AttractionBuilder().withTags(tags).build()));
    }


    @Test
    public void test_fieldDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withName("Alice").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withAddress("Alice").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withDescription("Alice").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withEmail("ZOO@GMAIL.COM").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withLocation("Alice").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withOpeningHours("1200-1300").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withPhone("123456").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withPriceRange("MEDIUM").build()));

        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AttractionBuilder().withRating("1.1").build()));


        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new AttractionBuilder().withName("Alice Bob").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Mandai", "Drive"));
        assertFalse(predicate.test(new AttractionBuilder().withAddress("Marina Way").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("This", "is", "The"));
        assertFalse(predicate.test(new AttractionBuilder().withDescription("Big theme park").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("zoo@gmail.com"));
        assertFalse(predicate.test(new AttractionBuilder().withEmail("USS@gmail.com").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Malaysia"));
        assertFalse(predicate.test(new AttractionBuilder().withLocation("Singapore").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("1200-1300"));
        assertFalse(predicate.test(new AttractionBuilder().withOpeningHours("1000-1700").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("987654321"));
        assertFalse(predicate.test(new AttractionBuilder().withPhone("123456789").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("HIGH"));
        assertFalse(predicate.test(new AttractionBuilder().withPriceRange("MEDIUM").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("1"));
        assertFalse(predicate.test(new AttractionBuilder().withRating("1.2").build()));

        predicate = new NameContainsKeywordsPredicate(Arrays.asList("hot"));
        String[] tags = {"warm", "cold"};
        assertFalse(predicate.test(new AttractionBuilder().withTags(tags).build()));

    }
}
