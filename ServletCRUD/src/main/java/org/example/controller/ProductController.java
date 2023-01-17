package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.dto.ProductDTO;
import org.example.services.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ProductDTO> productList =  new ArrayList<>();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        StringBuilder sb = new StringBuilder();
//        String s;
//        while ((s = request.getReader().readLine()) != null) {
//            sb.append(s);
//        }
//
//        Gson gson = new Gson();

        //        request.getParameterNames();
        int len = request.getRequestURI().split("/").length;
        if (len <4){
            try{
                productList = this.productService.getAllProducts();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        else{

            int categoryId = Integer.parseInt(request.getRequestURI().split("/")[3]);

//        Category category = gson.fromJson(sb.toString(), Category.class);
            try{
                productList = this.productService.getAllProduct(categoryId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        response.getWriter().print(new Gson().toJson(productList));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDTO productDTO = new ProductDTO();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }
        Gson gson = new Gson();


        Product product = gson.fromJson(sb.toString(), Product.class);

        try {
            productDTO = this.productService.addProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(new Gson().toJson(productDTO));

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ProductDTO productDTO = new ProductDTO();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = request.getReader().readLine()) != null) {
            sb.append(s);
        }
        Gson gson = new Gson();

        Product product = gson.fromJson(sb.toString(), Product.class);

        int productId = Integer.parseInt(request.getRequestURI().split("/")[3]);

        try {
            productDTO = this.productService.updateProduct(product,productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(new Gson().toJson(productDTO));

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int productId = Integer.parseInt(request.getRequestURI().split("/")[3]);

        try{
            this.productService.deleteProduct(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.getWriter().print(new Gson().toJson("Success"));
    }

}
