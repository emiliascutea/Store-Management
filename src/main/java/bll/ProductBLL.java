package bll;

import bll.validators.PriceValidator;
import bll.validators.Validator;
import dao.ProductDAO;

import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class ProductBLL uses the methods from the dao package in order to check the results of the Product related queries
 */
public class ProductBLL {
    public List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * This method represents the constructor of the class ProductBLL and creates a list of validators for the product
     * and also a new instance of the ProductDAO
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        productDAO = new ProductDAO();
    }

    /**
     * This method returns the result provided by the findById method in ProductDAO
     * @param id represents the id by which a product is selected
     * @return the product, if it was found or an exception otherwise
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("Product with id =" + id + " was not found!");
        }
        return product;
    }

    /**
     * This method checks if a product was inserted by the insert method in ProductDAO
     * @param product represents the product to be inserted
     */
    public void insert(Product product) {

        if (!productDAO.insert(product)) {
            throw new NoSuchElementException("Client could not be inserted");
        }
    }

    /**
     * This method checks if a product was updated by the update method in ProductDAO
     * @param product represents the product to be updated
     */
    public void update(Product product) {
        if (!productDAO.update(product)) {
            throw new NoSuchElementException("Client could not be updated");
        }
    }

    /**
     * This method returns the result provided by the findAll method in ProductDAO
     * @return a list of products, if that exists, or an exception otherwise
     */
    public List<Product> findAll() {
        List<Product> productList = productDAO.findAll();
        if (productList == null) {
            throw new NoSuchElementException("Could not be retrieve the list of the clients");
        }
        return productList;
    }

    /**
     * This method returns the result provided by the findByName method in ProductDAO
     * @param name represents the name by which a product is selected
     * @return the product, if it was found or an exception otherwise
     */
    public Product findByName(String name) {
        Product product = productDAO.findByName(name);
        if (product == null) {
            throw new NoSuchElementException("Client with name =" + name + " was not found!");
        }
        return product;
    }
    /**
     * This method checks if a product was deleted by the delete method in ProductDAO
     * @param product represents the product to be deleted
     */
    public void delete(Product product) {
        if (!productDAO.delete(product)) {
            throw new NoSuchElementException("Client could not be deleted");
        }
    }
}
