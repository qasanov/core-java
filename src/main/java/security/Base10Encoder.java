package security;

public class Base10Encoder implements BaseEncoder{
    private final int base;
    private final String characters;
    private final int ENCODE_STRING_LENGTH;

    public final static BaseEncoder BASE_2 = new Base10Encoder( "01");
    public final static BaseEncoder BASE_16 = new Base10Encoder("0123456789ABCDEF");
    public final static BaseEncoder BASE_62 = new Base10Encoder( "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    public final static BaseEncoder BASE_58 = new Base10Encoder("123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz");

    public Base10Encoder( String characters) {
        this.characters = characters;
        this.base = characters.length();
        ENCODE_STRING_LENGTH = 7;
    }

    public Base10Encoder(int encodeStringLength, String characters) {
        this.characters = characters;
        this.base = characters.length();
        ENCODE_STRING_LENGTH = encodeStringLength;
    }

    @Override
    public String encode(long number) {
        StringBuilder stringBuilder = new StringBuilder(1);
        do {
            stringBuilder.insert(0, characters.charAt((int) (number % base)));
            number /= base;
        } while (number > 0);

        while (base > ENCODE_STRING_LENGTH && stringBuilder.length() < ENCODE_STRING_LENGTH)
            stringBuilder.append(0);

        return stringBuilder.toString();
    }

    @Override
    public long decode(String number) {
        long result = 0L;
        int length = number.length();
        for (int i = 0; i < length; i++) {
            result += (long) Math.pow(base, i) * characters.indexOf(number.charAt(length - i - 1));
        }
        return result;
    }
}
