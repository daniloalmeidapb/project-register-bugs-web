package br.com.ellos.registerbugs.model.repository;

import java.util.List;

public interface Repository<T> {

    void add(T t);
    void update(T t);
    void delete(Long id);
    T find(Long id);
    List<T> getList();
}
