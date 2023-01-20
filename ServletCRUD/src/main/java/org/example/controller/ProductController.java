package org.example.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.example.model.Category;
import org.example.model.Product;
import org.example.model.dto.ProductDTO;
import org.example.services.ProductService;
import org.example.utils.getFileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import static jdk.jpackage.internal.IOUtils.getFileName;

@WebServlet(urlPatterns = "/product/*")
@MultipartConfig(fileSizeThreshold=1024*1024*10,maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<ProductDTO> productList =  new ArrayList<>();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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

            try{
                productList = this.productService.getAllProduct(categoryId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        response.getWriter().print(new Gson().toJson(productList));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDTO productDTO = new ProductDTO();
        getFileUtils getfileutils = new getFileUtils();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        Product product =  new Gson().fromJson(request.getParameter("product"),Product.class);

        product.setImage( getfileutils.fileUpload(request));

        try {
            productDTO = this.productService.addProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().print(new Gson().toJson(productDTO));

    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDTO productDTO = new ProductDTO();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        getFileUtils getfileutils = new getFileUtils();

        Product product =  new Gson().fromJson(request.getParameter("product"),Product.class);

        product.setImage( getfileutils.fileUpload(request));

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
