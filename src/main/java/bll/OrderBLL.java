package bll;

import bll.validators.OrderValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.OrderTable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class OrderBLL uses the methods from the dao package in order to check the results of the OrderTable related queries
 */
public class OrderBLL {
    public List<Validator<OrderTable>> validators;
    private OrderDAO orderDAO;

    /**
     * This method represents the constructor of the class OrderBLL and creates a list of validators for the order
     * and also a new instance of the OrderDAO
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<OrderTable>>();
        validators.add(new OrderValidator());
        orderDAO = new OrderDAO();
    }
    /**
     * This method returns the result provided by the findById method in OrderDAO
     * @param id represents the id by which an order is selected
     * @return the order, if it was found or an exception otherwise
     */
    public OrderTable findById(int id) {
        OrderTable order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("Order with id =" + id + " was not found!");
        }
        return order;
    }

    /**
     * This method checks if an order was inserted by the insert method in OrderDAO
     * @param order represents the order to be inserted
     */
    public void insert(OrderTable order) {

        if (!orderDAO.insert(order)) {
            throw new NoSuchElementException("Order could not be inserted");
        }

    }

    /**
     * This method returns the result provided by the findAll method in OrderDAO
     * @return a list of orders, if that exists, or an exception otherwise
     */

    public List<OrderTable> findAll() {
        List<OrderTable> orderList = orderDAO.findAll();
        if (orderList == null) {
            throw new NoSuchElementException("Could not be retrieve the list of the orders");
        }
        return orderList;
    }
}
