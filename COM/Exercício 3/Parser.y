--    happy --ghc Parser.y
--    alex Lex.x
--    ghci -package array Parser.hs
{
module Parser where

import Token
import qualified Lex as L
}

%name calc
%tokentype { Token }
%error { parseError }
%token 
  '+'  {ADD}
  '-'  {SUB}
  '*'  {MUL}
  '/'  {DIV}
  '('  {LPAR}
  ')'  {RPAR}
  '<'  {LT_}
  '>'  {GT_}
  '<=' {LEQ}
  '>=' {GEQ}
  '==' {EQ_}
  '/=' {NEQ}
  '&&' {AND}
  '||' {OR}
  '!'  {NOT}
  Num  {NUM $$}

%%
S : LogExpr                    {Left $1}
  | Expr                       {Right $1}

LogExpr : LogExpr '&&' LogTerm {$1 && $3}
        | LogExpr '||' LogTerm {$1 || $3}
        | LogTerm              {$1}

LogTerm : '!' LogFactor        {not $2}
        | LogFactor            {$1}

LogFactor : '(' LogExpr ')'    {$2}
          | RelExpr            {$1}

RelExpr : Expr '<' Expr        {$1 < $3}
        | Expr '>' Expr        {$1 > $3}
        | Expr '<=' Expr       {$1 <= $3}
        | Expr '>=' Expr       {$1 >= $3}
        | Expr '==' Expr       {$1 == $3}
        | Expr '/=' Expr       {$1 /= $3}

Expr  : Expr '+' Term          {$1 + $3}
      | Expr '-' Term          {$1 - $3}
      | Term                   {$1}

Term  : Term '*' Factor        {$1 * $3}
      | Term '/' Factor        {$1 / $3}
      | Factor                 {$1}

Factor : Num                   {$1}
       | '(' Expr ')'          {$2}

{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do putStr "Expressão:"
          s <- getLine
          print (calc (L.alexScanTokens s))
}
