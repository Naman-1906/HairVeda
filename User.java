public class User {
    private String name;
    private String username;
    private int age;
    private HairProfile hairProfile;

    public User(String name, String username, int age) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.hairProfile = new HairProfile();
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public HairProfile getHairProfile() {
        return hairProfile;
    }
}
