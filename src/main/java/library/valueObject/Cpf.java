package library.valueObject;

import java.util.regex.Pattern;

import library.exception.InvalidCpfException;

public class Cpf {
    private static final String cpfRegex = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";
    private String rawCpf;

    public Cpf(String cpf)
            throws InvalidCpfException {
        if (!isValid(cpf)) {
            throw new InvalidCpfException();
        }
        rawCpf = cleanCpf(cpf);
    }

    public static boolean isValid(String cpf) {
        String rawCpf, validCPF;
        int aux = 0;

        if (!Pattern.matches(cpfRegex, cpf)) {
            return false;
        }

        rawCpf = cleanCpf(cpf);

        if (rawCpf.equals("00000000000") ||
                rawCpf.equals("11111111111") ||
                rawCpf.equals("22222222222") ||
                rawCpf.equals("33333333333") ||
                rawCpf.equals("44444444444") ||
                rawCpf.equals("55555555555") ||
                rawCpf.equals("66666666666") ||
                rawCpf.equals("77777777777") ||
                rawCpf.equals("88888888888") ||
                rawCpf.equals("99999999999")) {
            return false;
        }

        validCPF = rawCpf.substring(0, 9);

        for (byte i = 0; i < 9; i++) {
            aux += (validCPF.charAt(i) - 48) * (10 - i);
        }
        aux %= 11;
        validCPF += aux < 2 ? 0 : 11 - aux; // first check digit

        aux = 0;
        for (byte i = 0; i < 10; i++) {
            aux += (validCPF.charAt(i) - 48) * (11 - i);
        }
        aux %= 11;
        validCPF += aux < 2 ? 0 : 11 - aux; // second check digit

        return validCPF.equals(rawCpf);
    }

    public static String cleanCpf(String cpf) {
        return cpf.replaceAll("[.-]", "");
    }

    public String getRawCpf() {
        return rawCpf;
    }

    public String getFormattedCpf() {
        return (rawCpf.substring(0, 3) + "." +
                rawCpf.substring(3, 6) + "." +
                rawCpf.substring(6, 9) + "-" +
                rawCpf.substring(9));
    }

    @Override
    public String toString() {
        return getFormattedCpf();
    }
}
