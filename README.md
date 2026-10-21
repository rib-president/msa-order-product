# msa-order-product

주문과 상품을 독립 서비스로 분리하고, Eureka 서비스 디스커버리와 Spring Cloud Gateway로 연결한 MSA 구조 예제입니다.
서비스 간 재고 처리는 Kafka 이벤트로 주고받고, 재고 차감 동시성은 Redisson 분산 락으로 제어합니다.

## 아키텍처

```
Client → eureka-gateway(:9001) ─┬→ app-order   ─┐
                                └→ app-product ─┤  Kafka (order-events / product-events)
                                                │  Redis (상품 정보 캐시, 분산 락)
         eureka-server(:9000) ← 서비스 등록, 조회  ┘
```

## 모듈 구성

| 모듈 | 역할 |
| --- | --- |
| eureka-server | 서비스 레지스트리 |
| eureka-gateway | 단일 진입점, `/app-order/**`, `/app-product/**` 라우팅 |
| app-order | 주문 생성, 수정, 취소, 조회 |
| app-product | 상품 관리, 재고 차감과 원복 |
| common-core | 공통 응답, 예외 처리, 이벤트 정의, Kafka/Redis 설정 |
| infra | Kafka, Zookeeper Docker Compose |

## 서비스 간 통신

- **주문 생성**: 주문을 `PENDING`으로 저장하고 `ORDER_CREATED` 이벤트를 `order-events`에 발행
- **재고 처리**: 상품 서비스가 이벤트를 받아 분산 락 안에서 재고를 확인하고 차감한 뒤 `STOCK_UPDATED` 또는 `STOCK_FAILED`를 `product-events`에 발행
- **주문 확정**: 주문 서비스가 결과를 받아 `CONFIRMED` 또는 `CANCELED`로 상태 변경
- **주문 수정**: 확정된 주문만 수정 가능. 재고가 부족하면 `STOCK_ROLLBACK`을 받아 이전 수량으로 되돌림
- **주문 취소**: 확정된 주문이면 `ORDER_CANCELLED`를 발행해 재고 원복
- **상품 정보 조회**: 주문 서비스는 Redis에 캐시된 상품 정보를 먼저 읽고, 없으면 게이트웨이를 통해 상품 서비스에 캐시 적재를 요청 (RestTemplate 동기 호출)

## 기술 스택

Java 11, Spring Boot 2.5, Spring Cloud 2020.0 (Eureka, Gateway), JPA, QueryDSL, MySQL, Redis (Redisson), Kafka, Maven

## 실행 방법

```bash
# 1. Kafka, Zookeeper 실행 (MySQL, Redis는 로컬에 별도 실행)
docker-compose -f infra/docker-compose.yml up -d

# 2. MySQL 데이터베이스 준비: msa_order, msa_product

# 3. 빌드
mvn package -DskipTests
```

실행 순서: `eureka-server` → `eureka-gateway` → `app-product`, `app-order`

## 참고

- [docs/notes.md](docs/notes.md): 구현하면서 정리한 Spring 개념 노트
