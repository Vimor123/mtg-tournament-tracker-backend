package mttb.rest;

import mttb.domain.Card;
import mttb.domain.Cardindeck;
import mttb.domain.Deck;
import mttb.domain.Decktype;
import mttb.dto.CardOutDTO;
import mttb.dto.DeckInDTO;
import mttb.dto.DeckOutDTO;
import mttb.dto.DecktypeDTO;
import mttb.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping("")
    public List<DeckOutDTO> getAll() {
        List<Deck> decks = deckService.getAll();
        List<DeckOutDTO> outDTOs = new ArrayList<>();

        for (Deck deck : decks) {
            DeckOutDTO outDTO = new DeckOutDTO();
            outDTO.setIddeck(deck.getIddeck());
            outDTO.setNameDeck(deck.getNamedeck());
            outDTO.setPlayer(deck.getPlayer());
            outDTO.setDecktype(deck.getDecktype());
            outDTO.setColors(deck.getDeckcolors().stream().toList());

            List<CardOutDTO> cards = new ArrayList<>();
            for (Cardindeck cardindeck : deck.getCardsindeck()) {
                cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
            }
            outDTO.setCards(cards);
            outDTOs.add(outDTO);
        }
        return outDTOs;
    }

    @GetMapping("/{id}")
    public DeckOutDTO getById(@PathVariable Long id) {
        Deck deck = deckService.getById(id);
        DeckOutDTO outDTO = new DeckOutDTO();
        outDTO.setIddeck(deck.getIddeck());
        outDTO.setNameDeck(deck.getNamedeck());
        outDTO.setPlayer(deck.getPlayer());
        outDTO.setDecktype(deck.getDecktype());
        outDTO.setColors(deck.getDeckcolors().stream().toList());

        List<CardOutDTO> cards = new ArrayList<>();
        for (Cardindeck cardindeck : deck.getCardsindeck()) {
            cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
        }
        outDTO.setCards(cards);
        return outDTO;
    }

    @GetMapping("/player/{id}")
    public List<DeckOutDTO> getDecksForPlayer(@PathVariable Long id) {
        List<Deck> decks = deckService.getDecksForPlayerId(id);
        List<DeckOutDTO> outDTOs = new ArrayList<>();

        for (Deck deck : decks) {
            DeckOutDTO outDTO = new DeckOutDTO();
            outDTO.setIddeck(deck.getIddeck());
            outDTO.setNameDeck(deck.getNamedeck());
            outDTO.setPlayer(deck.getPlayer());
            outDTO.setDecktype(deck.getDecktype());
            outDTO.setColors(deck.getDeckcolors().stream().toList());

            List<CardOutDTO> cards = new ArrayList<>();
            for (Cardindeck cardindeck : deck.getCardsindeck()) {
                cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
            }
            outDTO.setCards(cards);
            outDTOs.add(outDTO);
        }
        return outDTOs;
    }

    @PostMapping("/create")
    public DeckOutDTO create(@RequestBody DeckInDTO dto) {
        Deck deck = deckService.create(dto.getLogin(), dto.getNamedeck(), dto.getIddecktype(), dto.getCards());
        DeckOutDTO outDTO = new DeckOutDTO();
        outDTO.setIddeck(deck.getIddeck());
        outDTO.setNameDeck(deck.getNamedeck());
        outDTO.setPlayer(deck.getPlayer());
        outDTO.setDecktype(deck.getDecktype());
        outDTO.setColors(deck.getDeckcolors().stream().toList());

        List<CardOutDTO> cards = new ArrayList<>();
        for (Cardindeck cardindeck : deck.getCardsindeck()) {
            cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
        }
        outDTO.setCards(cards);
        return outDTO;
    }

    @PostMapping("/edit")
    public DeckOutDTO edit(@RequestBody DeckInDTO dto) {
        Deck deck = deckService.edit(dto.getLogin(), dto.getNamedeck(), dto.getIddecktype(), dto.getCards());
        DeckOutDTO outDTO = new DeckOutDTO();
        outDTO.setIddeck(deck.getIddeck());
        outDTO.setNameDeck(deck.getNamedeck());
        outDTO.setPlayer(deck.getPlayer());
        outDTO.setDecktype(deck.getDecktype());
        outDTO.setColors(deck.getDeckcolors().stream().toList());

        List<CardOutDTO> cards = new ArrayList<>();
        for (Cardindeck cardindeck : deck.getCardsindeck()) {
            cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
        }
        outDTO.setCards(cards);
        return outDTO;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        deckService.delete(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @GetMapping("/types")
    public List<Decktype> getAllTypes() {
        return deckService.getAllTypes();
    }

    @GetMapping("/search/{decktype}")
    public List<DeckOutDTO> searchByDecktype(@PathVariable String decktype) {
        List<Deck> decks = deckService.searchByDecktype(decktype);
        List<DeckOutDTO> outDTOs = new ArrayList<>();

        for (Deck deck : decks) {
            DeckOutDTO outDTO = new DeckOutDTO();
            outDTO.setIddeck(deck.getIddeck());
            outDTO.setNameDeck(deck.getNamedeck());
            outDTO.setPlayer(deck.getPlayer());
            outDTO.setDecktype(deck.getDecktype());
            outDTO.setColors(deck.getDeckcolors().stream().toList());

            List<CardOutDTO> cards = new ArrayList<>();
            for (Cardindeck cardindeck : deck.getCardsindeck()) {
                cards.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
            }
            outDTO.setCards(cards);
            outDTOs.add(outDTO);
        }
        return outDTOs;
    }
}
