import java.util.LinkedList;

public class Rotinas {
    private GP gp;
    private Escalonador escalonador;

    public Rotinas(GP gp, Escalonador escalonador) {
        this.gp = gp;
        this.escalonador = escalonador;
    }
    //finaliza o processo, chamando o GP e escalona novo processo
    public void stop(PCB processo){
        gp.finalizaProcesso(processo);
    }
}
