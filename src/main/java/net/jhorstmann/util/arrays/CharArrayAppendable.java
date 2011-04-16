package net.jhorstmann.util.arrays;

public final class CharArrayAppendable implements Appendable {
    private CharArray array;

    public CharArrayAppendable(CharArray array) {
        this.array = array;
    }

    public Appendable append(char c) {
        array.add(c);
        return this;
    }

    public Appendable append(CharSequence csq) {
        for (int i=0, len=csq.length(); i<len; i++) {
            array.add(csq.charAt(i));
        }
        return this;
    }

    public Appendable append(CharSequence csq, int start, int end) {
        for (int i=start; i<end; i++) {
            array.add(csq.charAt(i));
        }
        return this;
    }
}
