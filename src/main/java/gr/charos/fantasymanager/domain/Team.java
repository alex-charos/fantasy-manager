package gr.charos.fantasymanager.domain;

import java.util.List;

public record Team(String id, String name, String crest, List<Player> squad) {}