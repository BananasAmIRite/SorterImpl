package org.BananasAmIRite;

import java.util.ArrayList;

public class SortedList<T extends Comparable<T>> extends ArrayList<T> {

    private boolean autoSort;

    public static void main(String[] args) {
        SortedList<Double> e = new SortedList<>(true);

        // testing sorting :D
        for (int i = 0; i < 100000; i++) {
            e.clear();
            e.setAutoSort(true);
            for (int j = 0; j < 100; j++) {
                e.add(Math.random());
            }

            for (int j = 0; j < e.size(); j++) {
                Double before = j-1 >= 0 ? e.get(j-1) : null;
                Double current = e.get(j);
                Double later = j+1 < e.size() ? e.get(j+1): null;
                if ((before == null && current >= later) ||
                        (later == null && current <= before) ||
                        (later != null && before != null && (current >= later || current <= before))) {
                    System.out.println("there was a problem with this set: " + e);
                    break;
                }
            }

            e.clear();
            e.setAutoSort(false);
            for (int j = 0; j < 100; j++) {
                e.add(Math.random());
            }
            e.sortAll();

            for (int j = 0; j < e.size(); j++) { // I know, right! bad code!!!
                Double before = j-1 >= 0 ? e.get(j-1) : null;
                Double current = e.get(j);
                Double later = j+1 < e.size() ? e.get(j+1): null;
                if ((before == null && current >= later) ||
                        (later == null && current <= before) ||
                        (later != null && before != null && (current >= later || current <= before))) {
                    System.out.println("there was a problem with this set: " + e);
                    break;
                }
            }

        }
    }

    public SortedList() {
        this(false);
    }

    public SortedList(boolean autoSort) {
        this.autoSort = autoSort;
    }

    @Override
    public boolean add(T t) {
        if (autoSort) {
            int pos = sortItem(t);
            if (pos == -1) return false;

            super.add(pos, t);
            return true;
        } else return super.add(t);
    }

    public void sortAll() {
        final ArrayList<T> copy = new ArrayList<>(this);

        for (T item : copy) {
            int newPosition = sortItem(item, indexOf(item));
            if (newPosition == -1) {
                System.out.println("something bad happened uh oh: " + newPosition);
                break;
            }

            if (remove(item)) {
                super.add(newPosition, item);
            }
        }
    }

    public int sortItem(T item) {
        if (size() == 0) return 0; // first item; just put it in
        return sortItem(item, size() - 1);
    }

    public int sortItem(T item, int startingIndex) {

        // in case item belongs at the starting index:
        if ((item != get(startingIndex)) && item.compareTo(get(startingIndex)) >= 0 && startingIndex == size() - 1) return startingIndex + 1;

        for (int i = Math.min(size() - 1, startingIndex); i >= 0; i--) {
            T itemAtIndex = get(i);
            T itemBeforeIndex = get(Math.max(i-1, 0));

            if (item.compareTo(itemAtIndex) <= 0 && (i - 1 < 0 || item.compareTo(itemBeforeIndex) >= 0)) return i;
        }
        return -1; // <-- this should NEVER happen
    }

    public void setAutoSort(boolean autoSort) {
        this.autoSort = autoSort;
    }

    public boolean isAutoSort() {
        return autoSort;
    }
}
