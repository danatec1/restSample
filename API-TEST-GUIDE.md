# JAR 파일 테스트 가이드

## 1. JAR 파일 실행

```bash
# 방법 1: 포그라운드 실행
java -jar target/demo-0.0.1-SNAPSHOT.jar

# 방법 2: 백그라운드 실행
java -jar target/demo-0.0.1-SNAPSHOT.jar &

# 방법 3: nohup으로 실행 (터미널 종료 후에도 계속 실행)
nohup java -jar target/demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

## 2. REST API 테스트

### 2.1 전체 병원 목록 조회
```bash
curl -X GET http://localhost:8080/api/hospitals
```

### 2.2 특정 ID의 병원 조회
```bash
curl -X GET http://localhost:8080/api/hospitals/1
```

### 2.3 새로운 병원 데이터 추가
```bash
curl -X POST http://localhost:8080/api/hospitals \
  -H "Content-Type: application/json" \
  -d '{
    "district": "서울",
    "facilityName": "테스트병원",
    "bedCount": 100,
    "departmentCount": 10,
    "phoneNumber": "02-1234-5678",
    "address": "서울시 강남구",
    "type": "병원",
    "detailAddress": "테헤란로 123",
    "longitude": 127.123,
    "latitude": 37.456,
    "contact": "02-1234-5678",
    "hospitalName": "테스트병원",
    "status": "위탁중"
  }'
```

### 2.4 병원 정보 수정
```bash
curl -X PUT http://localhost:8080/api/hospitals/1 \
  -H "Content-Type: application/json" \
  -d '{
    "district": "서울",
    "facilityName": "수정된병원",
    "bedCount": 150,
    "departmentCount": 15,
    "phoneNumber": "02-9999-9999",
    "address": "서울시 강남구",
    "type": "병원",
    "detailAddress": "테헤란로 456",
    "longitude": 127.123,
    "latitude": 37.456,
    "contact": "02-9999-9999",
    "hospitalName": "수정된병원",
    "status": "위탁완료"
  }'
```

### 2.5 병원 데이터 삭제
```bash
curl -X DELETE http://localhost:8080/api/hospitals/1
```

## 3. JSON 포맷으로 보기 (jq 사용)

```bash
# jq가 설치되어 있다면
curl -s http://localhost:8080/api/hospitals | jq

# 예쁘게 색상과 함께 출력
curl -s http://localhost:8080/api/hospitals | jq '.'

# 특정 필드만 추출
curl -s http://localhost:8080/api/hospitals | jq '.[].hospitalName'
```

## 4. 브라우저에서 테스트

브라우저를 열고 다음 URL로 접속:
- http://localhost:8080/api/hospitals
- http://localhost:8080/api/hospitals/1

## 5. Postman이나 Insomnia로 테스트

1. Postman 또는 Insomnia 설치
2. 새 요청 생성
3. 메서드 선택 (GET, POST, PUT, DELETE)
4. URL 입력: `http://localhost:8080/api/hospitals`
5. POST/PUT의 경우 Body에 JSON 데이터 입력
6. Send 클릭

## 6. 애플리케이션 종료

```bash
# 프로세스 찾기
ps aux | grep demo-0.0.1-SNAPSHOT.jar

# 프로세스 종료
kill [PID]

# 또는 강제 종료
kill -9 [PID]

# 백그라운드 프로세스 확인 및 종료
jobs
fg %1  # 포그라운드로 가져온 후 Ctrl+C
```

## 7. 로그 확인

```bash
# 실행 중인 애플리케이션 로그 보기 (nohup 사용시)
tail -f app.log

# 실시간 로그 확인
tail -f nohup.out
```

## 8. 데이터베이스 직접 확인

```bash
mysql -u root -pAdmin1 testdb -e "SELECT * FROM 위탁병원현황 LIMIT 10"
```

## 9. Health Check

Spring Boot Actuator를 통한 상태 확인:
```bash
curl http://localhost:8080/actuator/health
```

## 10. 포트 변경하여 실행

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=9090
```

## 주의사항

1. **MySQL이 실행 중이어야 합니다**
   ```bash
   # MySQL 상태 확인
   mysql.server status
   
   # MySQL 시작
   mysql.server start
   ```

2. **포트 8080이 사용 가능해야 합니다**
   ```bash
   # 포트 확인
   lsof -i :8080
   ```

3. **데이터베이스 연결 정보가 정확해야 합니다**
   - 데이터베이스: testdb
   - 사용자: root
   - 비밀번호: Admin1
