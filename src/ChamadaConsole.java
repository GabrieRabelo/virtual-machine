public class ChamadaConsole {
    private String type;
    private int processId;
    private int memoryAddress;

    public ChamadaConsole(String type, int processId, int memoryAddress) {
        this.type = type;
        this.processId = processId;
        this.memoryAddress = memoryAddress;
    }

    public String getType() {
        return type;
    }

    public int getProcessId() {
        return processId;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }
}
