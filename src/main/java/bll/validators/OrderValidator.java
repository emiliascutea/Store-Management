package bll.validators;

import model.OrderTable;

/**
 * The class OrderValidator validates the price of an order
 */
public class OrderValidator implements Validator<OrderTable> {

    /**
     * This method validates the price of an order
     * @param order represents the order which will get the price validated
     */
    public void validate(OrderTable order) {
        if (order.getPrice() <= 0 ) {
            throw new IllegalArgumentException("Price must have a positive value!");
        }
    }
}