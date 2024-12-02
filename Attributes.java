public interface Attributes {
    // Gets armor attribute value
    int getArmor();
    // Gets maximum hit point attribute
    int getMaxHP();
    // Gets the Damage Die Type
    DiceType getDamageDie();
    // A whole number of any int value to apply when making hit rolls.
    int getHitModifier();
}