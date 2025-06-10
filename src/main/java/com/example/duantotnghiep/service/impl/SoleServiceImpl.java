package com.example.duantotnghiep.service.impl;

import com.example.duantotnghiep.model.Role;
import com.example.duantotnghiep.repository.SoleRepository;
import com.example.duantotnghiep.service.SoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoleServiceImpl implements SoleService {
    private final SoleRepository soleRepository;

    @Override
    public List<Role> getAll() {
        return soleRepository.findByStatus();
    }

    @Override
    public Role them(String name) {
        Role b = new Role();
        b.setSoleCode(generateCode());
        b.setSoleName(name);
        b.setStatus(1);
        b.setCreatedDate(new Date());
        b.setCreatedBy("admin");
        Role saved = soleRepository.save(b);
        return saved;
    }

    @Override
    public Role sua(Long id, String name) {
        Role b = soleRepository.findById(id).orElse(null);
        b.setSoleName(name);
        b.setUpdatedBy("admin");
        b.setUpdatedDate(new Date());
        Role updated= soleRepository.save(b);
        return updated;
    }

    @Override
    public void xoa(Long id) {
        Role b = soleRepository.findById(id).orElse(null);
        b.setUpdatedDate(new Date());
        b.setStatus(0);
        soleRepository.save(b);
    }

    private String generateCode() {
        String prefix = "SOLE-";
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String randomPart = String.format("%04d", (int) (Math.random() * 10000));
        return prefix + datePart + "-" + randomPart;
    }
}
