package Service;

import Model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
   public List<Product>findAll();

   public  void  insert( Product product) throws SQLException;

    public  boolean update (Product product) throws SQLException;

    public  boolean deleted (int id ) throws SQLException;

}
