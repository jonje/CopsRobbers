package copsandrobbers.randomgeneration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by devin on 10/28/15.
 */
public class HotBitsHelper {
    private byte[] buffer;
    private int bitsToGenerate;
    private int positionInBuffer;

    private boolean firstRun = true;

    public HotBitsHelper(int bitToGenerate) {
        this.bitsToGenerate = bitToGenerate;
        this.buffer = new byte[bitsToGenerate];
    }

    private void fillBuffer() throws IOException {
        URL hotBitsQuery = new URL("http://www.fourmilab.ch/cgi-bin/uncgi/Hotbits?nbytes="
                + bitsToGenerate + "&fmt=bin");

        InputStream stream = hotBitsQuery.openStream();

        int current;
        while ((current = stream.read()) != -1) {
            buffer[positionInBuffer++] = (byte) current;
        }

        positionInBuffer = 0;
    }

    public byte nextByte() {
        if (positionInBuffer >= buffer.length || firstRun) {
            try {
                fillBuffer();
                positionInBuffer = 0;
                firstRun = false;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return buffer[positionInBuffer++];
    }
}
