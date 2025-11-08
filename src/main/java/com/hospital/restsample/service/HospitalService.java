package com.hospital.restsample.service;

import com.hospital.restsample.model.Hospital;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 위탁병원 (Commissioned Hospital) Service
 */
@Service
public class HospitalService {
    private final List<Hospital> hospitals = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public HospitalService() {
        // Initialize with sample data
        hospitals.add(new Hospital(counter.incrementAndGet(), "서울대학교병원", "서울특별시 종로구", "02-2072-2114", "종합병원", 1800));
        hospitals.add(new Hospital(counter.incrementAndGet(), "삼성서울병원", "서울특별시 강남구", "02-3410-2114", "종합병원", 2000));
        hospitals.add(new Hospital(counter.incrementAndGet(), "세브란스병원", "서울특별시 서대문구", "02-2228-5800", "종합병원", 2400));
    }

    public List<Hospital> getAllHospitals() {
        return new ArrayList<>(hospitals);
    }

    public Optional<Hospital> getHospitalById(Long id) {
        return hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
    }

    public Hospital createHospital(Hospital hospital) {
        hospital.setId(counter.incrementAndGet());
        hospitals.add(hospital);
        return hospital;
    }

    public Optional<Hospital> updateHospital(Long id, Hospital hospitalDetails) {
        return getHospitalById(id).map(hospital -> {
            hospital.setName(hospitalDetails.getName());
            hospital.setAddress(hospitalDetails.getAddress());
            hospital.setPhone(hospitalDetails.getPhone());
            hospital.setType(hospitalDetails.getType());
            hospital.setCapacity(hospitalDetails.getCapacity());
            return hospital;
        });
    }

    public boolean deleteHospital(Long id) {
        return hospitals.removeIf(h -> h.getId().equals(id));
    }
}
