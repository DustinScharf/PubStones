package org.example.pubstones.util;

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

    public void append(DataType data) {
        Node appendNode = new Node(this.current, data, null);

        if (this.head == null) {
            this.head = appendNode;
        } else {
            this.current.next = appendNode;
        }

        this.current = appendNode;
    }

    public DataType getCurrent() {
        return this.current.data;
    }

    public boolean hasNext() {
        return this.current.next != null;
    }

    public boolean hasPrevious() {
        return this.current.previous != null;
    }

    public DataType getNext() throws NullPointerException {
        if (!this.hasNext()) {
            throw new NullPointerException("Next element to current is null in HistoryList");
        }

        return this.current.next.data;
    }

    public DataType getPrevious() throws NullPointerException {
        if (!this.hasPrevious()) {
            throw new NullPointerException("Previous element to current is null in HistoryList");
        }

        return this.current.previous.data;
    }

    public DataType goNext() throws NullPointerException {
        if (!this.hasNext()) {
            throw new NullPointerException("Next element to current is null in HistoryList");
        }

        this.current = this.current.next;
        return this.current.data;
    }

    public DataType goPrevious() throws NullPointerException {
        if (!this.hasPrevious()) {
            throw new NullPointerException("Previous element to current is null in HistoryList");
        }

        this.current = this.current.previous;
        return this.current.data;
    }
}
