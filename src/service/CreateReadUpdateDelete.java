package service;

import model.Contact;

import java.io.IOException;

public interface CreateReadUpdateDelete {

    Contact add();

    String getAll();

    void get(String firstname);

    void delete();

    void update();

    void exit() throws IOException;


}
