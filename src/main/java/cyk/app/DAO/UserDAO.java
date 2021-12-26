package cyk.app.DAO;

import cyk.app.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {

    public UserDAO() throws IOException {
        sqlSession =  sqlSessionFactory.openSession();
    }

    public User getUser(String username){
        return sqlSession.selectOne("selectUser", username);
    }

    public void newUser(@Param("user") User user){
        sqlSession.insert("newUser", user);
        sqlSession.commit();
    }

    public void deleteUser(String username){
        sqlSession.delete("deleteUser", username);
        sqlSession.commit();
    }

    public void updateUser(@Param("user") User user){
        sqlSession.update("updateUser", user);
        sqlSession.commit();
    }

    public List<User> getAllUsers(){
        return sqlSession.selectList("selectAllUsers");
    }

    public List<User> getAllUsersLike(String s) {
        List<User> users = new ArrayList<User>();
        try{
            users = sqlSession.selectList("selectUsersLike", s);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static void main(String[] args) {
        UserDAO dao = null;
        try {
            dao = new UserDAO();
//            User cyk = new User("cyk2","1234","123@123.com", new Date(System.currentTimeMillis()), 100);
//            dao.updateUser(cyk);
            List<User> users = dao.getAllUsersLike("y");
            for(User u: users){
                System.out.println(u);
            }
//            dao.deleteUser("cyk11");
            dao.closeSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
