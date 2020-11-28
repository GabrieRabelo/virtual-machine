import enums.Interrupts;
import enums.Opcode;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Console extends Thread {

    private ConcurrentLinkedQueue<ChamadaConsole> pedidos;
    private Memory memory;
    private CPU cpu;
    private Scanner in;
    private Semaphore appSemaforo;
    private int appEntrada;

    public Console(ConcurrentLinkedQueue<ChamadaConsole> pedidos, Memory memory, CPU cpu, Semaphore appSemaforo) {
        in = new Scanner(System.in);
        this.pedidos = pedidos;
        this.memory = memory;
        this.cpu = cpu;
        this.appSemaforo = appSemaforo;
    }

    public void setEntrada(int entrada ){
        appEntrada = entrada;
        appSemaforo.release();
    }

    public int translateMemory(int[] allocatedPages, int address) {
        return (allocatedPages[(address / 16)] * 16) + (address % 16);
    }

    public void run() {
        while (true) {
            if (pedidos.isEmpty()) {
                continue;
            }

            ChamadaConsole chamadaConsole = pedidos.poll();
            String type = chamadaConsole.getType();

            if (type.equals("IN")) {

                System.out.println("Esperando entrada do usuário no console. Ex: \"c 3\"");
                try {
                    appSemaforo.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int position = translateMemory(chamadaConsole.getAllocatedPages(), chamadaConsole.getMemoryAddress());
                memory.mem[position] = new Word(Opcode.DADO, -1, -1, appEntrada);
                cpu.callIOInterrupt();
                appEntrada = -2;
            } else if (type.equals("OUT")) {
                int position = translateMemory(chamadaConsole.getAllocatedPages(), chamadaConsole.getMemoryAddress());
                System.out.println(memory.mem[position]);
                cpu.callIOInterrupt();
            }
        }
    }
}