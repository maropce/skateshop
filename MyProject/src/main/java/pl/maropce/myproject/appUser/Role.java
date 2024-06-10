package pl.maropce.myproject.appUser;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");


    public final String fullName;
    Role(String fullName) {
        this.fullName = fullName;
    }


}
