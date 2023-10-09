package capgemini.challenge.api.service.interfaces;

import capgemini.api.openapi.dto.DeckFormat;

import java.util.List;

public interface IDeckFormatService {
    List<DeckFormat> getDeckFormats();
}
