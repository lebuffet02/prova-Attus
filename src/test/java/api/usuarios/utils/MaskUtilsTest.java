package api.usuarios.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaskUtilsTest {

    private static final String CPF_CORRETO = "05084009801";
    private static final String CPF_INCORRETO = "050840";
    private static final String CPF_CORRETO_MASCARADO = "050.840.098-01";
    private static final String CPF_CORRETO_OFUSCADO = "050.***.***-01";

    @DisplayName("Não deve ser nulo ao chamar o método maskCpf.")
    @Test
    void testaMascaraCpfNaoNula() {
        Assertions.assertNotNull(MaskUtils.maskCpf(CPF_CORRETO));
    }

    @DisplayName("Não deve retornar o cpf mascarado ao inserir um cpf inválido.")
    @Test
    void testaMascaraCpfInvalida() {
        Assertions.assertNotNull(MaskUtils.maskCpf(CPF_INCORRETO));
    }

    @DisplayName("O cpf não deve ser mascarado corretamente.")
    @Test
    void testaCpfMascaradoDeveSerDiferente() {
        Assertions.assertNotEquals(CPF_CORRETO, MaskUtils.maskCpf(CPF_CORRETO));
    }

    @DisplayName("O cpf deve ser mascarado corretamente.")
    @Test
    void testaCpfMascaradoDeveSerIgual() {
        Assertions.assertEquals(CPF_CORRETO_MASCARADO, MaskUtils.maskCpf(CPF_CORRETO));
    }

    @DisplayName("O cpf inválido mascarado deve retornar ele mesmo(vazio).")
    @Test
    void testaCpfMascaradoDeveSerVazio() {
        Assertions.assertNotNull(MaskUtils.maskCpf(""));
    }

    @DisplayName("Não deve ser nulo ao chamar o método ofuscarCpf.")
    @Test
    void testaOfuscarCpfNaoNulo() {
        Assertions.assertNotNull(MaskUtils.ofuscarCpf(CPF_CORRETO));
    }

    @DisplayName("Deve estar ofuscado ao chamar o método ofuscarCpf passando um cpf válido.")
    @Test
    void testaOfuscarCpfValidoMascarado() {
        Assertions.assertEquals(CPF_CORRETO_OFUSCADO, MaskUtils.ofuscarCpf(CPF_CORRETO));
    }
}