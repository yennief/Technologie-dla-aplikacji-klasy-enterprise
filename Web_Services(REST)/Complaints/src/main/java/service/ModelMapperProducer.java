/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped; 
import jakarta.enterprise.inject.Produces; 
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

/**
 *
 * @author justyna
 */
public class ModelMapperProducer {
    
    @Produces
    @ApplicationScoped
    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
    
}
