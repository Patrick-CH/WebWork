package cyk.app.service;

import cyk.app.DAO.PassageDAO;
import cyk.app.bean.Info;
import cyk.app.bean.Passage;

import java.io.IOException;
import java.util.List;

public class PassageService extends BaseService {
    PassageDAO passageDAO;

    public PassageService() throws IOException {
        passageDAO = new PassageDAO();
    }

    public int addPassage(Passage p){
        if (null == passageDAO.getPassage(p.getId())){
            passageDAO.newPassage(p);
            return 0;
        } else {
            return -1;
        }
    }

    public int editPassageInfo(Passage info){
        Passage original = passageDAO.getPassage(info.getId());
        if (null != original){
            passageDAO.updatePassage(info);
            return 0;
        } else {
            return -1;
        }
    }

    public int removePassage(int id){
        if (null != passageDAO.getPassage(id)){
            passageDAO.deletePassage(id);
            return 0;
        } else {
            return -1;
        }
    }

    public Passage findPassageById(int id){
        return passageDAO.getPassage(id);
    }

    public List<Passage> findAllPassages(){
        return passageDAO.getAllPassages();
    }

    public List<Passage> findPassagesByTitle(String title){
        return passageDAO.getPassagesByTitle(title);
    }

    public List<Passage> findPassageByUsername(String username){
        return passageDAO.getPassagesByUsername(username);
    }

    public List<Passage> findPassagesByUsernameAndTitle(String username, String title){
        return passageDAO.getPassagesByUsernameAndTitle(username, title);
    }

    public List<Info> findInfos(){
        return passageDAO.getInfos();
    }
}
