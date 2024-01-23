package com.demkiv.hospitalmanager.service;

import com.demkiv.hospitalmanager.exception.exceptions.ElementNotFoundException;
import com.demkiv.hospitalmanager.model.Staff;
import com.demkiv.hospitalmanager.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public Staff add(Staff staff) {

        return staffRepository.save(staff);
    }

    public Staff findById(Long id) {
        return staffRepository
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id.toString()));
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff update(Staff staff) {
        return staffRepository.save(staff);
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
