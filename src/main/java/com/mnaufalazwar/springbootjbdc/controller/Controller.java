package com.mnaufalazwar.springbootjbdc.controller;

import com.mnaufalazwar.springbootjbdc.dao.MahasiswaDao;
import com.mnaufalazwar.springbootjbdc.entity.Mahasiswa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/con")
public class Controller {

    @Autowired
    private MahasiswaDao mahasiswaDao;

    @RequestMapping(value = "/insert", method = RequestMethod.PUT)
    public String insertMahasiswa(@RequestBody Mahasiswa mahasiswa){

        if(mahasiswaDao.insert(mahasiswa)){
            return "insert success";
        }
        else{
            return "insert failed";
        }

    }

}
