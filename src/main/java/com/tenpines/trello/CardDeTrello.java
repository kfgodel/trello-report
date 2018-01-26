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
}
