import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class OS {
    public Memory memory;
    private CPU cpu;
    private GM gm;
    private GP gp;
    private Escalonador escalonador;
    private LinkedList<PCB> prontos;
    private LinkedList<PCB> bloqueados;
    private ConcurrentLinkedQueue<ChamadaConsole> pedidos;
    private Rotinas rotinas;
    private Semaphore escSemaforo = new Semaphore(0);
    private Semaphore cpuSemaforo = new Semaphore(0);
    private Console console;


    public OS(Semaphore appSemaforo) {
        memory = new Memory(escSemaforo, cpuSemaforo);
        prontos = new LinkedList();
        bloqueados = new LinkedList();
        pedidos = new ConcurrentLinkedQueue();
        gm = new GM(memory.mem);
        escalonador = new Escalonador();
        gp = new GP();
        rotinas = new Rotinas();
        cpu = new CPU();
        console = new Console(pedidos, memory, cpu, appSemaforo);

        escalonador.setAttributes(prontos, escSemaforo, cpuSemaforo, cpu);
        gp.setAttributes(gm, memory, prontos, escSemaforo, escalonador);
        rotinas.setAttributes(gp, escalonador, escSemaforo, memory, bloqueados, pedidos, appSemaforo);
        cpu.setAttributes(memory.mem, escSemaforo, cpuSemaforo, rotinas);

        this.run();
    }

    public void setEntradaConsole(int appEntrada){
        console.setEntrada(appEntrada);
    }

    public void carga(String file) {
        gp.criaProcesso(file);
    }

    public void run() {
        escalonador.setName("Escalonador");
        System.out.println("\nIniciando Thread Escalonador");
        escalonador.start();
        System.out.println("Iniciando Thread CPU");
        cpu.setName("Cpu");
        cpu.start();
        System.out.println("Iniciando Thread Console");
        console.setName("Console");
        console.start();
    }

}