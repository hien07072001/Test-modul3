package Service;

import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductIpl implements ProductService {

    private String jdbcURL = "jdbc:mysql://localhost:3308/productManager?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_PRODUCT = "insert into product" + "  (name,price,quantity,color) VALUES " +
            " (?, ?, ?,?);";
    private static final String SELECT_PRODUCT = "select * from product";
    private static final String DELETE_PRODUCT = "delete from shoes where id = ?;";
    private static final String UPDATE_PRODUCT = "update shoes set name= ?,price= ?,quantity=? ,color=? where id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcPassword, jdbcUsername);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                products.add(new Product(name, price, quantity, color));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void insert(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT);
        try
                (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }

    @Override
    public boolean deleted(int id) throws SQLException {
        return false;
    }


}
