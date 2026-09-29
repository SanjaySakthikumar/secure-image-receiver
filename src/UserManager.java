import java.util.ArrayList;

public class UserManager {


    private static ArrayList<User> users =
            new ArrayList<>();


    // Temporary receiver users

    static {

        users.add(
                new User(
                        "sanjay",
                        "1234",
                        "HIGH",
                        "RECEIVER"
                )
        );


        users.add(
                new User(
                        "sachitha",
                        "5678",
                        "MEDIUM",
                        "RECEIVER"
                )
        );


        users.add(
                new User(
                        "vanathi",
                        
                        "9999",
                        "LOW",
                        "RECEIVER"
                )
        );

    }



    public static User login(
            String username,
            String password
    ) {


        for(User u : users)
        {

            if(u.getUsername()
                    .equals(username)
              &&
               u.getPassword()
                    .equals(password))
            {

                return u;

            }

        }


        return null;

    }



    public static boolean verify(
            String username,
            String password
    )
    {

        return login(username,password)!=null;

    }


}