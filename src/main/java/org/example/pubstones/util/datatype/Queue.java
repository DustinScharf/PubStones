package org.example.pubstones.util.datatype;

import java.util.LinkedList;

public class Queue<T> {
    
    private LinkedList<T> list;
    
    /**
     * Creates a new queue object
     */
    public Queue() {
        this.list = new LinkedList<T>();
    }
    
    /**
     * Enqueues the given object 
     * @param object
     */
    public void enqueue(T object) {
        this.list.add(object);
    }
    
    /**
     * Dequeues the first element
     * @return
     */
    public T dequeue() {
        return this.list.removeFirst();
    }
    
    /**
     * This queue's size
     * @return
     */
    public int size() {
        return this.list.size();
    }
    
    /**
     * Checks whether the queue is empty
     * @return
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
    /**
     * Removes the given object from the queue
     * @param object
     */
    public void remove(T object) {
        this.list.remove(object);
    }
    
    public T first() {
        return this.list.getFirst();
    }
    
}
