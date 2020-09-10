import java.util.LinkedList;

public class OS {
    private VM vm;
    private GM gm;

    private LinkedList<PCB> processos;
    public OS(String file) {
        vm = new VM();
        gm = new GM(vm.tamMem);

        vm.assembly(file);
    }
}