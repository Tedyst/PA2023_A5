package ro.tedyst;

public class Token {
    private final int number;

    public Token(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Token{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token = (Token) o;

        return getNumber() == token.getNumber();
    }

    @Override
    public int hashCode() {
        return getNumber();
    }
}
