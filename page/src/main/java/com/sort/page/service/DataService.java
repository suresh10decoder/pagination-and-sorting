package com.sort.page.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sort.page.entity.DataEntity;
import com.sort.page.exception.RecordNotFoundException;
import com.sort.page.repository.DataRepository;

 
@Service
public class DataService{
     
    @Autowired
    DataRepository repository;
     
    public List<DataEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<DataEntity> pagedResult = repository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<DataEntity>();
        }
    }
     
    public DataEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<DataEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public DataEntity createOrUpdateEmployee(DataEntity entity) 
    {
     
    	return repository.save(entity);
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<DataEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}