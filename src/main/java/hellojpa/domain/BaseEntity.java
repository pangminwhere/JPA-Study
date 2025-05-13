package hellojpa.domain;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/*
@MappedSuperClass : 엔티티 별 중복되는 속성(id, name 등등)을 중복하지 않는 방법의 어노테이션
- 이 클래스를 상속 받는 엔티티는 DB에 공통되는 속성을 가짐
- 당연히 상속관계 매핑, 엔티티, 테이블과의 매핑은 아니다
- 부모 클래스를 상속받는 자식 클래스에 매핑 정보만 제공
- 조회, 검색 불가 (em.find(BaseEntity) 불가)
- 직접 생성해서 사용할 일이 없으므로 추상 클래스 권장
참고 : @Entity 클래스는 엔티티나 @MappedSuperClass로 지정한 클래스만 상속 가능
 */
@MappedSuperclass
public class BaseEntity {

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

}
