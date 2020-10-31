import java.time.Duration;
import java.time.Instant;

public class Comparator {
    private int comparacaovaluees;
    private Duration processTime;
    private Instant startTime;
    private Instant endTime;

    public Comparator() {
        this.comparacaovaluees = 0;
        this.startTime = Instant.now();
    }

    public int getNumCompvalue() {
        return comparacaovaluees;
    }

    public String getProcessTime() {
        return processTime.toString().replace("PT", "");
    }

    public void addNumCompvalue() {
        this.comparacaovaluees++;
    }

    public void finishComparation() {
        this.endTime = Instant.now();
        this.processTime = Duration.between(this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return "Comparator [Comparacao de valores: " + this.getNumCompvalue() + ", Tempo de Processamento: " + this.getProcessTime()
                + "]";
    }

}