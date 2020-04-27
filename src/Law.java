enum Law{
    FASCIST("Фашистский закон" ), LIBERAL("Либеральный закон");
    private String string;

   private Law(String s){
        this.string = s;
    }

    @Override
    public String toString() {
        return string;
    }



    public String getLaw() {
        return string;
    }
}
