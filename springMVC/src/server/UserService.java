package src.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.dao.UserDAO;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public List list(){
        return userDAO.list();
    }
}
