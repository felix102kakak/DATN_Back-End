package com.example.duantotnghiep.controller;

import com.example.duantotnghiep.model.Role;
import com.example.duantotnghiep.service.SoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sole")
public class SoleController {
    @Autowired
    private SoleService service;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<Role>> getAll(){
        List<Role> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    // Thêm loại đế
    @PostMapping
    public ResponseEntity<Role> addSole(@RequestParam String name) {
        Role newRole = service.them(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }

    // Cập nhật loại đế
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateSole(@PathVariable Long id, @RequestParam String name) {
        Role updatedRole = service.sua(id, name);
        if (updatedRole == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRole);
    }

    // Xóa loại đế
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSole(@PathVariable Long id) {
        service.xoa(id);
        return ResponseEntity.noContent().build();
    }
}
