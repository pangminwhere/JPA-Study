package hellojpa.domain;

import jakarta.persistence.*;


/*
Item2 테이블을 만들고 Album, Book, Movie에 상속 extends 설정해도
JPA 전략에서 그냥 Item2에 하나에 테이블로 만드는 단일 테이블 전략으로 설정한다.
그래서, 부모 클래스에서 조인 전략을 설정할 수 있는 어노테이션 존재
@inheritance(strategy = InheritanceType.XXX)
JOINED : 조인 전략
SINGLE_TABLE : 단일 테이블 전략
TABLE_PER_CLASS : 구현 클래스마다 테이블 전략
 */
@Entity
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
