package com.tenpines.trello;

import ar.com.kfgodel.nary.api.Nary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExportadoDeTrello {

  private List<CardDeTrello> cards;

  private List<ListaDeTrello> lists;

  public List<ListaDeTrello> getLists() {
    if (cards == null) {
      cards = new ArrayList<>();
    }
    return lists;
  }

  public void setLists(List<ListaDeTrello> lists) {
    this.lists = lists;
  }

  public List<CardDeTrello> getCards() {
    return cards;
  }

  public void setCards(List<CardDeTrello> cards) {
    this.cards = cards;
  }

  public void completarDatosCruzados() {
    cards.forEach(card->{
      card.setLista(getListaConId(card.getIdList()));
    });
  }

  private ListaDeTrello getListaConId(String idList) {
    return Nary.create(getLists())
    .filterNary(lista -> lista.getId().equals(idList))
    .orElseThrow(()-> new RuntimeException("No existe la lista con id["+idList+"] y una card lo referencia"));
  }

}
