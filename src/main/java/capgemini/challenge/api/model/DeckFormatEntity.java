package capgemini.challenge.api.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table
@Getter
@Setter
public class DeckFormatEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "deckFormatId")
  private Long deckFormatId;
  @Column(name = "name")
  private String name;
}

