package bll.validators;

import model.Product;
import java.util.regex.Pattern;

/**
 * The class PriceValidator validates the price of a product
 */
public class PriceValidator implements Validator<Product> {
    private static final String PRICE_PATTERN = "^[1-9]\\d{0,7}(?:\\.\\d{1,4})?|\\.\\d{1,4}$\n";

    /**
     * This method validates the price of a product
     * @param product represents the product which will get its price validated
     */
    public void validate(Product product) {
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        if (!pattern.matcher(String.valueOf(product.getPrice())).matches()) {
            throw new IllegalArgumentException("Price must have a positive value!");
        }
    }
}
