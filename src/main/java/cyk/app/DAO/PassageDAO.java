package cyk.app.DAO;

import cyk.app.bean.Info;
import cyk.app.bean.Passage;
import cyk.app.bean.User;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PassageDAO extends BaseDAO {
    public PassageDAO() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
    }

    public Passage getPassage(int id){
        return sqlSession.selectOne("selectPassage", id);
    }

    public void newPassage(@Param("passage") Passage p){
        sqlSession.insert("newPassage", p);
        sqlSession.commit();
    }

    public void deletePassage(int id){
        sqlSession.delete("deletePassage", id);
        sqlSession.commit();
    }

    public void updatePassage(@Param("passage") Passage passage){
        sqlSession.update("updatePassage", passage);
        sqlSession.commit();
    }

    public List<Passage> getAllPassages(){
        return sqlSession.selectList("selectAllPassages");
    }

    public List<Passage> getPassagesByUsername(String username) {return sqlSession.selectList("selectPassagesByUsername", username);}

    public List<Passage> getPassagesByTitle(String title) {return sqlSession.selectList("selectPassagesByTitle", title);}

    public List<Passage> getPassagesByUsernameAndTitle(String username, String title){
        Map parama = new HashMap();
        parama.put("username", username);
        parama.put("title", title);
        return sqlSession.selectList("selectPassagesByUsernameAndTitle", parama);
    }

    public List<Info> getInfos(){
        return sqlSession.selectList("selectInfo");
    }

    public static void main(String[] args) {
        Passage p = new Passage(3, "这是个标题1", "CYK", "这是文章内容1");
        PassageDAO pd = null;
        try {
            pd = new PassageDAO();
//            pd.newPassage(p);
//            pd.deletePassage(1);
//            pd.updatePassage(p);
//            List<Passage> ps = pd.getPassagesByTitle("题");
//            for(Passage i: ps){
//                System.out.println(i);
//            }
//            List<Passage> ps = pd.getPassagesByUsernameAndTitle("WUTCYK", "测试");
            List<Info> is = pd.getInfos();
            for(Info i: is){
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
