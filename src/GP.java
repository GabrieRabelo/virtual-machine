import enums.Opcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class GP {
    private GM gm;
    private Memory memory;
    private LinkedList<PCB> prontos;
    private Semaphore escSemaforo;
    private static int process_id = 0;
    private Escalonador escalonador;
    private Semaphore mutex = new Semaphore(1);

    public GP() {

    }

    public void setAttributes(GM gm, Memory memory, LinkedList<PCB> prontos, Semaphore escSemaforo, Escalonador escalonador){
        this.gm = gm;
        this.memory = memory;
        this.prontos = prontos;
        this.escSemaforo = escSemaforo;
        this.escalonador = escalonador;
    }

    /*solicita memoria, carrega imagem processo, cria pcb, coloca na fila de prontos
        se não ha processo rodando, libera o escalonador*/

    public void criaProcesso(String file){
        try{
            mutex.acquire();
        }catch (InterruptedException e){}
        Word[] p = assembly(file);
        int[] allocatedPages = gm.alloc(p);
        PCB processo = new PCB(process_id, allocatedPages);
        process_id++;
        //cuidar com muitos processos, e já tiver processos ativos
        if(prontos.size() == 0 && escalonador.getRunningProcess() == null){
            System.out.println("Adiciona com release");
            prontos.add(processo);
            escSemaforo.release();
        }else{
            System.out.println("Adiciona sem release");
            prontos.add(processo);
        }
        mutex.release();
    }

    /*mutex do GP, inicializado em 1 para cria e finaliza*/
    /*mutex para a fila de prontos*/
    /*desaloca pcb e memoria e retira de filas*/

    public void finalizaProcesso(PCB process){
        try{
            mutex.acquire();
        }catch (InterruptedException e){}
        gm.desaloca(process);
        prontos.remove(process);
        mutex.release();
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
