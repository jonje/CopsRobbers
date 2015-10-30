package copsandrobbers.randomgeneration;

import java.io.*;
import java.net.URL;

/**
 * Created by devin on 10/28/15.
 */
public class HotBitsHelper {
    private byte[] buffer;
    private int positionInBuffer;
    private String currentDataSource;

    private final String hotBitsUrl;
    private final String randomOrgUrl;

    public HotBitsHelper(int bytesToGenerate) {
        this.buffer = new byte[bytesToGenerate];
        this.positionInBuffer = -1;

        this.randomOrgUrl = "https://www.random.org/cgi-bin/randbyte?nbytes="
                + bytesToGenerate + "&format=f";

        this.hotBitsUrl = "http://www.fourmilab.ch/cgi-bin/uncgi/Hotbits?nbytes="
                + bytesToGenerate + "&fmt=bin";

        this.currentDataSource = hotBitsUrl;
    }

    private InputStream checkDataLimitExceeded(InputStream input) throws Exception {
        ByteArrayOutputStream clone = cloneStream(input);

        InputStream cloned = new ByteArrayInputStream(clone.toByteArray());
        BufferedReader reader = new BufferedReader(new InputStreamReader(cloned));

        String str;
        while ((str = reader.readLine()) != null) {
            if (str.contains("exceeded")) {
                if (currentDataSource == hotBitsUrl) {
                    currentDataSource = randomOrgUrl;
                    return checkDataLimitExceeded(new URL(currentDataSource).openStream());
                } else {
                    throw new Exception("Data request Rejected, 24 hour data limit reached.");
                }
            }
        }

        return new ByteArrayInputStream(clone.toByteArray());
    }

    private ByteArrayOutputStream cloneStream(InputStream input) throws IOException {
        ByteArrayOutputStream clone = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) != -1 ) {
            clone.write(buffer, 0, len);
        }
        clone.flush();

        return clone;
    }

    private void fillBuffer() throws Exception {
        URL hotBitsQuery = new URL(hotBitsUrl);

        InputStream stream = checkDataLimitExceeded(hotBitsQuery.openStream());
        positionInBuffer = 0;

        int current;
        while ((current = stream.read()) != -1) {
            buffer[positionInBuffer++] = (byte) current;
        }
        
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
