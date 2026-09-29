import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;


public class ImageDecryptor {



    public static void decrypt(
            String input,
            
            String output,
            int level
    ) throws Exception {



        if(level == 1)
        {

            rgbDecrypt(
                    input,
                    output,
                    level
            );

        }



        else if(level == 2)
        {

            pixelDecrypt(
                    input,
                    output,
                    level
            );

        }



        else if(level == 3)
        {


            String temp =
                    "temp.png";



            // reverse pixel first

            pixelDecrypt(
                    input,
                    temp,
                    level
            );



            // reverse RGB second

            rgbDecrypt(
                    temp,
                    output,
                    level
            );



            new File(temp).delete();

        }



    }







    private static void rgbDecrypt(
            String input,
            String output,
            int key
    ) throws Exception {



        BufferedImage image =
                ImageIO.read(
                new File(input));



        for(int x=0;x<image.getWidth();x++)
        {

            for(int y=0;y<image.getHeight();y++)
            {


                int pixel =
                        image.getRGB(x,y);



                int a =
                        (pixel>>24)&255;


                int r =
                        (pixel>>16)&255;


                int g =
                        (pixel>>8)&255;


                int b =
                        pixel&255;




                r =
                (r - key*80) & 255;


                g =
                (g - key*120) & 255;


                b =
                (b - key*160) & 255;




                image.setRGB(
                        x,
                        y,
                        (a<<24)
                        |
                        (r<<16)
                        |
                        (g<<8)
                        |
                        b
                );


            }

        }




        ImageIO.write(
                image,
                "png",
                new File(output)
        );


        System.out.println(
                "RGB Decrypted"
        );


    }









    private static void pixelDecrypt(
            String input,
            String output,
            int key
    ) throws Exception {



        BufferedImage encrypted =
                ImageIO.read(
                new File(input));



        int width =
                encrypted.getWidth();


        int height =
                encrypted.getHeight();



        BufferedImage decrypted =
                new BufferedImage(
                        width,
                        height,
                        BufferedImage.TYPE_INT_ARGB
                );



        ArrayList<Integer> map =
                new ArrayList<>();



        for(int i=0;i<width*height;i++)
        {
            map.add(i);
        }



        Collections.shuffle(
                map,
                new Random(key)
        );




        for(int i=0;i<map.size();i++)
        {


            int encryptedPosition =
                    map.get(i);



            int encryptedX =
                    encryptedPosition % width;


            int encryptedY =
                    encryptedPosition / width;



            int originalX =
                    i % width;


            int originalY =
                    i / width;



            decrypted.setRGB(
                    originalX,
                    originalY,
                    encrypted.getRGB(
                            encryptedX,
                            encryptedY
                    )
            );

        }




        ImageIO.write(
                decrypted,
                "png",
                new File(output)
        );


        System.out.println(
                "Pixels Decrypted"
        );


    }


}