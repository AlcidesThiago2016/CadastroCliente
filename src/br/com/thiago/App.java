package br.com.thiago;

import br.com.thiago.dao.ClienteMapDAO;
import br.com.thiago.dao.IClienteDAO;
import br.com.thiago.domain.Cliente;

import javax.swing.*;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para Cadastro,  2 para Consultar, 3 para Excluir, 4 para Alterar ou 5 para Sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)){
            if ("".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opcão inválida Digite 1 para Cadastro,  2 para Consultar, 3 para Excluir, 4 para Alterar ou 5 para Sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog( null,
                        "Digite os dados do cliente separados por virgula, conforme exemplo: Nome, CPF, Telefone ...",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastro(dados);
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog( null,
                        "Digite o CPF",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);

                consultar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para Cadastro,  2 para Consultar, 3 para Excluir, 4 para Alterar ou 5 para Sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar(String dados) {
       Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
       if (cliente != null){
           JOptionPane.showMessageDialog(null, "Cliente encontrado com sucesso!" + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
       } else {
           JOptionPane.showMessageDialog(null, "Cliente encontrado não encontrado!" , "Sucesso", JOptionPane.INFORMATION_MESSAGE);

       }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)) {
            return true;
        }
        return false;
    }
    private static void cadastro(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        Boolean isCadastrado = iClienteDAO.cadastra(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já está cadastrado!");
        }
    }

    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)) {
            return true;
        }
        return false;
    }

    private static void sair() {
        JOptionPane.showInputDialog( null, "Até Logo: ", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)) {
            return true;
        }
        return false;
    }
}
