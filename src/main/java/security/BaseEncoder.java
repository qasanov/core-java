package security;

public interface BaseEncoder {
    String encode(long number);
    long decode(String number);
}

