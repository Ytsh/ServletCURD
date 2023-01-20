package org.example.services;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Schema;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.model.User;
import org.example.model.dto.SigninDTO;
import org.example.repository.AuthenticationRepository;
import org.example.utils.HTTPUtils;
import org.example.utils.JWTUtils;
import org.example.utils.Validation;

import java.sql.SQLException;
import java.util.List;

public class AuthenticationService {

    private final Gson gson;
    private final AuthenticationRepository authRepository;
    private final UserService userService;
    private final JWTUtils jwtUtils;

    public AuthenticationService() {
        this.gson = new Gson();
        this.authRepository = new AuthenticationRepository();
        this.userService = new UserService();
        this.jwtUtils = new JWTUtils();
    }

    public void register(HttpServletRequest request) throws ServletException, SQLException, ClassNotFoundException {
        String requestBody = HTTPUtils.jsonParser(request);
        User user = gson.fromJson(requestBody, User.class);
        if (!Validation.isEmail(user.getEmail())
                || Validation.isEmpty(user.getPassword())) {
            throw new ServletException("Email or password cannot be empty.");
        }
        authRepository.register(user);
    }

    public SigninDTO login(HttpServletRequest request) throws Exception {
        String requestBody = HTTPUtils.jsonParser(request);
        User userDTO = gson.fromJson(requestBody, User.class);
        String password = userDTO.getPassword().trim();
        if (!Validation.isEmail(userDTO.getEmail())
                || Validation.isEmpty(password)) {
            throw new ServletException("Email or password is invalid");
        }
        User user = authRepository.login(userDTO);
        if (user == null) {
            throw new Exception("User not found. Try again");
        }
        boolean passwordMatch = HTTPUtils.checkPasswordMatch(password, user.getPassword());
        if (!passwordMatch) {
            throw new Exception("Invalid credentials. Try again");
        }
        List<String> roles = userService.getRolesByEmail(userDTO.getEmail());
        String token = jwtUtils.generateToken(user.getEmail());
        return new SigninDTO(user, token, roles);
    }
}
