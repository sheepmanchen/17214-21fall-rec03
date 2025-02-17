package edu.cmu.cs.cs214.rec02;

import org.junit.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: Write detailed unit tests for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 * implementation. Refine your tests to achieve 100% line coverage and use them
 * to determine and correct the bugs!
 *
 * @author Alex Lockwood
 */
public class IntQueueTest {

    private IntQueue mQueue;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
//        mQueue = new LinkedIntQueue();
         mQueue = new ArrayIntQueue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testNotEmptySize() {
        mQueue.enqueue(1);
        assertEquals(1, mQueue.size());
    }

    @Test
    public void testClear() {
        mQueue.enqueue(1);
        mQueue.clear();
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testEmptySize() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNotEmptyQueue() {
        mQueue.enqueue(1);
        assertEquals(Integer.valueOf(1), mQueue.peek());
    }

    @Test
    public void testDequeEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println(input);
                mQueue.enqueue(input);
            }
            // Used boxed type to pacify assertEquals overload resolution
            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
