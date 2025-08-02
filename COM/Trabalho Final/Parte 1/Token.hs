module Token where

data Token
  = TInt
  | TString
  | TDouble
  | TVoid
  | TReturn
  | TIf
  | TElse
  | TWhile
  | TPrint
  | TRead
  | TAssign 
  | TLBrace
  | TRBrace
  | TComma
  | TSemi
  | TId String 
  | TIntConst Int
  | TDoubleConst Double
  | TLiteral String
  | ADD
  | SUB
  | MUL
  | DIV
  | LPAR
  | RPAR
  | LT_ 
  | GT_ 
  | LEQ 
  | GEQ 
  | EQ_ 
  | NEQ 
  | AND 
  | OR 
  | NOT
  deriving (Eq, Show)