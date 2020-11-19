import java.util.concurrent.Semaphore;

public class Rotinas {
    private GP gp;
    private Escalonador escalonador;
    private Semaphore escSemaforo;
    private Memory memory;


    public Rotinas() {
    }

    public void setAttributes(GP gp, Escalonador escalonador, Semaphore escSemaforo, Memory memory) {
        this.gp = gp;
        this.escalonador = escalonador;
        this.escSemaforo = escSemaforo;
        this.memory = memory;
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
}
