package org.example.pubstones.util.datatype;

import java.util.LinkedList;

public class Queue<T> {
    
    private LinkedList<T> list;
    
    public Queue() {
        this.list = new LinkedList<T>();
    }
    
    public void enqueue(T object) {
        this.list.add(object);
    }
    
    public T dequeue() {
        return this.list.removeFirst();
    }
    
    public int size() {
        return this.list.size();
    }
    
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
}
