package au.edu.sydney.soft3202.tutorials.week8.quiz;

public class Animal {
    private String name;
    private String species;
    private String colour;
    private String age;
    private String animalClass;
    private String numLegs;
    private int key;

    public Animal(int key) {
        this.name = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.NAME);
        this.species = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.SPECIES);
        this.colour = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.COLOUR);
        this.age = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.AGE);
        this.animalClass = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.ANIMAL_CLASS);
        this.numLegs = null;//AnimalDataSource.getData(key, AnimalDataSource.Field.NUM_LEGS);
        this.key = key;
    }
    
    

    public String getName() {
    	if(this.name == null) {
    		this.name = AnimalDataSource.getData(key, AnimalDataSource.Field.NAME);
    		return name;
    	}
        return name;
    }

    public String getSpecies() {
    	if(this.species == null) {
    		this.species = AnimalDataSource.getData(key, AnimalDataSource.Field.SPECIES);
    		return species;
    	}
        return species;
    }

    public String getColour() {
    	if(this.colour == null) {
    		this.colour = AnimalDataSource.getData(key, AnimalDataSource.Field.COLOUR);
    		return colour;
    	}
        return colour;
    }

    public String getAge() {
    	if(this.age == null) {
    		this.age = AnimalDataSource.getData(key, AnimalDataSource.Field.AGE);
    		return age;
    	}
        return age;
    }

    public String getAnimalClass() {
    	if(this.animalClass == null) {
    		this.animalClass = AnimalDataSource.getData(key, AnimalDataSource.Field.ANIMAL_CLASS);
    		return animalClass;
    	}
        return animalClass;
    }

    public String getNumLegs() {
    	if(this.numLegs == null) {
    		this.numLegs = AnimalDataSource.getData(key, AnimalDataSource.Field.NUM_LEGS);
    		return numLegs;
    	}
        return numLegs;
    }
}
