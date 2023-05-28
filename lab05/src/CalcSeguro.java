import java.security.InvalidParameterException;

public enum CalcSeguro {
    VALOR_BASE(10.0),
    FATOR_30(1.25),
    FATOR_30_60(1.0),
    FATOR_60_MAIS(1.5);

    public final double valor;

    CalcSeguro(double valor) {
        this.valor = valor;
    }

    public static double VALOR_BASE() {
        return VALOR_BASE.valor;
    }

    public static double FATOR_IDADE(int idade) 
    throws InvalidParameterException {
        if (idade > 0 && idade < 18) {
            throw new InvalidParameterException("Menores de idade não são aceitos");
        }
        if (idade < 30) {
            return FATOR_30.valor;
        }
        if (idade <= 60) {
            return FATOR_30_60.valor;
        }
        if (idade > 60) {
            return FATOR_60_MAIS.valor;
        }

        throw new InvalidParameterException("Idade inválida: " + idade);
    }
}
