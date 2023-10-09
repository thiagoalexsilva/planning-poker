package capgemini.challenge.api.controller;

import capgemini.api.openapi.api.DeckFormatApi;
import capgemini.api.openapi.dto.DeckFormat;
import capgemini.challenge.api.service.interfaces.IDeckFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeckFormatController implements DeckFormatApi {

    private IDeckFormatService deckFormatService;

    @Autowired
    public DeckFormatController (final IDeckFormatService deckFormatService){
        this.deckFormatService = deckFormatService;
    }

    @Override
    public ResponseEntity<List<DeckFormat>> getDeckFormats() {
        return ResponseEntity.ok(this.deckFormatService.getDeckFormats());
    }
}
