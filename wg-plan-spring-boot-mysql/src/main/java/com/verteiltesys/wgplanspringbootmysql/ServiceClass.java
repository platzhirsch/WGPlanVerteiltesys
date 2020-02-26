package com.verteiltesys.wgplanspringbootmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceClass {

    @Autowired
    private Repository repo;

    public List<Aufgabe> listAll() {
        return repo.findAll();
    }

    public void save(Aufgabe aufgabe){
        repo.save(aufgabe);
    }

    public Aufgabe get(Integer id){
        return repo.findById(id).get();
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
