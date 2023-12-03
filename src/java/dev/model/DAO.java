/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.model;

import java.util.ArrayList;

public interface DAO<Type> {
   public Type get(Long id);   
   public ArrayList<Type> getAll();
   public void insert(Type type);
   public void update(Type type);
   public void delete(Long id);
}
