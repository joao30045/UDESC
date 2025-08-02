#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

enum class LTLType {
    Bottom,
    Top,
    Prop,
    Not,
    And,
    Or,
    Implies,
    Globally,
    Eventually,
    Until,
    Release,
    Next
};

struct LTLProp {
    LTLType type;
    string prop;
    LTLProp* left;
    LTLProp* right;
};

LTLProp* makeProp(const string& prop) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Prop;
    node->prop = prop;
    node->left = nullptr;
    node->right = nullptr;
    return node;
}

LTLProp* makeNot(LTLProp* prop) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Not;
    node->prop = "";
    node->left = prop;
    node->right = nullptr;
    return node;
}

LTLProp* makeAnd(LTLProp* left, LTLProp* right) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::And;
    node->prop = "";
    node->left = left;
    node->right = right;
    return node;
}

LTLProp* makeOr(LTLProp* left, LTLProp* right) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Or;
    node->prop = "";
    node->left = left;
    node->right = right;
    return node;
}

LTLProp* makeImplies(LTLProp* left, LTLProp* right) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Implies;
    node->prop = "";
    node->left = left;
    node->right = right;
    return node;
}

LTLProp* makeGlobally(LTLProp* prop) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Globally;
    node->prop = "";
    node->left = prop;
    node->right = nullptr;
    return node;
}

LTLProp* makeEventually(LTLProp* prop) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Eventually;
    node->prop = "";
    node->left = prop;
    node->right = nullptr;
    return node;
}

LTLProp* makeNext(LTLProp* prop) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Next;
    node->prop = "";
    node->left = prop;
    node->right = nullptr;
    return node;
}


LTLProp* makeUntil(LTLProp* left, LTLProp* right) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Until;
    node->prop = "";
    node->left = left;
    node->right = right;
    return node;
}

LTLProp* makeRelease(LTLProp* left, LTLProp* right) {
    LTLProp* node = new LTLProp();
    node->type = LTLType::Release;
    node->prop = "";
    node->left = left;
    node->right = right;
    return node;
}

bool eval(const LTLProp& prop, const vector<vector<string>>& s) {
    switch (prop.type) {
        case LTLType::Bottom:
            return false;
        case LTLType::Top:
            return true;
        case LTLType::Prop:
            // Verdadeiro se prop é umas das proposições presentes no primeiro estado da execução
            return find(s.front().begin(), s.front().end(), prop.prop) != s.front().end();
        case LTLType::Not:
            return !eval(*prop.left, s);
        case LTLType::And:
            return eval(*prop.left, s) && eval(*prop.right, s);
        case LTLType::Or:
            return eval(*prop.left, s) || eval(*prop.right, s);
        case LTLType::Implies:
            return !eval(*prop.left, s) || eval(*prop.right, s);
        case LTLType::Globally: {
            vector<vector<string>> ts;
            for(long unsigned int t1 = 0; t1 < s.size(); t1++) {
                ts = vector<vector<string>>(s.begin() + t1, s.end());
                if (!eval(*prop.left, ts)) {
                    return false;
                }
            }

            return true;
        }
        case LTLType::Eventually: {
            vector<vector<string>> ts;
            for(long unsigned int t1 = 0; t1 < s.size(); t1++) {
                ts = vector<vector<string>>(s.begin() + t1, s.end());
                if (eval(*prop.left, ts)) {
                    return true;
                }
            }

            return false;
        }
        case LTLType::Next: {
            vector<vector<string>> ts = vector<vector<string>>(s.begin() + 1, s.end());
            return eval(*prop.left, ts);
        }
        case LTLType::Until: {
            long unsigned int t1, t2;
            vector<vector<string>> ts;
            // Procura quando a fórmula da direita passa a ser verdadeira
            for(t2 = 0; t2 < s.size(); t2++){
                ts = vector<vector<string>>(s.begin() + t2, s.end());
                if (eval(*prop.right, ts)) {
                    break;
                }
            }
            if(t2 == s.size()){
                // A fórmula da direita nunca é verdadeira
                return false;
            }
            // A fórmula da esquerda deve ser verdadeira até um estado antes do ponto encontrado
            for(t1 = 0; t1 < t2; t1++){
                ts = vector<vector<string>>(s.begin() + t1, s.end());
                if (!eval(*prop.left, ts)) {
                    return false;
                }
            }
            
            return true;
        }
        case LTLType::Release: {
            long unsigned int t1, t2;
            vector<vector<string>> ts;
            for(t2 = 0; t2 < s.size() - 1; t2++){
                ts = vector<vector<string>>(s.begin() + t2, s.end());
                if (eval(*prop.left, ts)) {
                    break;
                }
            }

            for(t1 = 0; t1 <= t2; t1++){
                ts = vector<vector<string>>(s.begin() + t1, s.end());
                if (!eval(*prop.right, ts)) {
                    return false;
                }
            }
            
            return true;
        }
        default:
            throw runtime_error("Evaluation not implemented for this LTLProp type.");
    }
}

