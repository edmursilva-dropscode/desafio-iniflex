package Pack.Iniflex;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Principal {

	public static void main(String[] args) {
		
		/*1.  2.  3.*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", formatter), new BigDecimal("2009.14"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter), new BigDecimal("2204.30"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1990", formatter), new BigDecimal("9036.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter), new BigDecimal("2234.66"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Keloísa", LocalDate.parse("24/05/2003", formatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter), new BigDecimal("2799.93"), "Gerente"));

        /*3.1*/
        /*Exibe todos os funcionários inseridos*/
        System.out.println("Lista de funcionários antes da remoção:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        /*3.2*/
        /*Remove o funcionário "João"*/
        Iterator<Funcionario> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNome().equals("João")) {
                iterator.remove();
                break;
            }
        }

        /*3.3*/
        /*Exibe todos os funcionários após a remoção*/
        System.out.println("\nLista de funcionários após a remoção de João:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        /*Aplica aumento de 10% no salário de cada funcionário*/
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioAtualizado = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(salarioAtualizado.setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }

        /*3.4*/
        /*Exibe todos os funcionários após o aumento*/
        System.out.println("\nLista de funcionários após o aumento de 10% no salário:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }        

        /*3.5    3.6*/
        /*Agrupando os funcionários por função usando um Map*/
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

        /*Usando Java Stream para agrupar os funcionários por função*/
        funcionarios.forEach(funcionario -> {
            String funcao = funcionario.getFuncao();
            funcionariosPorFuncao.computeIfAbsent(funcao, k -> new ArrayList<>()).add(funcionario);
        });

        /*Exibindo os funcionários agrupados por função de forma detalhada*/
        System.out.println("Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("\nFunção: " + funcao);
            listaFuncionarios.forEach(funcionario -> {
                System.out.println("  Nome: " + funcionario.getNome());
                System.out.println("  Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                System.out.println("  Salário: " + NumberFormat.getCurrencyInstance().format(funcionario.getSalario()));
                System.out.println(); /*Linha em branco para separar os funcionários*/
            });
        });
                
        /*3.8*/
        /*Filtrando os funcionários que fazem aniversário em outubro (mês 10) e dezembro (mês 12)*/
        List<Funcionario> aniversariantesOutubroDezembro = funcionarios.stream()
                .filter(funcionario ->
                        funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        /*Exibindo os funcionários que fazem aniversário em outubro e dezembro*/
        System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
        aniversariantesOutubroDezembro.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome() +
                               ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter) +
                               ", Salário: " +  NumberFormat.getCurrencyInstance().format(funcionario.getSalario()) +
                               ", Função: " + funcionario.getFuncao());
        });
        
        System.out.println(); /*Linha em branco para separar os funcionários*/
        
        /*3.9*/
        /*Variáveis para armazenar o funcionário com maior idade*/
        Funcionario funcionarioMaiorIdade = null;
        int maiorIdadeEncontrada = Integer.MIN_VALUE;

        /* Iterando sobre a lista de funcionários para encontrar o funcionário com maior idade */
        for (Funcionario funcionario : funcionarios) {
            int idade = calcularIdade(funcionario.getDataNascimento());
            if (idade > maiorIdadeEncontrada) {
                maiorIdadeEncontrada = idade;
                funcionarioMaiorIdade = funcionario;
            }
        }

        /* Exibindo o funcionário com maior idade */
        if (funcionarioMaiorIdade != null) {
            System.out.println("Funcionário com maior idade:");
            System.out.println("Nome: " + funcionarioMaiorIdade.getNome());
            System.out.println("Idade: " + maiorIdadeEncontrada);
        } else {
            System.out.println("Lista de funcionários vazia");
        }
        
        System.out.println(); /*Linha em branco para separar os funcionários*/

        /*3.9*/
        /* Ordenando a lista de funcionários por nome (ordem alfabética) */
        Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));

        /* Exibindo a lista de funcionários ordenada por nome */
        System.out.println("Lista de funcionários por ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                               ", Data de Nascimento: " + funcionario.getDataNascimento().format(formatter) +
                               ", Salário: " + NumberFormat.getCurrencyInstance().format(funcionario.getSalario()) +
                               ", Função: " + funcionario.getFuncao());
        }               
        
        System.out.println(); /*Linha em branco para separar os funcionários*/
        
        /*3.10*/
        /* Variável para armazenar o total dos salários */
        BigDecimal totalSalarios = BigDecimal.ZERO;

        /* Calculando o total dos salários */
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        /* Exibindo o total dos salários */
        System.out.println("Total dos salários dos funcionários: " + NumberFormat.getCurrencyInstance().format(totalSalarios));        
               
        System.out.println(); /*Linha em branco para separar os funcionários*/
        
        /*3.11*/        
        /*Valor do salário mínimo*/
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        /*Calculando quantos salários mínimos cada funcionário ganha*/
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioFuncionario = funcionario.getSalario();
            BigDecimal salariosMinimos = salarioFuncionario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }        
	}

    /*Método para calcular a idade com base na data de nascimento*/
    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    } 	
	
}
