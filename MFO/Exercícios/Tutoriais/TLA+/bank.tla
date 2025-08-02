-------------------------- MODULE bank --------------------------
EXTENDS Integers

\* A state variable to store the balance of each account
VARIABLE balances

ADDRESSES == { "alice", "bob", "charlie" }

Deposit(account, amount) ==
  \* Increment balance of account by amount
  balances' = [balances EXCEPT ![account] = @ + amount]

Withdraw(account, amount) ==
  \* A precondition, there should be enough to withdraw
  /\ balances[account] >= amount
  \* Decrement balance of account by amount
  /\ balances' = [balances EXCEPT ![account] = @ - amount]

Init ==
  \* At the initial state, all balances are zero
  balances = [ addr \in ADDRESSES |-> 0 ]

Next ==
  \* Only continue if balance is small enough to avoid a big state space
  /\ \A addr \in ADDRESSES : balances[addr] <= 50
  \* Non-deterministically pick an address and an amount
  /\ \E account \in ADDRESSES, amount \in 1..100 :
      \* Non-deterministically choose to either deposit or withdraw
      \/ Deposit(account, amount)
      \/ Withdraw(account, amount)

NoNegatives == \A addr \in ADDRESSES : balances[addr] >= 0
=================================================================