struct TestCase {
    string name;
    bool result;
    LTLProp* prop;
    vector<vector<string>> path;
};

int main() {
    // As funções make* ajudam a definir as structs de um jeito mais conveniente
    LTLProp* propA = makeProp("a");
    LTLProp* propB = makeProp("b");
    LTLProp* notA = makeNot(propA);
    LTLProp* notB = makeNot(propB);
    LTLProp* andAB = makeAnd(propA, propB);
    LTLProp* orAB = makeOr(propA, propB);
    LTLProp* impliesAB = makeImplies(propA, propB);
    LTLProp* globallyA = makeGlobally(propA);
    LTLProp* eventuallyA = makeEventually(propA);
    LTLProp* nextA = makeNext(propA);
    LTLProp* releaseBA = makeRelease(propB, propA);
    LTLProp* untilAB = makeUntil(propA, propB);

    vector<TestCase> tests = {
        {"a", true, propA, {{"a"}}},
        {"not a", false, notA, {{"a"}}},
        {"a and b", true, andAB, {{"a", "b"}, {"a"}}},
        {"a and b", false, andAB, {{"a"}, {"a", "b"}}},
        {"a or b", true, orAB, {{"a", "b"}}},
        {"a implies b", true, impliesAB, {{"a", "b"}}},

        // Globally
        {"globally1", true, globallyA, {{"a"}, {"a"}, {"a"}}},
        {"globally2", false, globallyA, {{"a"}, {"b"}, {"a"}}},
        {"globally3", false, globallyA, {{"b"}, {"a"}, {"a"}}},

        // Eventually
        {"eventually1", true, eventuallyA, {{"a", "a", "a"}}},
        {"eventually2", true, eventuallyA, {{"a", "b", "a"}}},
        {"eventually3", false, eventuallyA, {{"b", "b", "b"}}},

        // Next
        {"next1", true, nextA, {{"a"}, {"a"}, {"a"}}},
        {"next2", true, nextA, {{"b"}, {"a"}, {"a"}}},
        {"next3", false, nextA, {{"a"}, {"b"}, {"a"}}},

        // Until
        {"until1", true, untilAB, {{"a"}, {"a", "b"}, {"b"}}},
        {"until2", true, untilAB, {{"a"}, {"a"}, {"b"}}},
        {"until3", true, untilAB, {{"b"}, {"a"}, {"b"}}},
        {"until4", false, untilAB, {{"a"}, {"a"}, {"a"}}},
        {"until5", true, untilAB, {{"b"}, {"b"}, {"b"}}},
        {"until6", true, untilAB, {{"a", "b"}, {"b"}, {"b"}}},
        {"until7", true, untilAB, {{"a"}, {"a", "b"}, {"b"}}},
        {"until8", true, untilAB, {{"a", "b"}}},

        // Release
        {"release1", true, releaseBA, {{"a"}, {"a", "b"}, {"b"}}},
        {"release2", false, releaseBA, {{"a"}, {"a"}, {"b"}}},
        {"release3", false, releaseBA, {{"b"}, {"b"}, {"a"}}},
        {"release4", false, releaseBA, {{"b"}, {"a", "b"}, {"a"}}},
        {"release5", true, releaseBA, {{"a"}, {"a"}, {"a"}}},
        {"release6", false, releaseBA, {{"b"}, {"b"}, {"b"}}},
        {"release7", true, releaseBA, {{"a", "b"}}}
    };

    int successes = 0, failures = 0;
    for (TestCase test : tests) {
        bool result = eval(*test.prop, test.path);
        if (result == test.result) {
            successes++;
        } else {
            failures++;
            cout << "Teste " << test.name << " falhou" << endl;
        }
    }

    cout << "Sucessos: " << successes << " Falhas: " << failures << endl;

    // Desalocando
    delete propA;
    delete propB;
    delete notA;
    delete notB;
    delete andAB;
    delete orAB;
    delete impliesAB;
    delete globallyA;
    delete eventuallyA;
    delete nextA;
    delete untilAB;
    delete releaseBA;

    return 0;
}