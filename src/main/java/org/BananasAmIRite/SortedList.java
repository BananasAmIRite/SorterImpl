package org.BananasAmIRite;

import java.util.ArrayList;

public class SortedList<T extends Comparable<T>> extends ArrayList<T> {

    public static void main(String[] args) {
        SortedList<Integer> e = new SortedList<>();
        e.add(24);
        e.add(8);
        e.add(2);
        e.add(18);
        e.add(1);
        e.add(-1);
        e.add(1092381023);
        e.sortAll();
        System.out.println(e);
    }

    public SortedList() {

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
                add(newPosition, item);
            }
        }
    }

    public int sortItem(T item) {
        return sortItem(item, size() - 1);
    }

    public int sortItem(T item, int startingIndex) {
        for (int i = Math.min(size() - 1, startingIndex); i >= 0; i--) {
            T itemAtIndex = get(i);
            T itemBeforeIndex = get(Math.max(i-1, 0));

            if (item.compareTo(itemAtIndex) <= 0 && (i - 1 < 0 || item.compareTo(itemBeforeIndex) >= 0)) return i;
        }
        return -1;
    }
}
