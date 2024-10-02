package br.com.jarvisfinance.infraestructure.data.enums;

public enum TypeAsset {
    FIXED, VARIABLE;

    public boolean isVariable() {
        return this.equals(VARIABLE);
    }
}
