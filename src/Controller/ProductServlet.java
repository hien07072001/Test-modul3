package Controller;

import Model.Product;
import Service.ProductIpl;
import Service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name="ProductServlet",urlPatterns="/product")
public class ProductServlet extends HttpServlet {
    private ProductService productService=new ProductIpl();
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String action=request.getParameter("action");
        switch (action !=null?action:""){
            case "create":
                try {
                    createProduct(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                editProduct(request,response);
                break;
            default:
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        Float price=Float.parseFloat(request.getParameter("price"));
        int quantity =Integer.parseInt(request.getParameter("quantity"));
        String color=request.getParameter("color");
        Product product=new Product(name,price,quantity,color);
        this.productService.insert(product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("create.jsp");
        request.setAttribute("messeage","tao moi khong thanh cong");
        dispatcher.forward(request,response);

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
        String action=request.getParameter("action");
        switch (action !=null?action:""){
            case "create":
                showCreateProduct(request,response);
                break;

            case "list":
                showListProduct(request, response);
                break;
                
            default:
                break;



        }
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = this.productService.findAll();
        request.setAttribute("listProduct", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request,response);

    }

}
