package com.xilixir.fortniteapi.Frame;

import java.util.HashMap;
import java.util.Map;

public class AlltimeStats {
    private double soloMatchesPlayed; // br_matchesplayed_pc_m0_p2
    private double duoMatchesPlayed; // br_matchesplayed_pc_m0_p10
    private double squadMatchesPlayed; // br_matchesplayed_pc_m0_p9
    private long soloLastModified; // br_lastmodified_pc_m0_p2
    private long duoLastModified; // br_lastmodified_pc_m0_p10
    private long squadLastModified; // br_lastmodified_pc_m0_p9
    private double soloWins; // br_placetop1_pc_m0_p2
    private double duoWins; // br_placetop1_pc_m0_p10
    private double squadWins; // br_placetop1_pc_m0_p9
    private long soloMinutesPlayed; // br_minutesplayed_pc_m0_p2
    private long duoMinutesPlayed; // br_minutesplayed_pc_m0_p10
    private long squadMinutesPlayed; // br_minutesplayed_pc_m0_p9
    private double soloKills; // br_kills_pc_m0_p2
    private double duoKills; // br_kills_pc_m0_p10
    private double squadKills; // br_kills_pc_m0_p9
    private double squadTop3; // br_placetop3_pc_m0_p9
    private double squadTop6; // br_placetop6_pc_m0_p9
    private double duoTop12; // br_placetop12_pc_m0_p10
    private double duoTop5; // br_placetop5_pc_m0_p10
    private double soloTop25; // br_placetop25_pc_m0_p2
    private double soloTop10; // br_placetop10_pc_m0_p2
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

    public AlltimeStats(Stat[] stats){
        Map<String, Stat> mappedByName = new HashMap<>();
        for (Stat stat : stats){
            mappedByName.put(stat.getName(), stat);
        }

        this.soloMatchesPlayed = (int) getValue(mappedByName, "br_matchesplayed_pc_m0_p2");
        this.duoMatchesPlayed = (int) getValue(mappedByName, "br_matchesplayed_pc_m0_p10");
        this.squadMatchesPlayed = (int) getValue(mappedByName, "br_matchesplayed_pc_m0_p9");

        this.soloLastModified = getValue(mappedByName, "br_lastmodified_pc_m0_p2");
        this.duoLastModified = getValue(mappedByName, "br_lastmodified_pc_m0_p10");
        this.squadLastModified = getValue(mappedByName, "br_lastmodified_pc_m0_p9");

        this.soloWins = (int) getValue(mappedByName, "br_placetop1_pc_m0_p2");
        this.duoWins = (int) getValue(mappedByName, "br_placetop1_pc_m0_p10");
        this.squadWins = (int) getValue(mappedByName, "br_placetop1_pc_m0_p9");

        this.soloMinutesPlayed = getValue(mappedByName, "br_minutesplayed_pc_m0_p2");
        this.duoMinutesPlayed = getValue(mappedByName, "br_minutesplayed_pc_m0_p10");
        this.squadMinutesPlayed = getValue(mappedByName, "br_minutesplayed_pc_m0_p9");

        this.soloKills = (int) getValue(mappedByName, "br_kills_pc_m0_p2");
        this.duoKills = (int) getValue(mappedByName, "br_kills_pc_m0_p10");
        this.squadKills = (int) getValue(mappedByName, "br_kills_pc_m0_p9");

        this.squadTop3 = (int) getValue(mappedByName, "br_placetop3_pc_m0_p9");
        this.squadTop6 = (int) getValue(mappedByName, "br_placetop6_pc_m0_p9");
        this.duoTop12 = (int) getValue(mappedByName, "br_placetop12_pc_m0_p10");
        this.duoTop5 = (int) getValue(mappedByName, "br_placetop5_pc_m0_p10");
        this.soloTop25 = (int) getValue(mappedByName, "br_placetop25_pc_m0_p2");
        this.soloTop10 = (int) getValue(mappedByName, "br_placetop10_pc_m0_p2");
        this.calculateStuff();
    }

