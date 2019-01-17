package uk.co.caprica.vlcj.player.direct;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class UnsafeAccess {
    public static final Unsafe UNSAFE;
    static {
        try {
            // This is a bit of voodoo to force the unsafe object into
            // visibility and acquire it.
            // This is not playing nice, but as an established back door it is
            // not likely to be
            // taken away.
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

/**
 * Factory for creating property aligned native byte buffers.
 */
public class ByteBufferFactory {

    private static final long addressOffset;
    public static final int CACHE_LINE_SIZE = 64;
    public static final int PAGE_SIZE = UnsafeAccess.UNSAFE.pageSize();
    static {
        try {
            addressOffset = UnsafeAccess.UNSAFE.objectFieldOffset(Buffer.class
                    .getDeclaredField("address"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static long getAddress(ByteBuffer buffy) {
        return UnsafeAccess.UNSAFE.getLong(buffy, addressOffset);
    }


    /**
     * Alignment suitable for use by LibVLC video callbacks.
     */
    private static final int LIBVLC_ALIGNMENT = 32;

    /**
     * Allocate a properly aligned native byte buffer, suitable for use by the LibVLC video
     * callbacks.
     *
     * @param capacity size of the buffer
     * @return aligned byte buffer
     */
    public static ByteBuffer allocateAlignedBuffer(int capacity) {
        return allocateAlignedBuffer(capacity, LIBVLC_ALIGNMENT);
    }

    /**
     * Allocate a property aligned native byte buffer.
     * <p></p>
     * Original credit: http://psy-lob-saw.blogspot.co.uk/2013/01/direct-memory-alignment-in-java.html
     *
     * @param capacity size of the buffer
     * @param alignment alignment
     * @return aligned byte buffer
     */
    public static ByteBuffer allocateAlignedBuffer(int capacity, int alignment) {
        ByteBuffer result;
        ByteBuffer buffer = ByteBuffer.allocateDirect(capacity + alignment);
        long address = getAddress(buffer);
        if ((address & (alignment - 1)) == 0) {
            buffer.limit(capacity);
            result = buffer.slice().order(ByteOrder.nativeOrder());
        }
        else {
            int newPosition = (int) (alignment - (address & (alignment - 1)));
            buffer.position(newPosition);
            buffer.limit(newPosition + capacity);
            result = buffer.slice().order(ByteOrder.nativeOrder());
        }
        return result;
    }
}
