package net.jhorstmann.util.arrays;

public final class CharArraySequence implements CharSequence {
    private CharArray array;
    private int offset;
    private int length;

    public CharArraySequence(CharArray array) {
        this(array, 0, array.size);
    }

    public CharArraySequence(CharArray array, int offset) {
        this(array, offset, array.size-offset);
    }

    public CharArraySequence(CharArray array, int offset, int length) {
        if (offset < 0 || offset >= array.size || length > array.size) {
            throw new IndexOutOfBoundsException();
        }
        this.array = array;
        this.offset = offset;
        this.length = length;
    }

    public char charAt(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        return array.get(offset+index);
    }

    public int length() {
        return length;
    }

    public CharSequence subSequence(int start, int end) {
        if (start < 0 || start >= length || end < start || end >= start+length) {
            throw new IndexOutOfBoundsException();
        }
        return new CharArraySequence(array, offset+start, end-start);
    }

    @Override
    public String toString() {
        return new String(array.data, offset, length);
    }
}
