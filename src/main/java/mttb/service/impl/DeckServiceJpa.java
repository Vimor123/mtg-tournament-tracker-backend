package mttb.service.impl;

import mttb.dao.*;
import mttb.domain.*;
import mttb.dto.CardindeckDTO;
import mttb.dto.LoginDTO;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckServiceJpa implements DeckService {

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private FormatRepository formatRepo;

    @Autowired
    private CardlegalityRepository cardlegalityRepo;

    @Autowired
    private DecklegalityRepository decklegalityRepo;

    @Autowired
    private CardindeckRepository cardindeckRepo;

    @Autowired
    private DecktypeRepository decktypeRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Deck> getAll() {
        List<Deck> allDecks = deckRepo.findAll();
        List<Deck> currentDecks = new ArrayList<>();
        for (Deck deck : allDecks) {
            if (deck.isInuse()) {
                currentDecks.add(deck);
            }
        }
        return currentDecks;
    }

    @Override
    public Deck getById(Long id) {
        Optional<Deck> deck = deckRepo.findById(id);
        if (deck.isEmpty()) {
            throw new EntityMissingException(Deck.class, id);
        }
        return deck.get();
    }

    @Override
    public List<Deck> getDecksForPlayerId(Long id) {
        Optional<Player> player = playerRepo.findById(id);
        if (player.isEmpty()) {
            throw new EntityMissingException(Player.class, player);
        }
        return deckRepo.findAllByPlayerAndInuse(player.get(), true);
    }

    @Override
    public Deck create(LoginDTO loginDTO, String namedeck, Long iddecktype, List<CardindeckDTO> cards) {
        Optional<Player> player = playerRepo.findByUsername(loginDTO.getUsername());
        if (player.isEmpty()) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), player.get().getPasswordhash())) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!deckRepo.findByPlayerAndNamedeckAndInuse(player.get(), namedeck, true).isEmpty()) {
            throw new RequestDeniedException("Deck " + namedeck + " already exists");
        }

        Deck deck = new Deck();

        deck.setNamedeck(namedeck);
        deck.setPlayer(player.get());

        Optional<Decktype> decktype = decktypeRepo.findById(iddecktype);
        if (decktype.isEmpty()) {
            throw new EntityMissingException(Decktype.class, iddecktype);
        }
        deck.setDecktype(decktype.get());

        deck.setInuse(false);

        Deck newDeck = deckRepo.save(deck);

        int mainDeckCards = 0;
        int sideboardCards = 0;

        Set<Cardindeck> cardsindeck = new HashSet<>();

        HashMap<Long, Integer> cardquantities = new HashMap<>();

        Set<Color> deckcolors = new HashSet<>();

        HashMap<Format, Boolean> legalities = new HashMap<>();

        for (Format format : formatRepo.findAll()) {
            legalities.put(format, true);
        }

        for (CardindeckDTO cardindeckDTO : cards) {
            Optional<Card> card = cardRepo.findByNamecard(cardindeckDTO.getCardname());
            if (card.isEmpty()) {
                throw new EntityMissingException(Card.class, cardindeckDTO.getCardname());
            }

            if (cardquantities.containsKey(card.get().getIdcard())) {
                cardquantities.replace(card.get().getIdcard(), cardquantities.get(card.get().getIdcard()) + cardindeckDTO.getQuantity());
            } else {
                cardquantities.put(card.get().getIdcard(), cardindeckDTO.getQuantity());
            }

            if (cardindeckDTO.isInsideboard()) {
                sideboardCards += cardindeckDTO.getQuantity();
            } else {
                mainDeckCards += cardindeckDTO.getQuantity();
            }

            for (Color color : card.get().getCardcolors()) {
                if (!deckcolors.contains(color)) {
                    deckcolors.add(color);
                }
            }

            CardindeckKey id = new CardindeckKey(newDeck.getIddeck(), card.get().getIdcard());
            Cardindeck cardindeck = new Cardindeck(id, newDeck, card.get(), cardindeckDTO.getQuantity(), cardindeckDTO.isInsideboard());
            cardsindeck.add(cardindeck);

            for (Map.Entry<Format, Boolean> entry : legalities.entrySet()) {
                CardlegalityKey idcardlegality = new CardlegalityKey(entry.getKey().getIdformat(), card.get().getIdcard());
                Optional<Cardlegality> cardlegality = cardlegalityRepo.findById(idcardlegality);
                if (!cardlegality.get().isLegal()) {
                    legalities.replace(entry.getKey(), false);
                }
            }
        }

        if (mainDeckCards < 60) {
            throw new RequestDeniedException("A deck can't have less than 60 cards");
        }
        if (sideboardCards > 15) {
            throw new RequestDeniedException("A deck can't have more than 15 sideboard cards");
        }

        for (Map.Entry<Long, Integer> entry : cardquantities.entrySet()) {
            if (entry.getValue() > 4) {
                Card card = cardRepo.findById(entry.getKey()).get();
                if (!card.getNamecard().equals("Plains") && !card.getNamecard().equals("Island") &&
                    !card.getNamecard().equals("Swamp") && !card.getNamecard().equals("Mountain") &&
                    !card.getNamecard().equals("Forest")) {
                    throw new RequestDeniedException("A deck can't have more than 4 copies of the same card (except basic lands)");
                }
            }
        }

        for (Map.Entry<Format, Boolean> entry : legalities.entrySet()) {
            DecklegalityKey iddecklegality = new DecklegalityKey(entry.getKey().getIdformat(), newDeck.getIddeck());
            Decklegality decklegality = new Decklegality(iddecklegality, entry.getKey(), newDeck, entry.getValue());
            decklegalityRepo.save(decklegality);
        }

        for (Cardindeck cardindeck : cardsindeck) {
            cardindeckRepo.save(cardindeck);
        }

        newDeck.setCardsindeck(cardsindeck);
        newDeck.setDeckcolors(deckcolors);

        newDeck.setInuse(true);

        deckRepo.save(newDeck);

        return newDeck;
    }

    @Override
    public Deck edit(LoginDTO loginDTO, String namedeck, Long iddecktype, List<CardindeckDTO> cards) {
        Optional<Player> player = playerRepo.findByUsername(loginDTO.getUsername());
        if (player.isEmpty()) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), player.get().getPasswordhash())) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        Optional<Deck> oldDeckCheck = deckRepo.findByPlayerAndNamedeckAndInuse(player.get(), namedeck, true);
        if (oldDeckCheck.isEmpty()) {
            throw new EntityMissingException(Deck.class, namedeck);
        }

        Deck oldDeck = oldDeckCheck.get();
        oldDeck.setInuse(false);
        deckRepo.save(oldDeck);

        Deck deck = new Deck();

        deck.setNamedeck(namedeck);
        deck.setPlayer(player.get());

        Optional<Decktype> decktype = decktypeRepo.findById(iddecktype);
        if (decktype.isEmpty()) {
            throw new EntityMissingException(Decktype.class, iddecktype);
        }
        deck.setDecktype(decktype.get());

        deck.setInuse(true);

        Deck newDeck = deckRepo.save(deck);

        int mainDeckCards = 0;
        int sideboardCards = 0;

        Set<Cardindeck> cardsindeck = new HashSet<>();

        HashMap<Long, Integer> cardquantities = new HashMap<>();

        Set<Color> deckcolors = new HashSet<>();

        HashMap<Format, Boolean> legalities = new HashMap<>();

        for (Format format : formatRepo.findAll()) {
            legalities.put(format, true);
        }

        for (CardindeckDTO cardindeckDTO : cards) {
            Optional<Card> card = cardRepo.findByNamecard(cardindeckDTO.getCardname());
            if (card.isEmpty()) {
                throw new EntityMissingException(Card.class, cardindeckDTO.getCardname());
            }

            if (cardquantities.containsKey(card.get().getIdcard())) {
                cardquantities.replace(card.get().getIdcard(), cardquantities.get(card.get().getIdcard()) + cardindeckDTO.getQuantity());
            } else {
                cardquantities.put(card.get().getIdcard(), cardindeckDTO.getQuantity());
            }

            if (cardindeckDTO.isInsideboard()) {
                sideboardCards += cardindeckDTO.getQuantity();
            } else {
                mainDeckCards += cardindeckDTO.getQuantity();
            }

            for (Color color : card.get().getCardcolors()) {
                if (!deckcolors.contains(color)) {
                    deckcolors.add(color);
                }
            }

            CardindeckKey id = new CardindeckKey(newDeck.getIddeck(), card.get().getIdcard());
            Cardindeck cardindeck = new Cardindeck(id, newDeck, card.get(), cardindeckDTO.getQuantity(), cardindeckDTO.isInsideboard());
            cardsindeck.add(cardindeck);

            for (Map.Entry<Format, Boolean> entry : legalities.entrySet()) {
                CardlegalityKey idcardlegality = new CardlegalityKey(entry.getKey().getIdformat(), card.get().getIdcard());
                Optional<Cardlegality> cardlegality = cardlegalityRepo.findById(idcardlegality);
                if (!cardlegality.get().isLegal()) {
                    legalities.replace(entry.getKey(), false);
                }
            }
        }

        if (mainDeckCards < 60) {
            throw new RequestDeniedException("A deck can't have less than 60 cards");
        }
        if (sideboardCards > 15) {
            throw new RequestDeniedException("A deck can't have more than 15 sideboard cards");
        }

        for (Map.Entry<Long, Integer> entry : cardquantities.entrySet()) {
            if (entry.getValue() > 4) {
                Card card = cardRepo.findById(entry.getKey()).get();
                if (!card.getNamecard().equals("Plains") && !card.getNamecard().equals("Island") &&
                        !card.getNamecard().equals("Swamp") && !card.getNamecard().equals("Mountain") &&
                        !card.getNamecard().equals("Forest")) {
                    throw new RequestDeniedException("A deck can't have more than 4 copies of the same card (except basic lands)");
                }
            }
        }

        for (Map.Entry<Format, Boolean> entry : legalities.entrySet()) {
            DecklegalityKey iddecklegality = new DecklegalityKey(entry.getKey().getIdformat(), newDeck.getIddeck());
            Decklegality decklegality = new Decklegality(iddecklegality, entry.getKey(), newDeck, entry.getValue());
            decklegalityRepo.save(decklegality);
        }

        for (Cardindeck cardindeck : cardsindeck) {
            cardindeckRepo.save(cardindeck);
        }

        newDeck.setCardsindeck(cardsindeck);
        newDeck.setDeckcolors(deckcolors);

        deckRepo.save(newDeck);

        return newDeck;
    }

    @Override
    public void delete(Long id) {
        Optional<Deck> oldDeckCheck = deckRepo.findById(id);
        if (oldDeckCheck.isEmpty()) {
            throw new EntityMissingException(Deck.class, id);
        }

        Deck oldDeck = oldDeckCheck.get();
        oldDeck.setInuse(false);
        deckRepo.save(oldDeck);
    }

    @Override
    public List<Decktype> getAllTypes() {
        return decktypeRepo.findAll();
    }

    @Override
    public List<Deck> searchByDecktype(String decktype) {
        List<Deck> allDecks = deckRepo.findAll();
        List<Deck> matchingDecks = new ArrayList<>();

        for (Deck deck : allDecks) {
            if (deck.getDecktype().getNamedecktype().toLowerCase().contains(decktype.toLowerCase()) && deck.isInuse()) {
                matchingDecks.add(deck);
            }
        }

        return matchingDecks;
    }
}
