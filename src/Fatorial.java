public class Fatorial {
    public static void main (String args[]){

        double x = 5; // aqui criamos uma variável que irá armazenar o numero do fatorial
        //stx [50] = x
        double f = x; // aqui criamos outra var. Será o resultado temporário da multiplicação
        //f = x.[memo]
        while (x > 1){ // Enquanto x for menor que 1 faça o que está entre as chaves

            f = f *(x-1); // A variável temporária ira receber o resultado da multiplicação dela, pelo valor de x menos 1
            // r1 = r3 - 1
            // r2 = r2 * r1
            //r3 = r3 - 1


            x--; // aqui decrementamos o valor de x em um, no final do loop

            System.out.println(f); // Esse comando imprime o valor de f. O último será o valor final do Fatorial.
        }
    }
}


//stx [50] = r3

//r2 = r3.[memo]


// r1 = r3 - 1
// r2 = r2 * r1
//r3 = r3 - 1

/*
    r1 = 3
    r2 = 60
    r3 = 3

 */