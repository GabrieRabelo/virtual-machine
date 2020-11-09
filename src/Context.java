public class Context {
    private int base;
    private int limite;
    private int[] allocatedPages;
    private int[] registers;
    private int programCounter;
    private Word instrucionRegister;

    public Context(int base, int limite, int[] allocatedPages, int[] registers, int programCounter, Word instrucionRegister) {
        this.base = base;
        this.limite = limite;
        this.allocatedPages = allocatedPages;
        this.registers = registers;
        this.programCounter = programCounter;
        this.instrucionRegister = instrucionRegister;
    }

    public int getBase() {
        return base;
    }

    public int getLimite() {
        return limite;
    }

    public int[] getAllocatedPages() {
        return allocatedPages;
    }

    public int[] getRegisters() {
        return registers;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public Word getInstrucionRegister() {
        return instrucionRegister;
    }
}
