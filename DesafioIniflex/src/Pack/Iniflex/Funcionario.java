package Pack.Iniflex;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    // Getters e Setters
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataNascimento().format(formatter) +
                ", salario=" + numberFormat.format(salario) +
                ", funcao='" + funcao + '\'' +
                '}';
    }
	
}
