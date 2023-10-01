package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder // 부모의 속성을 사용하기 위해서 적용
@Data
@EqualsAndHashCode(callSuper = true) // 객체 비교를 할때 사용 (부모에 있는 값까지 비교 하려고 옵션 )
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

}
