package org.example.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.dto.SigninDTO;
import org.example.services.AuthenticationService;
import org.example.utils.HTTPUtils;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthenticationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String requestType = request.getParameter("type");
        AuthenticationService authService = new AuthenticationService();
        if (requestType.equals("login")) {
            SigninDTO authResponse = null;
            try {
                authResponse = authService.login(request);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            HTTPUtils.sendResponse(response, authResponse);

        } else {

            try {
                authService.register(request);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