    private void calculateStuff(){
        // kills/death
        this.soloKillDeathRatio = this.soloKills/((this.soloMatchesPlayed - this.soloWins) > 0 ? (this.soloMatchesPlayed - this.soloWins) : 1);
        this.duoKillDeathRatio = this.duoKills/((this.duoMatchesPlayed - this.duoWins) > 0 ? (this.duoMatchesPlayed - this.duoWins) : 1);
        this.squadKillDeathRatio = this.squadKills/((this.squadMatchesPlayed - this.squadWins) > 0 ? (this.squadMatchesPlayed - this.squadWins) : 1);

        // winrate
        this.soloWinRatio = (this.soloWins/(this.soloMatchesPlayed > 0 ? this.soloMatchesPlayed : 1)) * 100;
        this.duoWinRatio = (this.duoWins/(this.duoMatchesPlayed > 0 ? this.duoMatchesPlayed : 1)) * 100;
        this.squadWinRatio = (this.squadWins/(this.squadMatchesPlayed > 0 ? this.squadMatchesPlayed : 1)) * 100;

        // kills/minute
        this.soloKillsPerMinute = this.soloKills/(this.soloMinutesPlayed > 0 ? this.soloMinutesPlayed : 1);
        this.duoKillsPerMinute = this.duoKills/(this.duoMinutesPlayed > 0 ? this.duoMinutesPlayed : 1);
        this.squadKillsPerMinute = this.squadKills/(this.squadMinutesPlayed > 0 ? this.squadMinutesPlayed : 1);

        // kills/match
        this.soloKillsPerMatch = this.soloKills/(this.soloMatchesPlayed > 0 ? this.soloMatchesPlayed : 1);
        this.duoKillsPerMatch = this.duoKills/(this.duoMatchesPlayed > 0 ? this.duoMatchesPlayed : 1);
        this.squadKillsPerMatch = this.squadKills/(this.squadMatchesPlayed> 0 ? this.squadMatchesPlayed: 1);

        // total
        this.totalMatchesPlayed = this.soloMatchesPlayed + this.duoMatchesPlayed + this.squadMatchesPlayed;
        this.totalLastModified = this.soloLastModified;
        if (this.totalLastModified < this.duoLastModified){
            this.totalLastModified = this.duoLastModified;
        } else if (this.totalLastModified < this.squadLastModified){
            this.totalLastModified = this.squadLastModified;
        }
        this.totalWins = this.soloWins + this.duoWins + this.squadWins;
        this.totalMinutesPlayed = this.soloMinutesPlayed + this.duoMinutesPlayed + this.squadMinutesPlayed;
        this.totalKills = this.soloKills + this.duoKills + this.squadKills;
        this.totalKillDeathRatio = this.totalKills/((this.totalMatchesPlayed - this.totalWins) > 0 ? (this.totalMatchesPlayed - this.totalWins) : 1);
        this.totalWinRatio = (this.totalWins/(this.totalMatchesPlayed > 0 ? this.totalMatchesPlayed : 1)) * 100;
        this.totalKillsPerMinute = this.totalKills/(this.totalMinutesPlayed > 0 ? this.totalMinutesPlayed : 1);
        this.totalKillsPerMatch = this.totalKillsPerMinute/(this.totalMatchesPlayed > 0 ? this.totalMatchesPlayed : 1);
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

    public double getSoloMatchesPlayed() {
        return soloMatchesPlayed;
    }

    public double getDuoMatchesPlayed() {
        return duoMatchesPlayed;
    }

    public double getSquadMatchesPlayed() {
        return squadMatchesPlayed;
    }

    public double getSoloLastModified() {
        return soloLastModified;
    }

    public double getDuoLastModified() {
        return duoLastModified;
    }

    public double getSquadLastModified() {
        return squadLastModified;
    }

    public double getSoloWins() {
        return soloWins;
    }

    public double getDuoWins() {
        return duoWins;
    }

    public double getSquadWins() {
        return squadWins;
    }

    public double getSoloMinutesPlayed() {
        return soloMinutesPlayed;
    }

    public double getDuoMinutesPlayed() {
        return duoMinutesPlayed;
    }

    public double getSquadMinutesPlayed() {
        return squadMinutesPlayed;
    }

    public double getSoloKills() {
        return soloKills;
    }

    public double getDuoKills() {
        return duoKills;
    }

    public double getSquadKills() {
        return squadKills;
    }

    public double getSquadTop3() {
        return squadTop3;
    }

    public double getSquadTop6() {
        return squadTop6;
    }

    public double getDuoTop12() {
        return duoTop12;
    }

    public double getDuoTop5() {
        return duoTop5;
    }

    public double getSoloTop25() {
        return soloTop25;
    }

    public double getSoloTop10() {
        return soloTop10;
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

    private long getValue(Map<String, Stat> map, String key){
        return map.containsKey(key) ? map.get(key).getValue() : 0;
    }
}
