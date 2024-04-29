package api.usuarios.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskUtils {

    public static String maskCpf(String tipoCpf) {
        String cpf = clean(tipoCpf);
        if (cpf.length() == 11) {
            Pattern pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})");
            Matcher matcher = pattern.matcher(cpf);
            if (matcher.find()) {
                return matcher.replaceAll("$1.$2.$3-$4");
            }
        }
        return tipoCpf;
    }

    public static String ofuscarCpf(String cpf) {
        String mask = maskCpf(cpf);
        char[] ofuscarCpf = new char[mask.length()];
        for(int i = 0; i < ofuscarCpf.length; i++) {
            char ch = mask.charAt(i);
            ofuscarCpf[i] = (ch == '.' || ch == '-') ? ch : (i > 3 && i < 11) ? mask.replace(ch, '*').charAt(i) : ch;
        }
        return String.valueOf(ofuscarCpf);
    }

    private static String clean(String tipo) {
        if (!tipo.isEmpty()) {
            return tipo.replaceAll("\\D", "");
        }
        return tipo;
    }
}
