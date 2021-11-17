package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * The class ClientBLL uses the methods from the dao package in order to check the results of the Client related queries
 */
public class ClientBLL {

    public List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * This method represents the constructor of the class ClientBLL and creates a list of validators for the client
     * and also a new instance of the ClientDAO
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        clientDAO = new ClientDAO();
    }

    /**
     * This method returns the result provided by the findById method in ClientDAO
     * @param id represents the id by which a client is selected
     * @return the client, if it was found or an exception otherwise
     */
    public Client findById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("Client with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * This method checks if a client was inserted by the insert method in ClientDAO
     * @param client represents the client to be inserted
     */
    public void insert(Client client) {
        if (!clientDAO.insert(client)) {
            throw new NoSuchElementException("Client could not be inserted");
        }
    }

    /**
     * This method checks if a client was updated by the update method in ClientDAO
     * @param client represents the client to be updated
     */
    public void update(Client client) {
        if (!clientDAO.update(client)) {
            throw new NoSuchElementException("Client could not be updated");
        }
    }

    /**
     * This method returns the result provided by the findAll method in ClientDAO
     * @return a list of clients, if that exists, or an exception otherwise
     */
    public List<Client> findAll() {
        List<Client> clientList = clientDAO.findAll();
        if (clientList == null) {
            throw new NoSuchElementException("Could not be retrieve the list of the clients");
        }
        return clientList;
    }

    /**
     * This method checks if a client was deleted by the delete method in ClientDAO
     * @param client represents the client to be deleted
     */
    public void delete(Client client) {
        if (!clientDAO.delete(client)) {
            throw new NoSuchElementException("Client could not be deleted");
        }
    }
}



