package me.hsanchez.digital_library.services;

import me.hsanchez.digital_library.dao.UserDAO;
import me.hsanchez.digital_library.dto.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author hsanchez <hsanchez.dev@gmail.com>
 */
public class UsersService {

    private final UserDAO usersDAO;

    public UsersService() {
        this.usersDAO = new UserDAO();
    }

    public UserDTO checkCredentials(String username, String password) {
        System.out.println("SERVICE - Start: checkCredentials");
        UserDTO user = this.usersDAO.getUserByUsername(username);
        
        if(user == null) {
            return null;
        }
        
        String hashedPassword = DigestUtils.md5Hex(password);
        
        if(!user.getPassword().equals(hashedPassword)) {
            return null;
        }

        System.out.println("SERVICE - End: checkCredentials");
        return user;
    }
}
