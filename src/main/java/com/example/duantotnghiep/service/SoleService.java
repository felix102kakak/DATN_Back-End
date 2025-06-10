package com.example.duantotnghiep.service;

import com.example.duantotnghiep.model.Role;

import java.util.List;

public interface SoleService {
    List<Role> getAll();
    Role them(String name);
    Role sua(Long id, String name);
    void xoa(Long id);
}
