module Token where

data Token
  = NUM Double
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