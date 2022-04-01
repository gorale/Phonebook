package service;

import model.Contact;

public interface CreateReadUpdateDelete  {

    Contact add();

    String getAll();

    void get(String firstname);
    void delete();



}
