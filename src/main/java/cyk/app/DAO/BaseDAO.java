package cyk.app.DAO;

import cyk.app.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BaseDAO {
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession = null;

    public BaseDAO() throws IOException {
        String resource = "cyk\\app\\config\\mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void openSqlSession(){
        if (sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
        }
    }

    public void closeSqlSession(){
        if (sqlSession != null) {
            sqlSession.close();
            sqlSession = null;
        }
    }

    public static void main(String[] args) {
        try {
            BaseDAO service = new BaseDAO();
            SqlSession sqlSession =  service.sqlSessionFactory.openSession();
            User user = sqlSession.selectOne("selectUser", "cyk");
            System.out.println(user);
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
