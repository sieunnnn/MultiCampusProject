package multi.second.project.domain.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;



    @Entity
    @DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
    @DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Table(name = "friends")
    public class Friend {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private int age;

        private String email;

        public void setId(Long id) {
        }

        // Getter, Setter, Constructor 생략
    }

