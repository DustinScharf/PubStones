package org.example.pubstones.util;

/**
 * This class represents a history of objects.
 * The history is a list, that can append instances of DataType.
 * <p>
 * The class also contains a pointer pointing to the current (i.e. "present") instance of DataType.
 * This pointer can be moved forward or backward in order to go through history.
 *
 * @param <DataType> DataType of the instances for this history
 */
public class HistoryList<DataType> {
    private class Node {
        private Node previous;
        private DataType data;
        private Node next;

        public Node(Node previous, DataType data, Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    private Node head;

    private Node current;

    /**
     * Appends an object to the current point of history.
     * <p>
     * The whole history after this new object will be dropped (if there is anything).
     *
     * @param data data to append
     */
    public void append(DataType data) {
        Node appendNode = new Node(this.current, data, null);

        if (this.head == null) {
            this.head = appendNode;
        } else {
            this.current.next = appendNode;
        }

        this.current = appendNode;
    }

    /**
     * Returns the current (i.e. "present") element
     *
     * @return current (i.e. "present") data
     */
    public DataType getCurrent() {
        if (this.current == null) {
            throw new NullPointerException("No current element because HistoryList is empty");
        }

        return this.current.data;
    }

    /**
     * Checks if there is a next element in history
     *
     * @return true if there is a next element in history, false otherwise
     */
    public boolean hasNext() {
        return this.current.next != null;
    }

    /**
     * Checks if there is a previous element in history
     *
     * @return true if there is a previous element in history, false otherwise
     */
    public boolean hasPrevious() {
        return this.current.previous != null;
    }

    /**
     * Returns the next element in history if there is one
     *
     * @return the next element in history if there is one
     * @throws NullPointerException if there is no next element in history
     */
    public DataType getNext() throws NullPointerException {
        if (!this.hasNext()) {
            throw new NullPointerException("Next element to current is null in HistoryList");
        }

        return this.current.next.data;
    }

    /**
     * Returns the previous element in history if there is one
     *
     * @return the previous element in history if there is one
     * @throws NullPointerException if there is no previous element in history
     */
    public DataType getPrevious() throws NullPointerException {
        if (!this.hasPrevious()) {
            throw new NullPointerException("Previous element to current is null in HistoryList");
        }

        return this.current.previous.data;
    }

    /**
     * Moves the current pointer to the next element in history if there is one and returns it
     *
     * @return the next element in history if there is one
     * @throws NullPointerException if there is no next element in history
     */
    public DataType goNext() throws NullPointerException {
        if (!this.hasNext()) {
            throw new NullPointerException("Next element to current is null in HistoryList");
        }

        this.current = this.current.next;
        return this.current.data;
    }

    /**
     * Moves the current pointer to the previous element in history if there is one and returns it
     *
     * @return the previous element in history if there is one
     * @throws NullPointerException if there is no previous element in history
     */
    public DataType goPrevious() throws NullPointerException {
        if (!this.hasPrevious()) {
            throw new NullPointerException("Previous element to current is null in HistoryList");
        }

        this.current = this.current.previous;
        return this.current.data;
    }
}
