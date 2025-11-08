package io.reflectoring.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "위탁병원현황")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WitakByungwonHyunhwang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "연번")
    private Integer serialNumber;
    
    @Column(name = "시군구")
    private String district;
    
    @Column(name = "요양기관명")
    private String facilityName;
    
    @Column(name = "병상수")
    private Integer bedCount;
    
    @Column(name = "진료과수")
    private Integer departmentCount;
    
    @Column(name = "전화번호")
    private String phoneNumber;
    
    @Column(name = "주소")
    private String address;
    
    @Column(name = "종별")
    private String type;
    
    @Column(name = "상세주소")
    private String detailAddress;
    
    @Column(name = "경도")
    private Double longitude;
    
    @Column(name = "위도")
    private Double latitude;
    
    @Column(name = "연락처")
    private String contact;
    
    @Column(name = "병원명")
    private String hospitalName;
    
    @Column(name = "위탁상태")
    private String status;
}
