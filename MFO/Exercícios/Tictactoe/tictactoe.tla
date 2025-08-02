---------------------------- MODULE tictactoe -------------------------------

EXTENDS Naturals

VARIABLES
    board, \* board[1..3][1..3] A 3x3 tic-tac-toe board
    nextTurn \* who goes next

BoardIs(coordinate, player) ==
    board[coordinate[1]][coordinate[2]] = player

BoardFilled ==
    \* There does not exist
    ~\E i \in 1..3, j \in 1..3:
        \* an empty space
        LET space == board[i][j] IN
        space = "_"

BoardEmpty ==
    \* There does not exist
    \A i \in 1..3, j \in 1..3:
        \* an empty space
        LET space == board[i][j] IN
        space = "_"

WinningPositions == {
    \* Horizonal wins
    <<<<1,1>>, <<1,2>>, <<1,3>>>>,
    <<<<2,1>>, <<2,2>>, <<2,3>>>>,
    <<<<3,1>>, <<3,2>>, <<3,3>>>>,
    \* Vertical wins
    <<<<1,1>>, <<2,1>>, <<3,1>>>>,
    <<<<1,2>>, <<2,2>>, <<3,2>>>>,
    <<<<1,3>>, <<2,3>>, <<3,3>>>>,
    \* Diagonal wins
    <<<<1,1>>, <<2,2>>, <<3,3>>>>,
    <<<<3,1>>, <<2,2>>, <<1,3>>>>
}

Won(player) ==
    \* A player has won if there exists a winning position
    \E winningPosition \in WinningPositions:
        \* Where all the needed spaces
        \A i \in 1..3:
            \* are occupied by one player
            board[winningPosition[i][1]][winningPosition[i][2]] = player

Move(player, coordinate) ==
    /\ board[coordinate[1]][coordinate[2]] = "_"
    /\ board' = [board EXCEPT
                        ![coordinate[1]][coordinate[2]] = player]

MoveToEmpty(player) ==
    /\ \E i \in 1..3: \E j \in 1..3: \* There exists a position on the board
        /\ board[i][j] = "_" \* Where the board is currently empty
        /\ Move(player, <<i,j>>)

MoveO ==
    /\ nextTurn = "O" \* Only enabled on O's turn
    /\ ~Won("X") \* And X has not won
    /\ MoveToEmpty("O") \* O still tries every empty space
    /\ nextTurn' = "X" \* The future state of next turn is X

Corners == {
    <<1,1>>,
    <<3,1>>,
    <<1,3>>,
    <<3,3>>
}

StartInCorner ==
    \E corner \in Corners:
        Move("X", corner)

PartialWins == {
    <<1,2,3>>,
    <<2,3,1>>,
    <<3,1,2>>
}

CanWin == \E winningPostion \in WinningPositions, partialWin \in PartialWins:
                    /\ BoardIs(winningPostion[partialWin[1]],"X")
                    /\ BoardIs(winningPostion[partialWin[2]],"X")
                    /\ BoardIs(winningPostion[partialWin[3]],"_")

CanBlockWin == \E winningPostion \in WinningPositions, partialWin \in PartialWins:
                        /\ BoardIs(winningPostion[partialWin[1]], "O")
                        /\ BoardIs(winningPostion[partialWin[2]], "O")
                        /\ BoardIs(winningPostion[partialWin[3]], "_")

CanTakeCenter == board[2][2] = "_"

CanSetupWin ==
    \E winningPostion \in WinningPositions, partialWin \in PartialWins:
        /\ BoardIs(winningPostion[partialWin[1]], "X")
        /\ BoardIs(winningPostion[partialWin[2]], "_")
        /\ BoardIs(winningPostion[partialWin[3]], "_")

Win == \E winningPostion \in WinningPositions, partialWin \in PartialWins:
                    /\ BoardIs(winningPostion[partialWin[1]],"X")
                    /\ BoardIs(winningPostion[partialWin[2]],"X")
                    /\ BoardIs(winningPostion[partialWin[3]],"_")
                    /\ Move("X", winningPostion[partialWin[3]])

BlockWin == \E winningPostion \in WinningPositions, partialWin \in PartialWins:
                        /\ BoardIs(winningPostion[partialWin[1]], "O")
                        /\ BoardIs(winningPostion[partialWin[2]], "O")
                        /\ BoardIs(winningPostion[partialWin[3]], "_")
                        /\ Move("X", winningPostion[partialWin[3]])

TakeCenter ==
    /\ Move("X", <<2,2>>)

SetupWin ==
    \E winningPostion \in WinningPositions, partialWin \in PartialWins:
        /\ BoardIs(winningPostion[partialWin[1]], "X")
        /\ BoardIs(winningPostion[partialWin[2]], "_")
        /\ BoardIs(winningPostion[partialWin[3]], "_")
        /\ \E i \in 2..3:
            Move("X", winningPostion[partialWin[i]])

MoveX ==
    /\ nextTurn = "X" \* Only enabled on X's turn
    /\ ~Won("O") \* And X has not won
    \* This specifies the spots X will move on X's turn
    /\ \/ /\ BoardEmpty
          /\ StartInCorner
       \/ /\ ~BoardEmpty \* If its not the start
          /\ \/ /\ CanWin
                /\ Win
             \/ /\ ~CanWin
                /\  \/ /\ CanBlockWin
                       /\ BlockWin
                    \/ /\ ~CanBlockWin
                       /\ \/ /\ CanTakeCenter
                             /\ TakeCenter
                          \/ /\ ~CanTakeCenter
                             /\ \/ /\ CanSetupWin
                                   /\ SetupWin
                                \/ /\ ~CanSetupWin
                                   /\ MoveToEmpty("X") \* No more strategies. Pick spot
    /\ nextTurn' = "O" \* The future state of next turn is O

Init ==
        /\ nextTurn = "X" \* X always goes first
        \* Every space in the board states blank
        /\ board = [i \in 1..3 |-> [j \in 1..3 |-> "_"]]

GameOver == Won("X") /\ Won("O") /\ BoardFilled

\* Every state, X will move if X's turn, O will move on O's turn
Next == MoveX \/ MoveO \/ (GameOver /\ UNCHANGED << board, nextTurn >>)

XHasNotWon == ~Won("X")
OHasNotWon == ~Won("O")

\* It's not a stalemate if one player has won or the board is not filled
NotStalemate ==
    \/ Won("X")
    \/ Won("O")
    \/ ~BoardFilled

\* ...

=============================================================================
