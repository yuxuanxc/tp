package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ZOO;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ACTIVITY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditAttractionDescriptor;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class EditAttractionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAttractionDescriptor descriptorWithSameValues = new EditAttractionDescriptor(DESC_ZOO);
        assertTrue(DESC_ZOO.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ZOO.equals(DESC_ZOO));

        // null -> returns false
        assertFalse(DESC_ZOO.equals(null));

        // different types -> returns false
        assertFalse(DESC_ZOO.equals(5));

        // different values -> returns false
        assertFalse(DESC_ZOO.equals(DESC_MBS));

        // different name -> returns false
        EditAttractionDescriptor editedZoo = new EditAttractionDescriptorBuilder(DESC_ZOO).withName(VALID_NAME_MBS).build();
        assertFalse(DESC_ZOO.equals(editedZoo));

        // different phone -> returns false
        editedZoo = new EditAttractionDescriptorBuilder(DESC_ZOO).withPhone(VALID_PHONE_MBS).build();
        assertFalse(DESC_ZOO.equals(editedZoo));

        // different email -> returns false
        editedZoo = new EditAttractionDescriptorBuilder(DESC_ZOO).withEmail(VALID_EMAIL_MBS).build();
        assertFalse(DESC_ZOO.equals(editedZoo));

        // different address -> returns false
        editedZoo = new EditAttractionDescriptorBuilder(DESC_ZOO).withAddress(VALID_ADDRESS_MBS).build();
        assertFalse(DESC_ZOO.equals(editedZoo));

        // different tags -> returns false
        editedZoo = new EditAttractionDescriptorBuilder(DESC_ZOO).withTags(VALID_TAG_ACTIVITY).build();
        assertFalse(DESC_ZOO.equals(editedZoo));
    }
}
