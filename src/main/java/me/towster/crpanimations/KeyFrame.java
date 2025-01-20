package me.towster.crpanimations;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;

public class KeyFrame {
    /*
    Storage class
     */

    int startTime;
    int endTime;
    EulerAngle startPosition;
    EulerAngle endPosition;
    LimbType limb;

    public KeyFrame(int startTime, int endTime, EulerAngle startPosition, EulerAngle endPosition, LimbType limb) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.limb = limb;
    }

    public void move(ArmorStand armorStand, int time) {
        if (time < startTime || time > endTime) return;

        float t = (float) (time - startTime) / (endTime - startTime);
        EulerAngle result = new EulerAngle(
                startPosition.getX() + (endPosition.getX() - startPosition.getX()) * t,
                startPosition.getY() + (endPosition.getY() - startPosition.getY()) * t,
                startPosition.getZ() + (endPosition.getZ() - startPosition.getZ()) * t);

        // TODO add more limbs
        switch (limb) {
            case RIGHT_ARM:
                armorStand.setRightArmPose(result);
                break;
        }
    }
}
