import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;


public class SHA256 {


    public static String generateHash(String filePath)
            throws Exception {


        MessageDigest md =
                MessageDigest.getInstance("SHA-256");


        FileInputStream fis =
                new FileInputStream(
                        new File(filePath)
                );


        byte buffer[] =
                new byte[4096];


        int bytes;


        while((bytes=fis.read(buffer))!=-1)
        {

            md.update(
                    buffer,
                    0,
                    bytes
            );

        }


        fis.close();



        byte hash[] =
                md.digest();



        StringBuilder result =
                new StringBuilder();



        for(byte b:hash)
        {

            result.append(
                    String.format(
                            "%02x",
                            b
                    )
            );

        }


        return result.toString();

    }


}