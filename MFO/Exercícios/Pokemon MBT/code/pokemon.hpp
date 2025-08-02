#include <map>
#include <string>
using namespace std;

enum class PokemonType {
  Water,
  Fire,
  Grass,
  Electric,
};

enum class DamageModifier {
  NotVeryEffective,
  Normal,
  SuperEffective,
};

struct Pokemon {
  string name;
  int hp;
  int speed;
  string team;
  PokemonType pokemon_type;
};

void tackle(Pokemon &attacker, Pokemon &receiver, int damage) {
  receiver.hp -= damage;
}

void elemental_attack(Pokemon &attacker, Pokemon &receiver, int damage) {
  if (attacker.pokemon_type == receiver.pokemon_type) {
    damage /= 2;
  } else if (attacker.pokemon_type == PokemonType::Water && 
    receiver.pokemon_type == PokemonType::Grass) {
    damage /= 2;
  } else if (attacker.pokemon_type == PokemonType::Grass &&
    receiver.pokemon_type == PokemonType::Water) {
    damage *= 2;
  } else if (attacker.pokemon_type == PokemonType::Grass && 
    receiver.pokemon_type == PokemonType::Fire) {
    damage /= 2;
  } else if (attacker.pokemon_type == PokemonType::Electric && 
    receiver.pokemon_type == PokemonType::Grass) {
    damage /= 2;
  } else if (attacker.pokemon_type == PokemonType::Fire && 
    receiver.pokemon_type == PokemonType::Water) {
    damage /= 2;
  } else if (attacker.pokemon_type == PokemonType::Water &&
    receiver.pokemon_type == PokemonType::Fire) {
    damage *= 2;
  } else if (attacker.pokemon_type == PokemonType::Fire &&
    receiver.pokemon_type == PokemonType::Grass) {
    damage *= 2;
  } else if (attacker.pokemon_type == PokemonType::Electric &&
    receiver.pokemon_type == PokemonType::Water) {
    damage *= 2;
  }
    receiver.hp -= damage;
}
