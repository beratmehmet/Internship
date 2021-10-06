public enum OrderField {
    NAME("name"),
    SURNAME("surname"),
    BIRTHYEAR("birthyear");
    private final String label;

     OrderField(String label){
        this.label=label;
    }

    public String getLabel() {
        return label;
    }
}
