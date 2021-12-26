package cyk.app.bean;

public class Info {
    private String username;
    private int passage_num;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassage_num() {
        return passage_num;
    }

    public void setPassage_num(int passage_num) {
        this.passage_num = passage_num;
    }

    @Override
    public String toString() {
        return "Info{" +
                "username='" + username + '\'' +
                ", passage_num=" + passage_num +
                '}';
    }

    public Info(String username, int passage_num) {
        this.username = username;
        this.passage_num = passage_num;
    }

    public Info() { }
}
