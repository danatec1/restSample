# restSample
위탁병원 json file test jar 만들기 (Commissioned Hospital JSON file test JAR creation)

## 개요 (Overview)
이 프로젝트는 위탁병원 데이터를 관리하기 위한 REST API 샘플 애플리케이션입니다.

This project is a REST API sample application for managing commissioned hospital data.

## 기능 (Features)
- 병원 목록 조회 (GET /api/hospitals)
- 특정 병원 조회 (GET /api/hospitals/{id})
- 새 병원 추가 (POST /api/hospitals)
- 병원 정보 수정 (PUT /api/hospitals/{id})
- 병원 삭제 (DELETE /api/hospitals/{id})

## 빌드 방법 (Build)
```bash
mvn clean package
```

## 실행 방법 (Run)
```bash
java -jar target/rest-sample-1.0.0.jar
```

또는 (or):
```bash
mvn spring-boot:run
```

## 테스트 (Test)
```bash
mvn test
```

## API 사용 예시 (API Usage Examples)

### 모든 병원 조회 (Get all hospitals)
```bash
curl http://localhost:8080/api/hospitals
```

### 특정 병원 조회 (Get hospital by ID)
```bash
curl http://localhost:8080/api/hospitals/1
```

### 새 병원 추가 (Create new hospital)
```bash
curl -X POST http://localhost:8080/api/hospitals \
  -H "Content-Type: application/json" \
  -d '{
    "name": "새병원",
    "address": "서울특별시 중구",
    "phone": "02-1234-5678",
    "type": "종합병원",
    "capacity": 1000
  }'
```

### 병원 정보 수정 (Update hospital)
```bash
curl -X PUT http://localhost:8080/api/hospitals/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "수정된병원",
    "address": "서울특별시 강남구",
    "phone": "02-9876-5432",
    "type": "종합병원",
    "capacity": 1500
  }'
```

### 병원 삭제 (Delete hospital)
```bash
curl -X DELETE http://localhost:8080/api/hospitals/1
```

## 기술 스택 (Technology Stack)
- Java 11
- Spring Boot 2.7.18
- Maven
- JUnit 5
