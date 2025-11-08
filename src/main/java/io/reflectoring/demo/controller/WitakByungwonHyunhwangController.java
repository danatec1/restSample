package io.reflectoring.demo.controller;

import io.reflectoring.demo.entity.WitakByungwonHyunhwang;
import io.reflectoring.demo.repository.WitakByungwonHyunhwangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class WitakByungwonHyunhwangController {
    
    @Autowired
    private WitakByungwonHyunhwangRepository repository;
    
    // 모든 위탁병원현황 조회
    @GetMapping
    public ResponseEntity<List<WitakByungwonHyunhwang>> getAllHospitals() {
        List<WitakByungwonHyunhwang> hospitals = repository.findAll();
        return ResponseEntity.ok(hospitals);
    }
    
    // ID로 특정 위탁병원현황 조회
    @GetMapping("/{id}")
    public ResponseEntity<WitakByungwonHyunhwang> getHospitalById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // 새로운 위탁병원현황 추가
    @PostMapping
    public ResponseEntity<WitakByungwonHyunhwang> createHospital(@RequestBody WitakByungwonHyunhwang hospital) {
        WitakByungwonHyunhwang savedHospital = repository.save(hospital);
        return ResponseEntity.ok(savedHospital);
    }
    
    // 위탁병원현황 수정
    @PutMapping("/{id}")
    public ResponseEntity<WitakByungwonHyunhwang> updateHospital(@PathVariable Integer id, @RequestBody WitakByungwonHyunhwang hospital) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        hospital.setSerialNumber(id);
        WitakByungwonHyunhwang updatedHospital = repository.save(hospital);
        return ResponseEntity.ok(updatedHospital);
    }
    
    // 위탁병원현황 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
