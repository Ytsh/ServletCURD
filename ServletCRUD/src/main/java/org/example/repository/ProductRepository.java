package org.example.repository;

import org.example.model.Category;
import org.example.model.Product;
import org.example.model.dto.ProductDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Connection {
    private String sql = "";
    public List<ProductDTO> getAllProductForCategory(int category_id) throws SQLException {
        List<ProductDTO> allProduct = new ArrayList<>();
        try {
            CategoryRepository categoryRepository = new CategoryRepository();
            Category category = categoryRepository.getCategory(category_id);

            sql = "Select * from product where categoryId =?";

            setConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, category_id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("productName");
                String productDescription = resultSet.getString("productDescription");
                String image = resultSet.getString("image");
                float price = resultSet.getFloat("price");
                allProduct.add(new ProductDTO(id, productName, productDescription,image, price, category));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }


        return allProduct;
    }

    public ProductDTO getProductById(int productId) throws SQLException {
        ProductDTO productDTO = new ProductDTO();
        try {
            sql = "Select *,p.id as pid, c.id as cid from product p INNER JOIN category c ON p.categoryId=c.id where p.id =?";
            setConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, productId);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("pid");
                String productName = resultSet.getString("productName");
                String productDescription = resultSet.getString("productDescription");
                String image = resultSet.getString("image");
//                int categoryId = resultSet.getInt(("categoryId"));
                int cid = resultSet.getInt(("cid"));
                float price = resultSet.getFloat("price");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");



                productDTO.setId(id);
                productDTO.setProductName(productName);
                productDTO.setProductDescription(productDescription);
                productDTO.setImage(image);
                productDTO.setPrice(price);
                Category category = new Category(cid,categoryName,categoryDescription);
                productDTO.setCategory(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
        return productDTO;
    }


    public ProductDTO addProduct(Product product) throws SQLException {
        ProductDTO productDTO = new ProductDTO();
        try {
//            sql = "Select * from category";
            setConnection();
            sql = "insert into product(productName,productDescription,image,price,categoryId) values(?,?,?,?,?);";

            PreparedStatement prepareStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, product.getProductName());
            prepareStatement.setString(2, product.getProductDescription());
            prepareStatement.setString(3, product.getImage());
            prepareStatement.setFloat(4, product.getPrice());
            prepareStatement.setInt(5, product.getCategoryId());

            int isInserted = prepareStatement.executeUpdate();
            if (isInserted == 1) {
                System.out.println("Record edited successfully.");
            }
            int id = 0;
            try (ResultSet generatedKeys = prepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }}

            productDTO = getProductById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
        return productDTO;
    }

    public ProductDTO updateProduct(Product product, int productId) throws SQLException {
        ProductDTO productDTO = new ProductDTO();
        try {
//            sql = "Select * from category";
            setConnection();
            sql = "Update product set productName = ?, productDescription = ?, image = ?, price = ?, categoryId = ? where id = ?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, product.getProductName());
            prepareStatement.setString(2, product.getProductDescription());
            prepareStatement.setString(3, product.getImage());
            prepareStatement.setFloat(4, product.getPrice());
            prepareStatement.setInt(5, product.getCategoryId());
            prepareStatement.setInt(6, productId);

            int isUpdated = prepareStatement.executeUpdate();
            if (isUpdated == 1) {
                System.out.println("Record edited successfully.");
            }


            productDTO = getProductById(productId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
        return productDTO;
    }


    public void deleteProduct(int productId) throws SQLException {
        try {
            setConnection();
            sql = "Delete from product where id =?";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, productId);

            int isDeleted = prepareStatement.executeUpdate();

            if (isDeleted == 1) {
                System.out.println("Record edited successfully.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> allProduct = new ArrayList<>();
        try {

            setConnection();

            sql = "Select *,p.id as pid, c.id as cid from product p inner join category c on p.categoryId= c.id";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("pid");
                String productName = resultSet.getString("productName");
                String productDescription = resultSet.getString("productDescription");
                String image = resultSet.getString("image");
                int cid = resultSet.getInt("cid");
                float price = resultSet.getFloat("price");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                Category category = new Category(cid,categoryName,categoryDescription);
                allProduct.add(new ProductDTO(id, productName, productDescription,image,price,category));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }


        return allProduct;
    }
}

