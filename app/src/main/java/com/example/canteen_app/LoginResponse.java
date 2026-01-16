package com.example.canteen_app;

public class LoginResponse {
    private String message;
    private UserData data;

    public String getMessage() {return message;}
    public UserData getData() {return data;}

    public static class UserData {
        private int user_id;
        private String email;
        private String fullname;
        private String user_role;
        private String user_phone;
        private String create_at;

        public int getUserId() { return user_id; }
        public String getFullname() { return fullname; }
        public String getEmail() { return email; }
    }
}
