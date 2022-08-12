package shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShopTest {
    private static final String VALID_SHELF_NAME = "Shelves1";
    private static final String ANOTHER_VALID_SHELF_NAME = "Shelves2";
    private static final String INVALID_SHELF_NAME = "Invalid Shelf";
    private static final String TEST_GOODS_NAME = "Soda can";
    private static final String TEST_GOODS_CODE = "123456";
    private static final String ANOTHER_TEST_GOODS_NAME = "Carton of Milk";
    private static final String ANOTHER_TEST_GOODS_CODE = "101010";

    private static final int NUMBER_OF_SHELVES = 12;

    private Shop shop;
    private Goods testGoods;
    private Goods anotherTestGoods;

    @Before
    public void setUp() {
        shop = new Shop();
        testGoods = new Goods(TEST_GOODS_NAME, TEST_GOODS_CODE);
        anotherTestGoods = new Goods(ANOTHER_TEST_GOODS_NAME, ANOTHER_TEST_GOODS_CODE);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void shelvesAreUnmodifiable() {
        shop.getShelves().put(INVALID_SHELF_NAME, testGoods);
    }

    @Test
    public void constructorCreatesEmptyShelves() {
        Map<String, Goods> shelves = shop.getShelves();
        assertEquals(NUMBER_OF_SHELVES, shelves.size());

        long numberOfNonNullShelves = shelves.values().stream()
                .filter(Objects::nonNull)
                .count();

        assertEquals(0, numberOfNonNullShelves);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingNotPresentGoods_ToInvalidShelf_Throws() throws OperationNotSupportedException {
        shop.addGoods(INVALID_SHELF_NAME, testGoods);
    }

    @Test
    public void addingNotPresentGoods_ToValidShelf_PutsItOnTheShelf() throws OperationNotSupportedException {
        shop.addGoods(VALID_SHELF_NAME, testGoods);
        assertEquals(shop.getShelves().get(VALID_SHELF_NAME), testGoods);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addingAlreadyPresentGoods_ToAnotherShelfThrows() throws OperationNotSupportedException {
        shop.addGoods(VALID_SHELF_NAME, testGoods);
        shop.addGoods(ANOTHER_VALID_SHELF_NAME, testGoods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingGoods_ToAlreadyTakenShelf_Throws() throws OperationNotSupportedException {
        shop.addGoods(VALID_SHELF_NAME, testGoods);
        shop.addGoods(VALID_SHELF_NAME, anotherTestGoods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingGoods_FromInvalidShelf_Throws() {
        shop.removeGoods(INVALID_SHELF_NAME, testGoods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNotPresentGoods_FromValidShelf_Throws() throws OperationNotSupportedException {
        shop.addGoods(VALID_SHELF_NAME, testGoods);
        shop.removeGoods(ANOTHER_VALID_SHELF_NAME, testGoods);
    }

    @Test
    public void removingGoods_FromValidShelf_SetsItToNull() throws OperationNotSupportedException {
        shop.addGoods(VALID_SHELF_NAME, testGoods);
        shop.removeGoods(VALID_SHELF_NAME, testGoods);
        assertNull(shop.getShelves().get(VALID_SHELF_NAME));
    }
}