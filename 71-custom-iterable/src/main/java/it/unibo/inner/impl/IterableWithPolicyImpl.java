package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    
    private final T[] elements;
    private Predicate<T> filter;
    
    public IterableWithPolicyImpl(T[] elements) {
        this (elements, new Predicate<>() {
            public boolean test(T elem) {
                return true;
            }    
        });
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> filter) {
        this.elements = Arrays.copyOf(elements, elements.length);
        this.filter = filter;
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public Iterator<T> iterator() {
        return this.new ArrayIterator();
    } 

    public class ArrayIterator implements Iterator<T> {
        
        private int actualIndex;

        public ArrayIterator () {
            this.actualIndex = 0;
        }

        public boolean hasNext() {
            if (this.actualIndex >= elements.length) {
                return false;
            }
            while (this.actualIndex < elements.length) {
                if (IterableWithPolicyImpl.this.filter.test (elements[actualIndex])) {
                    return true;
                }
                this.actualIndex++;
            }
            return false;
        }

        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            } else {
                var element = elements[this.actualIndex];
                this.actualIndex++;
                return element;
            }
        }
    }
}
