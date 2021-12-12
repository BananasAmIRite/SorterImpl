package org.BananasAmIRite;

public class TestClass implements Comparable<TestClass> {
    private final String str;

    public TestClass(String s) {
        this.str = s;
    }

    @Override
    public int compareTo(TestClass o) {
        return this.str.length() - o.str.length();
    }

    @Override
    public String toString() {
        return "TestClass(" + str + ')';
    }
}
