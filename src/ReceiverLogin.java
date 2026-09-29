import java.util.Scanner;

public class ReceiverLogin {


    public static User startLogin()
    {

        Scanner sc = new Scanner(System.in);


        System.out.println("Receiver Login");


        System.out.print("Username : ");

        String username =
                sc.nextLine();



        System.out.print("Password : ");

        String password =
                sc.nextLine();



        User user =
                UserManager.login(
                        username,
                        password
                );



        if(user == null)
        {

            System.out.println(
                    "Login Failed"
            );

            return null;

        }



        if(!user.getRole()
                .equalsIgnoreCase("RECEIVER"))
        {

            System.out.println(
                    "Not a Receiver Account"
            );

            return null;

        }



        System.out.println(
                "Receiver Login Successful"
        );


        return user;

    }

}