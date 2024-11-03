package fhv.team11.project.ems.events.repo;

public enum Category {
    SKI("Ski Courses");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
