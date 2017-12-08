package com.xilixir.fortniteapi.v2;

public class Stats {
    // epic provided
    private double br_score_pc_m0_p2;
    private double br_score_pc_m0_p10;
    private double br_score_pc_m0_p9;
    private double br_matchesplayed_pc_m0_p2;
    private double br_matchesplayed_pc_m0_p10;
    private double br_matchesplayed_pc_m0_p9;
    private long br_lastmodified_pc_m0_p2;
    private long br_lastmodified_pc_m0_p10;
    private long br_lastmodified_pc_m0_p9;
    private double br_placetop1_pc_m0_p2;
    private double br_placetop1_pc_m0_p10;
    private double br_placetop1_pc_m0_p9;
    private double br_minutesplayed_pc_m0_p2;
    private double br_minutesplayed_pc_m0_p10;
    private double br_minutesplayed_pc_m0_p9;
    private double br_kills_pc_m0_p2;
    private double br_kills_pc_m0_p10;
    private double br_kills_pc_m0_p9;
    private double br_placetop3_pc_m0_p9;
    private double br_placetop6_pc_m0_p9;
    private double br_placetop12_pc_m0_p10;
    private double br_placetop5_pc_m0_p10;
    private double br_placetop25_pc_m0_p2;
    private double br_placetop10_pc_m0_p2;

    // calculated
    private double soloKillDeathRatio;
    private double duoKillDeathRatio;
    private double squadKillDeathRatio;
    private double soloWinRatio;
    private double duoWinRatio;
    private double squadWinRatio;
    private double soloKillsPerMinute;
    private double duoKillsPerMinute;
    private double squadKillsPerMinute;
    private double soloKillsPerMatch;
    private double duoKillsPerMatch;
    private double squadKillsPerMatch;
    private double totalMatchesPlayed;
    private double totalLastModified;
    private double totalWins;
    private double totalMinutesPlayed;
    private double totalKills;
    private double totalKillDeathRatio;
    private double totalWinRatio;
    private double totalKillsPerMinute;
    private double totalKillsPerMatch;

    public void calculate(){
        // kills/death
        this.soloKillDeathRatio = this.getSoloKills()/((this.getSoloMatchesPlayed() - this.getSoloWins()) > 0 ? (this.getSoloMatchesPlayed() - this.getSoloWins()) : 1);
        this.duoKillDeathRatio = this.getDuoKills()/((this.getDuoMatchesPlayed() - this.getDuoWins()) > 0 ? (this.getDuoMatchesPlayed() - this.getDuoWins()) : 1);
        this.squadKillDeathRatio = this.getSquadKills()/((this.getSquadMatchesPlayed() - this.getSquadWins()) > 0 ? (this.getSquadMatchesPlayed() - this.getSquadWins()) : 1);

        // winrate
        this.soloWinRatio = (this.getSoloWins()/(this.getSoloMatchesPlayed() > 0 ? this.getSoloMatchesPlayed() : 1)) * 100;
        this.duoWinRatio = (this.getDuoWins()/(this.getDuoMatchesPlayed() > 0 ? this.getDuoMatchesPlayed() : 1)) * 100;
        this.squadWinRatio = (this.getSquadWins()/(this.getSquadMatchesPlayed() > 0 ? this.getSquadMatchesPlayed() : 1)) * 100;

        // kills/minute
        this.soloKillsPerMinute = this.getSoloKills()/(this.getSoloMinutesPlayed() > 0 ? this.getSoloMinutesPlayed() : 1);
        this.duoKillsPerMinute = this.getDuoKills()/(this.getDuoMinutesPlayed() > 0 ? this.getDuoMinutesPlayed() : 1);
        this.squadKillsPerMinute = this.getSquadKills()/(this.getSquadMinutesPlayed() > 0 ? this.getSquadMinutesPlayed() : 1);

        // kills/match
        this.soloKillsPerMatch = this.getSoloKills()/(this.getSoloMatchesPlayed() > 0 ? this.getSoloMatchesPlayed() : 1);
        this.duoKillsPerMatch = this.getDuoKills()/(this.getDuoMatchesPlayed() > 0 ? this.getDuoMatchesPlayed() : 1);
        this.squadKillsPerMatch = this.getSquadKills()/(this.getSquadMatchesPlayed()> 0 ? this.getSquadMatchesPlayed(): 1);

        // total
        this.totalMatchesPlayed = this.getSoloMatchesPlayed() + this.getDuoMatchesPlayed() + this.getSquadMatchesPlayed();
        this.totalLastModified = this.getSoloLastModified();
        if (this.totalLastModified < this.getDuoLastModified()){
            this.totalLastModified = this.getDuoLastModified();
        } else if (this.totalLastModified < this.getSquadLastModified()){
            this.totalLastModified = this.getSquadLastModified();
        }
        this.totalWins = this.getSoloWins() + this.getDuoWins() + this.getSquadWins();
        this.totalMinutesPlayed = this.getSoloMinutesPlayed() + this.getDuoMinutesPlayed() + this.getSquadMinutesPlayed();
        this.totalKills = this.getSoloKills() + this.getDuoKills() + this.getSquadKills();
        this.totalKillDeathRatio = this.totalKills/((this.totalMatchesPlayed - this.totalWins) > 0 ? (this.totalMatchesPlayed - this.totalWins) : 1);
        this.totalWinRatio = (this.totalWins/(this.totalMatchesPlayed > 0 ? this.totalMatchesPlayed : 1)) * 100;
        this.totalKillsPerMinute = this.totalKills/(this.totalMinutesPlayed > 0 ? this.totalMinutesPlayed : 1);
        this.totalKillsPerMatch = this.totalKills/(this.totalMatchesPlayed > 0 ? this.totalMatchesPlayed : 1);
    }

