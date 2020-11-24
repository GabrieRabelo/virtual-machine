import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Rotinas {
    private GP gp;
    private Escalonador escalonador;
    private Semaphore escSemaforo;
    private Memory memory;
    private LinkedList<PCB> bloqueados;
    private LinkedList<ChamadaConsole> pedidos;


    public Rotinas() {
    }

    public void setAttributes(GP gp, Escalonador escalonador, Semaphore escSemaforo, Memory memory, LinkedList<PCB> bloqueados, LinkedList<ChamadaConsole> pedidos) {
        this.gp = gp;
        this.escalonador = escalonador;
        this.escSemaforo = escSemaforo;
        this.memory = memory;
        this.bloqueados = bloqueados;
        this.pedidos = pedidos;
    }

    //finaliza o processo, chamando o GP e escalona novo processo
    public void stop(){
        memory.dump(0,80);
        gp.finalizaProcesso(escalonador.getRunningProcess());
        escalonador.setRunningProcessAsNull();
        escSemaforo.release();
    }

    public void timer(Context context) {
        PCB process = escalonador.getRunningProcess();
        escalonador.setRunningProcessAsNull();
        process.saveContext(context);
        escalonador.getProntos().add(process);
        escSemaforo.release();
    }

    public void chamadaIO(Context context) {
        PCB process = escalonador.getRunningProcess();
        escalonador.setRunningProcessAsNull();
        process.saveContext(context);
        bloqueados.add(process);

        Word ir = process.getContext().getInstructionRegister();
        String type = ir.r1 == 1 ? "IN" : "OUT";
        ChamadaConsole chamadaConsole = new ChamadaConsole(type, process.getId(), ir.r2, context.getAllocatedPages());
        pedidos.add(chamadaConsole);
        System.out.println("adicionei");
        System.out.println(pedidos.get(0).toString());
        escSemaforo.release();
    }
}
