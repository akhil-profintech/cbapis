package in.pft.apis.creditbazaar.gateway.utils;

import de.huxhorn.sulky.ulid.ULID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdAndUlidGeneration
{
    private static final ULID ulid=new ULID();

    public static String generateUlid()
    {
        return ulid.nextULID();
    }

    public static long generateUniqueLong(int numberOfDigits)
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        int randomDigits = numberOfDigits - formattedDateTime.length();
        Random random = new Random();
        StringBuilder randomPart = new StringBuilder();
        for (int i = 0; i < randomDigits; i++) {
            randomPart.append(random.nextInt(10));
        }
        String uniqueString = formattedDateTime + randomPart;
        return Long.parseLong(uniqueString);
    }
}
