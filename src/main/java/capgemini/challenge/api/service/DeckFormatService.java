package capgemini.challenge.api.service;

import capgemini.api.openapi.dto.DeckFormat;
import capgemini.challenge.api.mapper.MapStructMapper;
import capgemini.challenge.api.model.DeckFormatEntity;
import capgemini.challenge.api.repository.IDeckFormatRepository;
import capgemini.challenge.api.service.interfaces.IDeckFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckFormatService implements IDeckFormatService {

    private final IDeckFormatRepository deckFormatRepository;
    private final MapStructMapper mapper;

    @Autowired
    public DeckFormatService(final IDeckFormatRepository deckFormatRepository,
                             final MapStructMapper mapper){
        this.deckFormatRepository = deckFormatRepository;
        this.mapper = mapper;
    }

    @Override
    public List<DeckFormat> getDeckFormats() {
        ArrayList<DeckFormat> deckFormats = new ArrayList<>();
        List<DeckFormatEntity> deckFormatEntities = this.deckFormatRepository.findAll();

        if(!deckFormatEntities.isEmpty()) {
            deckFormatEntities
                    .forEach(deckFormatEntity -> deckFormats.add(mapper.deckFormatEntityToDeckFormat(deckFormatEntity)));
        }

        return deckFormats;
    }
}
