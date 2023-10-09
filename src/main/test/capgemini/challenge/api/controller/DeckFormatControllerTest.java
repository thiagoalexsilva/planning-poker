package capgemini.challenge.api.controller;

import capgemini.api.openapi.dto.DeckFormat;
import capgemini.challenge.api.service.DeckFormatService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class DeckFormatControllerTest {

    @Mock
    private DeckFormatService deckFormatService;
    @InjectMocks
    private DeckFormatController deckFormatController;

    @Test
    void getDeckFormats() {
        List<DeckFormat> deckFormatsMock = List.of(new DeckFormat());

        Mockito.when(this.deckFormatService.getDeckFormats()).thenReturn(deckFormatsMock);

        final var response = this.deckFormatController.getDeckFormats();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().isEmpty());
    }
}