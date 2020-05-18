package homework.java;

public class Exercise5 {
    public static void main(String[] args) {
        Receptionist r1 = new Receptionist(new V1Filter());
        Receptionist r2 = new Receptionist(new V2Filter());
        Receptionist r3 = new Receptionist(new AFilter());
        User[] users = new User[15];

        for (int i = 0; i < users.length; i++) {
            users[i] = new User(i+1);
            if (i<5){
                r1.setUserType(users[i]);
                r1.receptionMethod(users[i]);
            }else if(i<10){
                r2.setUserType(users[i]);
                r2.receptionMethod(users[i]);
            }else {
                r3.setUserType(users[i]);
                r3.receptionMethod(users[i]);
            }
        }
    }
}

class User{
    private String type;
    private int id;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String toString(){
        return this.id+" - "+this.type;
    }
}

interface Filter{
    void filterUser(User u);
}

class V1Filter implements Filter{
    public void filterUser(User u){
        u.setType("v1");
    }
}

class V2Filter implements Filter{
    public void filterUser(User u){
        u.setType("v2");
    }
}

class AFilter implements Filter{
    public void filterUser(User u){
        u.setType("A");
    }
}

class Receptionist{
    private Filter filter;

    public Receptionist() {
    }

    public Receptionist(Filter filter) {
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void receptionMethod(User u){
        System.out.println(u);
    }

    public void setUserType(User u){
        filter.filterUser(u);
    }
}