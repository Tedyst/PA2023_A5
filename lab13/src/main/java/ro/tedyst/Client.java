package ro.tedyst;

public class Client {
    private LocaleTypes locale = LocaleTypes.ENGLISH;

    public LocaleTypes getLocale() {
        return locale;
    }

    public void setLocale(LocaleTypes locale) {
        this.locale = locale;
    }
}
