package org.example.repository;

import org.example.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends Connection {
    private String sql = "";


    public List<Category> getAllCategory() throws SQLException, ClassNotFoundException {
        List<Category> allCategory = new ArrayList<>();
        try {
            sql = "Select * from category";
            setConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");

                allCategory.add(new Category(id, categoryName, categoryDescription));
            }
        } finally {
            disconnect();
        }
        return allCategory;
    }

    public Category getCategory(int category_id) throws SQLException, ClassNotFoundException {
        Category category = new Category();
        try {
            sql = "Select * from category where id =?";
            setConnection();
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, category_id);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");

                category.setId(id);
                category.setCategoryName(categoryName);
                category.setCategoryDescription(categoryDescription);
            }

        }

        finally {
            disconnect();
        }
        return category;
    }


    public Category updateCategory(Category categories, int id) throws SQLException {
        try {
//            sql = "Select * from category";
            setConnection();
            sql = "Update category set categoryName = ?, categoryDescription = ? where id = ?;";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, categories.getCategoryName());
            prepareStatement.setString(2, categories.getCategoryDescription());
            prepareStatement.setInt(3, id);
            int isInserted = prepareStatement.executeUpdate();
            if (isInserted == 1) {
                System.out.println("Record edited successfully.");
            }
            sql = "select * from category where id = "+id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
                int ids = resultSet.getInt("id");
                String categoryName = resultSet.getString("categoryName");
                String categoryDescription = resultSet.getString("categoryDescription");
                Category category = new Category(ids, categoryName, categoryDescription);
                return category;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }

    }

    public Category addCategory(Category categories) throws SQLException {
        try {
//            sql = "Select * from category";
            setConnection();
            sql = "insert into category(categoryName,categoryDescription) values(?,?);";

            PreparedStatement prepareStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1, categories.getCategoryName());
            prepareStatement.setString(2, categories.getCategoryDescription());

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

            sql = "select * from category where id = "+id;
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int ids = resultSet.getInt("id");
            String categoryName = resultSet.getString("categoryName");
            String categoryDescription = resultSet.getString("categoryDescription");
            Category category = new Category(ids, categoryName, categoryDescription);
            return category;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public void deleteCategory(int category_id) throws SQLException {
        try {
//            sql = "Select * from category";
            setConnection();
            sql = "Delete from category where id =?";

            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setInt(1, category_id);

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
}
