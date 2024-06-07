import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Escolha o tipo de aposta: ");
            System.out.println("1. Aposta Esportiva");
            System.out.println("2. Aposta em Roleta");
            System.out.println("3. Visualizar histórico de apostas");
            System.out.println("4. Sair");
            int escolha = scanner.nextInt();
            String entrada = scanner.nextLine();

            if (escolha == 1) {
                ApostaEsportiva minhaAposta = new ApostaEsportiva();
                minhaAposta.definirValorAposta(scanner);
                minhaAposta.definirAposta(scanner);

            } else if (escolha == 2) {
                ApostaRoleta minhaAposta = new ApostaRoleta();
                minhaAposta.definirValorAposta(scanner);
                minhaAposta.resultadoFinal();
            }
            else if (escolha == 3) {
                // Leitura de histórico no arquivo
                String nomeArquivo = "historico_apostas.txt";
                try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
                    String linha;
                    while ((linha = leitor.readLine()) != null) {
                        System.out.println(linha);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                }
            }
            else if (escolha == 4) {
                System.out.println("Encerrando o programa...");
                break; // Sai do loop
            }
            else {
                System.out.println("Escolha inválida.");
            }
        }
    }
}
