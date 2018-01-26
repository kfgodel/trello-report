package com.tenpines.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportadoDeTrello {

  private List<CardDeTrello> cards;

  public List<CardDeTrello> getCards() {
    return cards;
  }

  public void setCards(List<CardDeTrello> cards) {
    this.cards = cards;
  }
}
