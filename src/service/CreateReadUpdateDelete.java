package service;

import model.Contact;

import java.util.Map;
import java.util.UUID;

public interface CreateReadUpdateDelete  {

    Map<UUID, Contact> add();

    String getAll();

    void get(String firstname);



}
