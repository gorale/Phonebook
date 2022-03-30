package service;

import model.Contact;

import java.util.Map;

public interface CreateReadUpdateDelete  {

    Map<String, Contact> add();

    String getAll();

    void get(String str);



}
