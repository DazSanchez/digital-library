package me.hsanchez.digital_library.services;

import me.hsanchez.digital_library.dao.UserDAO;
import me.hsanchez.digital_library.dto.UserDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.exceptions.UserNotFoundException;
import me.hsanchez.digital_library.exceptions.WrongPasswordException;
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

    public UserDTO checkCredentials(String username, String password) throws UserNotFoundException, WrongPasswordException, QueryExecutionException {
        System.out.println("SERVICE - Start: checkCredentials");
        UserDTO user = this.usersDAO.getUserByUsername(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        String hashedPassword = DigestUtils.md5Hex(password);

        if (!user.getPassword().equals(hashedPassword)) {
            throw new WrongPasswordException();
        }
        
        user.setPassword("");

        System.out.println("SERVICE - End: checkCredentials");
        return user;
    }
}
