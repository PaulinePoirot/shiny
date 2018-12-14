package android.shiny.pojo;

import java.util.List;

public class getPokemonStats {
    List<Stat> stats;

    public List<Stat> getStats() {
        return stats;
    }

    public class Stat {
        int base_stat;

        public int getBaseStat() {
            return base_stat;
        }
    }
}
