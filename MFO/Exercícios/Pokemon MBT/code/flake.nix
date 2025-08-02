{
  inputs = { nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable"; };

  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        haskellDeps = ps: with ps; [ base HUnit ];

        ghc = pkgs.haskellPackages.ghcWithPackages haskellDeps;

        nixPackages = [
          ghc
          pkgs.haskellPackages.haskell-language-server
          pkgs.haskellPackages.hlint
          pkgs.haskellPackages.apply-refact
          pkgs.haskellPackages.hoogle
          pkgs.haskellPackages.HUnit

          pkgs.cmake
          pkgs.clang
          pkgs.clang-tools
          pkgs.ccls
        ];

      in { devShell = pkgs.mkShell { buildInputs = nixPackages; }; });
}