    public double getSoloKillDeathRatio() {
        return soloKillDeathRatio;
    }

    public double getDuoKillDeathRatio() {
        return duoKillDeathRatio;
    }

    public double getSquadKillDeathRatio() {
        return squadKillDeathRatio;
    }

    public double getSoloWinRatio() {
        return soloWinRatio;
    }

    public double getDuoWinRatio() {
        return duoWinRatio;
    }

    public double getSquadWinRatio() {
        return squadWinRatio;
    }

    public double getSoloKillsPerMinute() {
        return soloKillsPerMinute;
    }

    public double getDuoKillsPerMinute() {
        return duoKillsPerMinute;
    }

    public double getSquadKillsPerMinute() {
        return squadKillsPerMinute;
    }

    public double getSoloKillsPerMatch() {
        return soloKillsPerMatch;
    }

    public double getDuoKillsPerMatch() {
        return duoKillsPerMatch;
    }

    public double getSquadKillsPerMatch() {
        return squadKillsPerMatch;
    }

    public double getTotalMatchesPlayed() {
        return totalMatchesPlayed;
    }

    public double getTotalLastModified() {
        return totalLastModified;
    }

    public double getTotalWins() {
        return totalWins;
    }

    public double getTotalMinutesPlayed() {
        return totalMinutesPlayed;
    }

    public double getTotalKills() {
        return totalKills;
    }

    public double getTotalKillDeathRatio() {
        return totalKillDeathRatio;
    }

    public double getTotalWinRatio() {
        return totalWinRatio;
    }

    public double getTotalKillsPerMinute() {
        return totalKillsPerMinute;
    }

    public double getTotalKillsPerMatch() {
        return totalKillsPerMatch;
    }

    public double getSoloScore(){
        return this.br_score_pc_m0_p2;
    }

    public double getDuoScore(){
        return this.br_score_pc_m0_p10;
    }

    public double getSquadScore(){
        return this.br_score_pc_m0_p9;
    }

    public double getSoloMatchesPlayed(){
        return this.br_matchesplayed_pc_m0_p2;
    }

    public double getDuoMatchesPlayed(){
        return this.br_matchesplayed_pc_m0_p10;
    }

    public double getSquadMatchesPlayed(){
        return this.br_matchesplayed_pc_m0_p9;
    }

    public long getSoloLastModified(){
        return this.br_lastmodified_pc_m0_p2;
    }

    public long getDuoLastModified(){
        return this.br_lastmodified_pc_m0_p10;
    }

    public long getSquadLastModified(){
        return this.br_lastmodified_pc_m0_p9;
    }

    public double getSoloWins(){
        return this.br_placetop1_pc_m0_p2;
    }

    public double getDuoWins(){
        return this.br_placetop1_pc_m0_p10;
    }

    public double getSquadWins(){
        return this.br_placetop1_pc_m0_p9;
    }

    public double getSoloMinutesPlayed(){
        return this.br_minutesplayed_pc_m0_p2;
    }

    public double getDuoMinutesPlayed(){
        return this.br_minutesplayed_pc_m0_p10;
    }

    public double getSquadMinutesPlayed(){
        return this.br_minutesplayed_pc_m0_p9;
    }

    public double getSoloKills(){
        return this.br_kills_pc_m0_p2;
    }

    public double getDuoKills(){
        return this.br_kills_pc_m0_p10;
    }

    public double getSquadKills(){
        return this.br_kills_pc_m0_p9;
    }

    public double getSquadTop3(){
        return this.br_placetop3_pc_m0_p9;
    }

    public double getSquadTop6(){
        return this.br_placetop6_pc_m0_p9;
    }

    public double getDuoTop12(){
        return this.br_placetop12_pc_m0_p10;
    }

    public double getDuoTop5(){
        return this.br_placetop5_pc_m0_p10;
    }

    public double getSoloTop25(){
        return this.br_placetop25_pc_m0_p2;
    }

    public double getSoloTop10(){
        return this.br_placetop10_pc_m0_p2;
    }
}
