#include <assert.h>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <nlohmann/json.hpp>
#include <string>
#include <unordered_map>

#include "pokemon.hpp"

using json = nlohmann::json;
using namespace std;

using PokemonMap = map<string, Pokemon>;

enum class Action { Init, ElementalAttack, Tackle, Unknown };

Action stringToAction(const std::string &actionStr) {
  static const std::unordered_map<std::string, Action> actionMap = {
      {"init", Action::Init},
      {"elemental_attack", Action::ElementalAttack},
      {"tackle", Action::Tackle}};

  auto it = actionMap.find(actionStr);
  if (it != actionMap.end()) {
    return it->second;
  } else {
    return Action::Unknown;
  }
}

int int_from_json(json j) {
  string s = j["#bigint"];
  return stoi(s);
}

PokemonType pokemon_type_from_json(json j) {
  string s = j["tag"];
  if (s == "Water") {
    return PokemonType::Water;
  } else if (s == "Fire") {
    return PokemonType::Fire;
  } else if (s == "Grass") {
    return PokemonType::Grass;
  } else if (s == "Electric") {
    return PokemonType::Electric;
  } else {
    throw invalid_argument("Invalid PokemonType");
  }
}

Pokemon pokemon_from_json(json j) {
  return {.name = j["name"],
          .hp = int_from_json(j["hp"]),
          .speed = int_from_json(j["speed"]),
          .team = j["team"],
          .pokemon_type = pokemon_type_from_json(j["pokemon_type"])};
}

map<string, Pokemon> pokemon_map_from_json(json j) {
  map<string, Pokemon> m;
  for (auto it : j["#map"]) {
    m[it[0]] = pokemon_from_json(it[1]);
  }
  return m;
}

// ----------------

string pokemon_type_to_string(PokemonType pokemon_type) {
  switch (pokemon_type) {
  case PokemonType::Water:
    return "Water";
  case PokemonType::Fire:
    return "Fire";
  case PokemonType::Grass:
    return "Grass";
  case PokemonType::Electric:
    return "Electric";
  default:
    throw invalid_argument("Invalid PokemonType");
  }
}

void print_pokemon_map(PokemonMap &pokemon_map) {
  for (auto it : pokemon_map) {
    cout << it.first << ":\t{ name: " << it.second.name
         << ", hp: " << it.second.hp << ", speed: " << it.second.speed
         << ", team: " << it.second.team
         << ", pokemon_type: " << pokemon_type_to_string(it.second.pokemon_type)
         << " }" << endl;
  }
}

string other_team(string team) { return team == "A" ? "B" : "A"; }

int main() {
  for (int i = 0; i < 10000; i++) {
    cout << "Trace #" << i << endl;
    std::ifstream f("traces/out" + to_string(i) + ".itf.json");
    json data = json::parse(f);

    PokemonMap pokemon_map =
        pokemon_map_from_json(data["states"][0]["pokemons"]);

    auto states = data["states"];
    for (auto state : states) {

      string action = state["mbt::actionTaken"];
      json nondet_picks = state["mbt::nondetPicks"];

      Pokemon attacker = pokemon_map[other_team(state["next_team"])];
      Pokemon receiver = pokemon_map[state["next_team"]];

      switch (stringToAction(action)) {
        case Action::Init: {
          cout << "initializing" << endl;
          break;
        }
        case Action::ElementalAttack: {
          int base_damage = int_from_json(nondet_picks["base_damage"]["value"]);
          cout << "elemental_attack(" << attacker.name << ", " << receiver.name
               << ", " << base_damage << ")" << endl;

          elemental_attack(attacker, receiver, base_damage);
          break;
        }
        case Action::Tackle: {
          int base_damage = int_from_json(nondet_picks["base_damage"]["value"]);
          cout << "tackle(" << attacker.name << ", " << receiver.name
               << ", " << base_damage << ")" << endl;

          tackle(attacker, receiver, base_damage);
          break;
        }
        default: {
          cout << "TODO: cover action " << action << endl;
          break;
        }
      }

      pokemon_map[other_team(state["next_team"])] = attacker;
      pokemon_map[state["next_team"]] = receiver;

      PokemonMap expected_pokemon_map =
          pokemon_map_from_json(state["pokemons"]);

      cout << "----------------------Expected-----------------" << endl;
      print_pokemon_map(expected_pokemon_map);
      cout << "----------------------Actual-------------------" << endl;
      print_pokemon_map(pokemon_map);
      cout << endl;

      assert(pokemon_map["A"].hp == expected_pokemon_map["A"].hp);
      assert(pokemon_map["B"].hp == expected_pokemon_map["B"].hp);
    }
  }
  return 0;
}
