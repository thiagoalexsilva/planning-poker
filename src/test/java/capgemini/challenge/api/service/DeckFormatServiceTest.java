package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.DeckFormat;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.mapper.MapStructMapperImpl;
import capgemini.challenge.api.model.DeckFormatEntity;
import capgemini.challenge.api.repository.IDeckFormatRepository;
import capgemini.challenge.api.service.DeckFormatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class DeckFormatServiceTest {

    @Mock
    private IDeckFormatRepository deckFormatRepository;
    @Mock
    private MapStructMapper mapper;
    @InjectMocks
    private DeckFormatService deckFormatService;

    @Test
    void getDeckFormats() {
        final var deckEntity = new DeckFormatEntity();
        List<DeckFormatEntity> deckFormatEntities = List.of(deckEntity);
        final var deck = new DeckFormat().deckFormatId(1L);

        Mockito.when(this.deckFormatRepository.findAll()).thenReturn(deckFormatEntities);
        Mockito.when(this.mapper.deckFormatEntityToDeckFormat(Mockito.any(DeckFormatEntity.class))).thenReturn(deck);

        final var decks = deckFormatService.getDeckFormats();

        Assertions.assertFalse(decks.isEmpty());
    }

    @Test
    void getDeckFormats_NotDecks_ReturnsEmptyList() {
        Mockito.when(this.deckFormatRepository.findAll()).thenReturn(List.of());

        final var decks = deckFormatService.getDeckFormats();

        Assertions.assertTrue(decks.isEmpty());
    }
}