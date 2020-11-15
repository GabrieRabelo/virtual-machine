import enums.Opcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GP {
    private GM gm;
    private VM vm;
    private LinkedList<PCB> prontos;
    private static int process_id = 0;

    public GP(GM gm, VM vm, LinkedList<PCB> prontos) {
        this.gm = gm;
        this.vm = vm;
        this.prontos = prontos;
    }

    /*solicita memoria, carrega imagem processo, cria pcb, coloca na fila de prontos
        se não ha processo rodando, libera o escalonador*/

    public void criaProcesso(String file) {
        Word[] p = assembly(file);
        int[] allocatedPages = gm.alloc(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        prontos.add(processo);
    }

    /*desaloca pcb e memoria e retira de filas*/

    public void finalizaProcesso(PCB process) {
        gm.desaloca(process);
        prontos.remove(process);
    }


    /*Funções auxiliares privadas */

    private Word[] assembly(String arquivo) {
        String path = "src/in/" + arquivo;
        int size = getFileSize(path);
        Word[] programa = new Word[size];
        int line = 0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().replaceAll("\\s+", "").split(",");
                Opcode code = Opcode.valueOf(data[0]);
                int r1 = Integer.parseInt(data[1]);
                int r2 = Integer.parseInt(data[2]);
                int param = Integer.parseInt(data[3]);
                programa[line] = new Word(code, r1, r2, param);
                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return programa;
    }

    private int getFileSize(String path) {
        int line = 0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                myReader.nextLine();
                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return line;
    }

}
