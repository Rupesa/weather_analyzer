package MPEIProject;

/**
 * TODO
 * Introdução:
 *  Decidimos implementar um programa que, utilizando bloom counter, minhashing e contador
 *  estocástico, ler a partir de ficheiros com informação sobre a precipitação das últimas
 *  décadas e listar, com a data pedida ao utilizador. 
 * 	
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Contador:
 * 		O contador tem dois atributos para contar, do tipo short: gap e counter.
 * 		Para pensar em como este contador funciona, temos de pensar primeiro num com um intervalo
 *  fixo, isto é, o contador conta 1 vez a cada k eventos (em média, que incrementa com prob = 1/k)
 *  portanto se o contador tiver valor 11, sabemos que existem aproximadamente 11k eventos.
 *  	O contador criado é um pouco diferente, com k variável, que varia de forma linear,
 *  ie: se o contador incrementar (prob = 1/(k=1)), k incrementa, a partir daí,
 *  se o contador incrementar (prob = 1/(k=2)), k incrementa, e assim sucessivamente.
 *  	Portanto, à medida que o contador incrementa, a probabilidade de este o fazer
 *  de novo é menor. Isto significa que a precisão vai diminuindo, mas ganha capacidade de contar
 *  valores enormes (na ordem dos centenas de milhares) com números de gama 0 - 2^15 (aprox 32 000).
 * 		Exemplo: se o contador tiver o contador a 320, o intervalo é 321 (o contador começa
 * 	a 0, o intervalo a 1, e ambos incrementam à mesma altura) portanto se quisermos saber
 *	aproximadamente o verdadeiro valor temos de calcular
 *  1 + 2 + ... + c-1 + c = c * (c+1) / 2, sendo c o valor do contador.
 *  No programa, o contador é o atributo counter, enquanto que o intervalo é o gap.
 *  O programa está comentado, explica o que faz cada método
 * 
 * 
 * 
 * 
 */
