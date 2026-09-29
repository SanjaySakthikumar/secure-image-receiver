public class User {

    private String username;
    private String password;
    private String priority;
    private String role;


    public User(
            String username,
            String password,
            String priority,
            String role
    ) {

        this.username = username;
        this.password = password;
        this.priority = priority;
        this.role = role;

    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getPriority() {
        return priority;
    }


    public String getRole() {
        return role;
    }

}