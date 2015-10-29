package copsandrobbers.randomgeneration;

import java.io.*;
import java.net.URL;

/**
 * Created by devin on 10/28/15.
 */
public class HotBitsHelper {
    private byte[] buffer;
    private int bytesToGenerate;
    private int positionInBuffer;

    public HotBitsHelper(int bytesToGenerate) {
        this.bytesToGenerate = bytesToGenerate;
        this.buffer = new byte[bytesToGenerate];
        this.positionInBuffer = -1;
    }

    private InputStream checkDataLimitExceded(InputStream input) throws Exception {
        ByteArrayOutputStream clone = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) != -1 ) {
            clone.write(buffer, 0, len);
        }
        clone.flush();

        InputStream cloned = new ByteArrayInputStream(clone.toByteArray());
        BufferedReader reader = new BufferedReader(new InputStreamReader(cloned));

        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("exceeded")) {
                throw new Exception("Data request Rejected, 24 hour data limit reached.");
            }
        }

        return new ByteArrayInputStream(clone.toByteArray());
    }

    private void fillBuffer() throws Exception {
        URL hotBitsQuery = new URL("http://www.fourmilab.ch/cgi-bin/uncgi/Hotbits?nbytes="
                + bytesToGenerate + "&fmt=bin");

        InputStream stream = checkDataLimitExceded(hotBitsQuery.openStream());
        positionInBuffer = 0;

        int current;
        while ((current = stream.read()) != -1) {
            buffer[positionInBuffer++] = (byte) current;
        }

//        System.out.println();
        positionInBuffer = 0;
    }

    public byte nextByte() {
        if (positionInBuffer >= buffer.length || positionInBuffer < 0) {
            try {
                fillBuffer();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return buffer[positionInBuffer++];
    }
}
