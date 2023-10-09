package capgemini.challenge.api.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table
@Getter
@Setter
public class UserEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "userId")
  private Long userId;
  @Column(name = "name")
  private String name;
  @Column(name = "nickname")
  private String nickname;
}

