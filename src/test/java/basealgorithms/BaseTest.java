package basealgorithms;

import org.junit.Test;
import security.Base10Encoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see : https://www.slmanju.com/2021/07/basebase62-encoding-with-java.html
 */
public class BaseTest {

    @Test
    public void encodeBase62String() {
        String encode = Base10Encoder.BASE_62.encode(1);
        assertThat(encode.length()).isEqualTo(7);
    }

    @Test
    public void decodeBase62String() {
        long decode = Base10Encoder.BASE_62.decode("0000001");
        assertThat(decode).isEqualTo(1);
    }
}
