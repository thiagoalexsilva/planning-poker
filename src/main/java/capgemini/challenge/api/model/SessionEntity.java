package capgemini.challenge.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table
@Getter
@Setter
public class SessionEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "sessionId")
  private Long sessionId;
  @Column(name = "title")
  private String title;
  @Column(name = "deckFormatId")
  private Long deckFormatId;

  @OneToMany
  @JoinColumn(name = "userStoryId")
  private List<UserStoryEntity> userStories = List.of();

  @OneToMany
  @JoinColumn(name = "userId")
  private List<UserEntity> players = List.of();
}

