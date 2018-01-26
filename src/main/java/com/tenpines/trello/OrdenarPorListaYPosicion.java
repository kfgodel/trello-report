package com.tenpines.trello;

import java.util.Comparator;

public class OrdenarPorListaYPosicion implements Comparator<CardDeTrello> {
  @Override
  public int compare(CardDeTrello o1, CardDeTrello o2) {
    int listaComparada = o1.getNombreDeLista().compareTo(o2.getNombreDeLista());
    if(listaComparada != 0){
      return listaComparada;
    }
    int posicionComparada = Long.compare(o1.getPos(), o2.getPos());
    return posicionComparada;
  }

  public static OrdenarPorListaYPosicion create() {
    OrdenarPorListaYPosicion ordenarPorListaYPosicion = new OrdenarPorListaYPosicion();
    return ordenarPorListaYPosicion;
  }

}
