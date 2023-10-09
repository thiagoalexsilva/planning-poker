package capgemini.challenge.api.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table
@Getter
@Setter
public class UserStoryEntity{
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "userStoryId")
  private Long userStoryId;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "storyPoints")
  private Long storyPoints;
  @Column(name = "status")
  private String status;
}

