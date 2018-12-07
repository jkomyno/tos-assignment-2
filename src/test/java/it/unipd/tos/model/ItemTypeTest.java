package it.unipd.tos.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTypeTest {
    @Test
    public void testItemTypeEnumValues() {
        assertEquals("PIZZE", ItemType.valueOf(ItemType.PIZZE.toString()).name());
        assertEquals("PRIMI", ItemType.valueOf(ItemType.PRIMI.toString()).name());
    }
}
