int busca( Lista l, void *info, int (*compara)(void *, void *) ){
	Elemento *aux = l.cabeca;
	int cont = 0;
	while(aux != NULL){
		if(compara(info, aux->info) == 0)
			return cont;
		cont++;
		aux = aux->proximo;
	}
	return -1;
}