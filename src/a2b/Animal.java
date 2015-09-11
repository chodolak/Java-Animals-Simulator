package a2b;

public abstract class Animal extends Lifeform {
    // Sets up the Animal with what cell they are in, what to eat, mates, empty
    // cells, and food.
    public Animal(Cell cell, int eat, int numMate, int numEmpty, int numFood) {
        super(cell, eat, numMate, numEmpty, numFood);
    }
}
