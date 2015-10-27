package com.ca.field.test;

import java.util.Comparator;

class ArrayComparator implements Comparator<Comparable[]> {
    private final int columnToSort;
    private final boolean ascending;

    public ArrayComparator(int columnToSort, boolean ascending) {
        this.columnToSort = columnToSort;
        this.ascending = ascending;
    }

    public int compare(Comparable[] c1, Comparable[] c2) {
        int cmp = c1[columnToSort].compareTo(c2[columnToSort]);
        return ascending ? cmp : -cmp;
    }
}
