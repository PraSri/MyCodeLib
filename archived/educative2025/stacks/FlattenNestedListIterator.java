package stacks;

import java.util.*;

public class FlattenNestedListIterator {

    public static class NestedIterator {
        private Stack<NestedInteger> stack;

        // NestedIterator constructor initializes the stack using the given nestedList
        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new Stack<>();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                this.stack.push(nestedList.get(i));
            }
        }

        // hasNext() will return true if there are still some integers in the stack (that has nested_list elements),
        // and false otherwise.
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger top = stack.peek();
                if (top.isInteger()) {
                    return true;
                }
                List<NestedInteger> topList = stack.pop().getList();
                for (int i = topList.size() - 1; i >= 0; i--) {
                    this.stack.push(topList.get(i));
                }
            }
            return false;
        }

        public int next() {
            if (hasNext()) {
                return this.stack.pop().getInteger();
            }
            return 0;
        }

    }

    public static class NestedInteger {
        private Integer integer;
        private List<NestedInteger> nList;

        // Constructor initializes a single integer if a value has been passed
        // else it initializes an empty list
        public NestedInteger() {
            this.nList = new ArrayList<>();
            this.integer = null;
        }

        public NestedInteger(int integer) {
            this.integer = integer;
            this.nList = null;
        }

        // If this NestedIntegers holds a single integer rather
        // than a nested list, returns TRUE, else, returns FALSE
        public boolean isInteger() {
            return integer != null;
        }

        // Returns the single integer, if this NestedIntegers holds a single integer
        // Returns null if this NestedIntegers holds a nested list
        public Integer getInteger() {
            return integer;
        }

        // Sets this NestedIntegers to hold a single integer.
        public void setInteger(int value) {
            this.nList = null;
            this.integer = value;
        }

        // Sets this NestedIntegers to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            if (this.integer != null) {
                this.nList = new ArrayList<>();
                this.nList.add(new NestedInteger(this.integer));
                this.integer = null;
            }
            if (this.nList != null) {
                this.nList.add(ni);
            }
        }

        // Returns the nested list, if this NestedIntegers holds a nested list
        // Returns null if this NestedIntegers holds a single integer
        public List<NestedInteger> getList() {
            return nList;
        }
    }

}

