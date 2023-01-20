package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Category;
import org.example.model.dto.CategoryDTO;
import org.example.services.CategoryService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/category/*")
public class CategoryController extends HttpServlet {
    CategoryService categoryService = new CategoryService();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int len = request.getRequestURI().split("/").length;

        if (len <4){
            List<Category> categoryList = new ArrayList<>();
            try {
                categoryList = categoryService.getAllCategory();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().print(new Gson().toJson(categoryList));
        }
        else{
            List<CategoryDTO> categoryDTO = new ArrayList<>();

            try {
                categoryDTO = categoryService.getAllCategoryWithProduct();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            response.getWriter().print(new Gson().toJson(categoryDTO));


        }


    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category categories = new Category();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        Category category = stringBuilder(request);

        int len = request.getRequestURI().split("/").length;
        if (len ==4) {
            int categoryId = Integer.parseInt(request.getRequestURI().split("/")[3]);
            try {
                categories = categoryService.updateCategory(category,categoryId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().print(new Gson().toJson(categories));
        }
        else{
            response.setStatus(405);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Category categories = new Category();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Category category = stringBuilder(request);


            try {
                categories = categoryService.addCategory(category);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().print(new Gson().toJson(categories));

    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category categories = new Category();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int len = request.getRequestURI().split("/").length;
        if (len ==4){
            int categoryId = Integer.parseInt(request.getRequestURI().split("/")[3]);
//            Category category = gson.fromJson(sb.toString(), Category.class);
            try {
                categoryService.deleteCategory(categoryId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().print("Success");
        }
        else{
            response.setStatus(405);
        }



    }


    public Category stringBuilder(HttpServletRequest request) throws IOException {

        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }


        Gson gson = new Gson();
        Category category = gson.fromJson(sb.toString(), Category.class);
        return category;
    }
}
