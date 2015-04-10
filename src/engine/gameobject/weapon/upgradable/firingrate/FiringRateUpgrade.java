package engine.gameobject.weapon.upgradable.firingrate;

import java.util.Optional;
import engine.gameobject.weapon.Upgrade;

public class FiringRateUpgrade implements FiringRate {
    
    private double increment;
    private Optional<FiringRate> decorated;

    public FiringRateUpgrade() {
        increment = 0;
    }
    
    public FiringRateUpgrade (double increment) {
        this.increment = increment;
    }

    @Override
    public double getRate () {
        return decorated.map(FiringRate::getRate).orElse(increment);
    }
    
    @Override
    public Class<? extends Upgrade> getType () {
        return FiringRate.class;
    }

    @Override
    public void setDecorated (Upgrade decorated) {
        this.decorated = Optional.of((FiringRate) decorated);
    }

    @Override
    public void setDefault () {
        this.decorated = Optional.of(new FiringRateUpgrade(0));
    }

}