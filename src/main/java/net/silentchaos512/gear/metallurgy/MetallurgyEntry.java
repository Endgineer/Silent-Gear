package net.silentchaos512.gear.metallurgy;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MetallurgyEntry {
    private static HashMap<String, MetallurgyEntry> REGISTRY = new HashMap<>();

    private Reinforce[] reinforces;

    public static MetallurgyEntry get(String resourcename) {
        return MetallurgyEntry.REGISTRY.get(resourcename);
    }

    public Reinforce getReinforce(int level) {
        return this.reinforces[level-1];
    }

    private MetallurgyEntry(JsonArray reinforces) {
        this.reinforces = new Reinforce[reinforces.size()];
        for(int i = 0; i < reinforces.size(); i++) {
            JsonObject reinforce = reinforces.get(i).getAsJsonObject();
            JsonObject xp_gain = reinforce.getAsJsonObject("xp_gain");
            JsonObject heat_loss = reinforce.getAsJsonObject("heat_loss");
            JsonObject folding_range = reinforce.getAsJsonObject("folding_range");
            this.reinforces[i] = new Reinforce(
                i,
                reinforce.get("xp_trip_ingot").getAsInt(),
                reinforce.get("heat_capacity").getAsInt(),
                folding_range.get("min").getAsInt(),
                folding_range.get("max").getAsInt(),
                reinforce.get("malleability_midpoint").getAsInt(),
                xp_gain.get("min").getAsDouble(),
                xp_gain.get("max").getAsDouble(),
                reinforce.get("malleability_slope").getAsDouble(),
                heat_loss.get("min").getAsDouble(),
                heat_loss.get("max").getAsDouble(),
                reinforce.get("cooling_slope").getAsDouble()
            );
        }
    }

    public static void register(String resourcename, JsonElement jsonelement) {
        JsonObject jsonobject = jsonelement.getAsJsonObject();

        MetallurgyEntry.REGISTRY.put(resourcename.split(":")[1], new MetallurgyEntry(jsonobject.getAsJsonArray("reinforces")));
    }

    public class Reinforce {
        private int xp_total_ingot;
        private int xp_trip_ingot;
        private int heat_capacity;
        private int folding_range_min;
        private int folding_range_max;
        private int malleability_midpoint;
        private double xp_gain_min;
        private double xp_gain_max;
        private double malleability_slope;
        private double heat_loss_min;
        private double heat_loss_max;
        private double cooling_slope;

        private double malleability_error_slope;
        private double malleability_error_intercept;
        private double cooling_error_slope;
        private double cooling_error_intercept;

        public Reinforce(int index, int xp_trip_ingot, int heat_capacity, int folding_range_min, int folding_range_max, int malleability_midpoint, double xp_gain_min, double xp_gain_max, double malleability_slope, double heat_loss_min, double heat_loss_max, double cooling_slope) {
            this.xp_total_ingot = (int) Math.pow(2, index) * xp_trip_ingot;
            this.xp_trip_ingot = xp_trip_ingot;
            this.heat_capacity = heat_capacity;
            this.folding_range_min = folding_range_min;
            this.folding_range_max = folding_range_max;
            this.malleability_midpoint = malleability_midpoint;
            this.xp_gain_min = xp_gain_min;
            this.xp_gain_max = xp_gain_max+1;
            this.malleability_slope = malleability_slope;
            this.heat_loss_min = heat_loss_min;
            this.heat_loss_max = heat_loss_max;
            this.cooling_slope = cooling_slope;

            this.malleability_error_intercept = -this.malleabilityPrenormalized(0);
            this.malleability_error_slope = (1.0 - this.malleabilityPrenormalized(this.heat_capacity) - this.malleability_error_intercept) / this.heat_capacity;
            this.cooling_error_intercept = this.heat_loss_min - this.coolingPrenormalized(0);
            this.cooling_error_slope = (this.heat_loss_max - this.coolingPrenormalized(this.heat_capacity) - this.cooling_error_intercept) / this.heat_capacity;
        }

        private double malleabilityNormalized(double heat) {
            return this.malleabilityPrenormalized(heat) + this.malleabilityError(heat);
        }

        private double malleabilityPrenormalized(double heat) {
            return 1.0 / (1.0 + Math.exp(this.malleability_slope * (this.malleability_midpoint - heat)));
        }

        private double malleabilityError(double heat) {
            return this.malleability_error_slope * heat + this.malleability_error_intercept;
        }

        private double coolingNormalized(double heat) {
            return this.coolingPrenormalized(heat) + this.coolingError(heat);
        }

        private double coolingPrenormalized(double heat) {
            return this.heat_loss_min + (this.heat_loss_max - this.heat_loss_min) * Math.exp(-this.cooling_slope * (this.heat_capacity - heat));
        }

        private double coolingError(double heat) {
            return this.cooling_error_slope * heat + this.cooling_error_intercept;
        }

        public double getXPGain(double heat) { return ThreadLocalRandom.current().nextDouble(this.xp_gain_min, this.xp_gain_max) * this.malleabilityNormalized(heat); }

        public double getHeatLoss(double heat) { return this.coolingNormalized(heat); }

        public int getXPTrip(int ingots) { return this.xp_trip_ingot * ingots; }

        public int getXPTotal(int ingots) { return this.xp_total_ingot * ingots; }

        public int getHeatCapacity() { return this.heat_capacity; }

        public double getMalleability(double heat) { return this.malleabilityNormalized(heat); }

        public int compareFoldingRange(double heat) { return heat < this.folding_range_min ? -1 : (heat > this.folding_range_max ? 1 : 0); }
    }
}
