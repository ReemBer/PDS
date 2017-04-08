package Ship;

import junit.framework.TestCase;

/**
 * Created by Taresevich Vladislav on 08.04.2017.
 * Unit test for class Ship
 */
public class ShipTest extends TestCase
{
    private final String TEST_NAME = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ1234567890 ";
    private final Cargo TEST_CARGO = Cargo.GAS;
    private final int TEST_COUNT   = (int)(2e9);
    private final boolean TEST_REQUEST = true;

    private Ship testShip1;
    private Ship testShip2;
    private Ship testShip3;
    private Ship testShip4;

    public void setUp()
    {
        testShip1 = new Ship();
        testShip2 = new Ship(TEST_NAME);
        testShip3 = new Ship(TEST_NAME, TEST_CARGO, TEST_COUNT);
        testShip4 = new Ship(TEST_NAME, TEST_CARGO, TEST_COUNT, TEST_REQUEST);
    }

    public void tearDown()
    {
        testShip1 = testShip2 = testShip3 = testShip4 = null;
    }

    /**
     * This method used to Unit-testing all getters in class Ship
     */
    public void testGetters()
    {
        assertNotNull(testShip1.getName());

        assertEquals(testShip2.getName(), TEST_NAME);
        assertEquals(testShip3.getCargo(), TEST_CARGO);
        assertEquals(testShip3.getCount(), TEST_COUNT);

        assertTrue(testShip4.isLoadRequest());
    }

    /**
     * This method used to Unit-testing all getters in class Ship
     */
    public void testSetters()
    {
        testShip1.setName(TEST_NAME);
        assertEquals(testShip1.getName(), TEST_NAME);

        testShip1.setCargo(TEST_CARGO);
        assertEquals(testShip1.getCargo(), TEST_CARGO);

        testShip1.setCount(TEST_COUNT);
        assertEquals(testShip1.getCount(), TEST_COUNT);

        testShip1.setLoadRequest(TEST_REQUEST);
        assertEquals(testShip1.isLoadRequest(), TEST_REQUEST);
    }
}
