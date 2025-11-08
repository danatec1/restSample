package com.hospital.restsample.controller;

import com.hospital.restsample.model.Hospital;
import com.hospital.restsample.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HospitalController.class)
class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalService hospitalService;

    @Test
    void getAllHospitals_ShouldReturnListOfHospitals() throws Exception {
        Hospital hospital1 = new Hospital(1L, "서울대학교병원", "서울특별시 종로구", "02-2072-2114", "종합병원", 1800);
        Hospital hospital2 = new Hospital(2L, "삼성서울병원", "서울특별시 강남구", "02-3410-2114", "종합병원", 2000);

        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital1, hospital2));

        mockMvc.perform(get("/api/hospitals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("서울대학교병원"))
                .andExpect(jsonPath("$[1].name").value("삼성서울병원"));
    }

    @Test
    void getHospitalById_WhenExists_ShouldReturnHospital() throws Exception {
        Hospital hospital = new Hospital(1L, "서울대학교병원", "서울특별시 종로구", "02-2072-2114", "종합병원", 1800);

        when(hospitalService.getHospitalById(1L)).thenReturn(Optional.of(hospital));

        mockMvc.perform(get("/api/hospitals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울대학교병원"))
                .andExpect(jsonPath("$.capacity").value(1800));
    }

    @Test
    void getHospitalById_WhenNotExists_ShouldReturn404() throws Exception {
        when(hospitalService.getHospitalById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/hospitals/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createHospital_ShouldReturnCreatedHospital() throws Exception {
        Hospital hospital = new Hospital(1L, "신규병원", "서울특별시", "02-1234-5678", "병원", 500);

        when(hospitalService.createHospital(any(Hospital.class))).thenReturn(hospital);

        mockMvc.perform(post("/api/hospitals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"신규병원\",\"address\":\"서울특별시\",\"phone\":\"02-1234-5678\",\"type\":\"병원\",\"capacity\":500}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("신규병원"));
    }

    @Test
    void updateHospital_WhenExists_ShouldReturnUpdatedHospital() throws Exception {
        Hospital hospital = new Hospital(1L, "수정병원", "서울특별시", "02-1234-5678", "병원", 500);

        when(hospitalService.updateHospital(eq(1L), any(Hospital.class))).thenReturn(Optional.of(hospital));

        mockMvc.perform(put("/api/hospitals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"수정병원\",\"address\":\"서울특별시\",\"phone\":\"02-1234-5678\",\"type\":\"병원\",\"capacity\":500}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("수정병원"));
    }

    @Test
    void deleteHospital_WhenExists_ShouldReturn204() throws Exception {
        when(hospitalService.deleteHospital(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/hospitals/1"))
                .andExpect(status().isNoContent());
    }
}
