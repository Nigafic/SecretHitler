enum PlayerRole {
    FASCIST(Role.FASCIST, Party.FASCIST, "Вы рядовой Фашист" ),
    LIBERAL(Role.LIBERAL, Party.LIBERAL, "Вы Либерал" ),
    FASCISTHitler(Role.HITLER, Party.FASCIST,"Вы ГИТЛЕР" );

    private String fullRole;
    private Role role;
    private Party party;

    private PlayerRole(Role role, Party party, String fullRole) {
        this.fullRole = fullRole;
        this.role = role;
        this.party = party;
    }

    public Role getRole() {
        return role;
    }

    public Party getParty() {
        return party;
    }

    @Override
    public String toString() {
        return fullRole;
    }
}
