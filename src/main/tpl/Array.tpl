package @package@;

public final class @className@ {
    private static final int DEFAULT_CAPACITY = 16;
    private static final java.nio.ByteOrder NATIVE_BYTE_ORDER = java.nio.ByteOrder.nativeOrder();

    @primitiveType@[] data;
    int size;

    public @className@() {
        this(DEFAULT_CAPACITY);
    }

    public @className@(int capacity) {
        this.data = new @primitiveType@[capacity];
        this.size = 0;
    }

    public @className@(@primitiveType@[] data) {
        this.data = data;
        this.size = data.length;
    }

    public @className@(@primitiveType@[] data, int size) {
        this.data = data;
        this.size = size;
    }

    public void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            @primitiveType@[] oldData = data;
            int newCapacity = (oldCapacity * 3)/2 + 1;
            @primitiveType@[] newData = new @primitiveType@[newCapacity];
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            System.arraycopy(oldData, 0, newData, 0, oldCapacity);
            data = newData;
        }
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(idx));
        }
    }

    private void checkIndex(int idx, int len) {
        if (len < 1) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (idx < 0) {
            throw new IndexOutOfBoundsException(String.valueOf(idx));
        }
        int end = idx+len-1;
        if (end >= size) {
            throw new IndexOutOfBoundsException(idx + "-" + end);
        }
    }

    public @primitiveType@ get(int idx) {
        checkIndex(idx);
        return data[idx];
    }

    public void get(int idx, @primitiveType@[] values) {
        get(idx, values, 0, values.length);
    }

    public void get(int idx, @primitiveType@[] values, int length) {
        get(idx, values, 0, length);
    }

    public void get(int idx, @primitiveType@[] values, int offset, int length) {
        checkIndex(idx, length);
        System.arraycopy(data, idx, values, offset, length);
    }

    public void get(int idx, java.nio.@bufferType@ b) {
        get(idx, b, size-idx);
    }

    public void get(int idx, java.nio.@bufferType@ b, int length) {
        checkIndex(idx, length);
        b.put(data, idx, length);
    }

    public void get(java.nio.@bufferType@ b) {
        b.put(data, 0, size);
    }

    public void set(int idx, @primitiveType@ value) {
        checkIndex(idx);
        data[idx] = value;
    }

    public void set(int idx, @primitiveType@[] values, int offset, int length) {
        checkIndex(idx, length);
        System.arraycopy(values, offset, data, idx, length);
    }

    public void set(int idx, @primitiveType@[] values, int length) {
        set(idx, values, 0, length);
    }

    public void set(int idx, @primitiveType@[] values) {
        set(idx, values, 0, values.length);
    }

    public void add(@primitiveType@ value) {
        ensureCapacity(size+1);
        data[size++] = value;
    }

    public void add(@primitiveType@ v1, @primitiveType@ v2) {
        ensureCapacity(size+2);
        data[size  ] = v1;
        data[size+1] = v2;
        size += 2;
    }

    public void add(@primitiveType@ v1, @primitiveType@ v2, @primitiveType@ v3) {
        ensureCapacity(size+3);
        data[size  ] = v1;
        data[size+1] = v2;
        data[size+2] = v3;
        size += 3;
    }

    public void add(@primitiveType@ v1, @primitiveType@ v2, @primitiveType@ v3, @primitiveType@ v4) {
        ensureCapacity(size+4);
        data[size  ] = v1;
        data[size+1] = v2;
        data[size+2] = v3;
        data[size+3] = v4;
        size += 4;
    }

    public void add(@primitiveType@[] v) {
        ensureCapacity(size+v.length);
        System.arraycopy(v, 0, data, size, v.length);
        size += v.length;
    }

    public void add(@primitiveType@[] v, int offset, int length) {
        ensureCapacity(size+length);
        System.arraycopy(v, offset, data, size, length);
        size += length;
    }

    public void add(@className@ v, int offset, int length) {
        ensureCapacity(size+length);
        System.arraycopy(v.data, offset, data, size, length);
        size += length;
    }

    public void push(@primitiveType@ v) {
        add(v);
    }

    public @primitiveType@ pop() {
        if (size == 0) {
            throw new IllegalStateException("@className@ size is 0");
        }
        return data[--size];
    }

    public @primitiveType@ top() {
        if (size == 0) {
            throw new IllegalStateException("@className@ size is 0");
        }
        return data[size-1];
    }

    public @primitiveType@[] toArray() {
        @primitiveType@[] res = new @primitiveType@[size];
        System.arraycopy(data, 0, res, 0, size);
        return res;
    }

    public java.nio.@bufferType@ toDirectBuffer() {
        java.nio.@bufferType@ b = java.nio.ByteBuffer.allocateDirect(@wrapperType@.SIZE/8*size).order(NATIVE_BYTE_ORDER)@asBuffer@;
        b.put(data, 0, size).position(0);
        return b;
    }

    public java.nio.@bufferType@ toBuffer() {
        java.nio.@bufferType@ b = java.nio.@bufferType@.allocate(size);
        b.put(data, 0, size).position(0);
        return b;
    }

    public java.nio.@bufferType@ asBuffer() {
        return java.nio.@bufferType@.wrap(data, 0, size);
    }

    public @className@ copy(int idx, int length) {
        checkIndex(idx, length);
        @primitiveType@[] d = new @primitiveType@[size];
        System.arraycopy(this.data, 0, d, 0, size);
        return new @className@(d);
    }

    public @className@ copy() {
        return copy(0, size);
    }

    public void clear() {
        size = 0;
    }

    public void trimToSize() {
        if (size < data.length) {
            @primitiveType@[] tmp = new @primitiveType@[size];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public void reverse() {
        @primitiveType@[] d = this.data;
        int s = size;
        for (int i=0, m=s/2; i<m; i++) {
            @primitiveType@ tmp = d[i];
            d[i] = d[s-i-1];
            d[s-i-1] = tmp;
        }
    }

    public boolean contentEquals(@className@ other) {
        if (this == other) {
            return true;
        } else if (size != other.size) {
            return false;
        } else {
            @primitiveType@[] d1 = data;
            @primitiveType@[] d2 = other.data;

            for (int i=0, len=size; i<len; i++) {
                if (d1[i] != d2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public void swap(int i1, int i2, int length) {
        checkIndex(i1, length);
        checkIndex(i2, length);
        @primitiveType@[] d = this.data;
        for (int i=0; i<length; i++) {
            @primitiveType@ tmp = d[i1+i];
            d[i1+1] = d[i2+i];
            d[i2+i] = tmp;
        }
    }
}
