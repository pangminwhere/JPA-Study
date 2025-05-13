package hellojpa.domain;

import jakarta.persistence.*;


/*
@DiscriminatorColumn : 하위 테이블의 종류가 무엇인지 알려주는 컬럼을 부모 테이블에 설정
(기본값은 DTYPE, name으로 명 설정 가능) *운영에서는 관례상 설정해주는 것이 좋음
@DiscriminatorValue : 자식 테이블에서 이름을 변경하기 위한 어노테이션
(기본값은 엔티티명, ex : @DiscriminatorValue("A")로 사용)

Item2 테이블을 만들고 Album, Book, Movie에 상속 extends 설정해도
JPA 전략에서 그냥 Item2에 하나에 테이블로 만드는 단일 테이블 전략으로 설정한다.
그래서, 부모 클래스에서 조인 전략을 설정할 수 있는 어노테이션 존재
@inheritance(strategy = InheritanceType.XXX)
JOINED : 조인 전략
- 장점 : 테이블 정규화, 외래키 참조 무결성 제약조건 활용 가능, 저장공간의 효율화
- 단점 : 조회 시 조인을 많이 사용해서 성능 저하, 조회 쿼리가 복잡함, 데이터 저장 시 INSERT SQL 2번 호출
실무적인 측면에서는 사실 단점들이 큰 문제가 되지 않음 * 조인 전략이 정석이라고 생각해야 함.
SINGLE_TABLE : 단일 테이블 전략 (@DiscriminatorColumn을 사용 안해도 DTYPE 자동 설정)
- 장점 : 조인이 필요 없어 조회 성능이 빠름, 조회 쿼리가 단순함
- 단점 : 자식 엔티티가 매핑한 컬럼은 모두 NULL 허용, 단일 테이블에 모든 것을 저장해서 너무 커져 조회 성능이 오히려 저하
TABLE_PER_CLASS : 구현 클래스마다 테이블 전략 (부모 클래스를 abstract로 수정, DB에 부모 클래스 생성 X)
- @DiscriminatorColumn 사용해도 설정 X (테이블이 다 달라 구분할 필요 X)
- 이 전략은 절대 쓰면 안되는 전략!!!! (변경에서의 관점에서 매우 안좋음, 컬럼 추가될 때 치명적)
모든 구현 테이블을 다 union all 후 전부 검색하기 때문에 성능 저하 이슈(비효율적) 발생
 */
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public class Item2 {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
