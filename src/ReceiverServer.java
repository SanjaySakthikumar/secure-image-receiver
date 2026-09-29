import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ReceiverServer {


    public static void main(String args[])
            throws Exception {


        Scanner sc =
                new Scanner(System.in);



        // Receiver Login

        System.out.println(
                "Receiver Login"
        );


        System.out.print(
                "Username : "
        );

        String username =
                sc.nextLine();



        System.out.print(
                "Password : "
        );

        String password =
                sc.nextLine();



        User receiver =
                UserManager.login(
                        username,
                        password
                );



        if(receiver == null)
        {

            System.out.println(
                    "Login Failed"
            );

            return;

        }



        System.out.println(
                "Login Successful"
        );



        ServerSocket server =
                new ServerSocket(5000);



        System.out.println(
                "Receiver Started..."
        );



        Socket socket =
                server.accept();



        System.out.println(
                "Sender Connected"
        );



        DataInputStream in =
                new DataInputStream(
                        socket.getInputStream()
                );



        // Receive security level

        int level =
                in.readInt();



        System.out.println(
                "Security Level : "
                +level
        );



        // Receive SHA

        String receivedHash =
                in.readUTF();



        // Receive filename

        String filename =
                in.readUTF();




        // Receive size

        long size =
                in.readLong();




        File folder =
                new File(
                "images/received"
                );


        folder.mkdirs();




        File receivedFile =
                new File(
                "images/received/"
                +filename
                );



        FileOutputStream fos =
                new FileOutputStream(
                        receivedFile
                );



        byte buffer[] =
                new byte[4096];


        long total=0;



        while(total<size)
        {

            int bytes =
                    in.read(buffer);



            if(bytes==-1)
                break;



            fos.write(
                    buffer,
                    0,
                    bytes
            );


            total += bytes;

        }



        fos.close();



        System.out.println(
                "Image Received"
        );




        // SHA Verification

        String calculatedHash =
                SHA256.generateHash(
                        receivedFile.getPath()
                );



        System.out.println(
                "Received SHA : "
                +receivedHash
        );


        System.out.println(
                "Calculated SHA : "
                +calculatedHash
        );



        if(receivedHash.equals(calculatedHash))
        {

            System.out.println(
                    "SHA Verification Successful"
            );



            File decryptFolder =
                    new File(
                    "images/decrypted"
                    );


            decryptFolder.mkdirs();



            String output =
                    "images/decrypted/decrypted_"
                    +filename;




            ImageDecryptor.decrypt(
                    receivedFile.getPath(),
                    output,
                    level
            );



            System.out.println(
                    "Image Decrypted"
            );


        }
        else
        {

            System.out.println(
                    "SHA Verification Failed"
            );

        }



        socket.close();

        server.close();


    }

}