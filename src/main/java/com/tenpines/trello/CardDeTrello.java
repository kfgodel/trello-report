package com.tenpines.trello;

import ar.com.kfgodel.nary.api.Nary;
import ar.com.kfgodel.nary.api.optionals.Optional;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDeTrello {

  private Boolean closed;
  private String idList;
  private String idShort;
  private String desc;
  private String name;
  private List<PluginDataDeTrello> pluginData;
  private ListaDeTrello lista;
  private Long pos;
  private List<LabelDeTrello> labels;

  public List<LabelDeTrello> getLabels() {
    if (labels == null) {
      labels = new ArrayList<>();
    }
    return labels;
  }

  public void setLabels(List<LabelDeTrello> labels) {
    this.labels = labels;
  }

  public Long getPos() {
    return pos;
  }

  public void setPos(Long pos) {
    this.pos = pos;
  }

  public List<PluginDataDeTrello> getPluginData() {
    if (pluginData == null) {
      pluginData = new ArrayList<>();
    }
    return pluginData;
  }

  public void setPluginData(List<PluginDataDeTrello> pluginData) {
    this.pluginData = pluginData;
  }

  public Boolean getClosed() {
    return closed;
  }

  public void setClosed(Boolean closed) {
    this.closed = closed;
  }

  public String getIdList() {
    return idList;
  }

  public void setIdList(String idList) {
    this.idList = idList;
  }

  public String getIdShort() {
    return idShort;
  }

  public void setIdShort(String idShort) {
    this.idShort = idShort;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEstimado() {
    Optional<PluginDataDeTrello> pluginData = buscarDataDePluginDePoker();
    return pluginData
      .mapNary(PluginDataDeTrello::getEstimado)
      .orElse("");
  }

  private Optional<PluginDataDeTrello> buscarDataDePluginDePoker() {
    return Nary.create(getPluginData())
      .filterNary(PluginDataDeTrello::esDelPluginDePoker);
  }

  public boolean isNotClosed() {
    return !getClosed();
  }

  public void setLista(ListaDeTrello lista) {
    this.lista = lista;
  }

  public String getNombreDeLista() {
    return lista.getName();
  }

  public boolean perteneceAUnaListaNoIncluidaEn(List<String> listasExcluidas) {
    return !listasExcluidas.contains(this.getNombreDeLista());
  }

  public boolean tieneUnLabelIncluidoEn(List<String> labelsIncluidos) {
    return Nary.create(getLabels())
      .mapNary(LabelDeTrello::getName)
      .anyMatch(labelsIncluidos::contains);
  }
}
