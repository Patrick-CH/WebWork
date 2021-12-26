package cyk.app.bean;

public class Passage {
    private int id;
    private String title;
    private String username;
    private String content;

    @Override
    public String toString() {
        return "Passage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Passage(int id, String title, String username, String content) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
    }

    public Passage(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
