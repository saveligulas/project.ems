package fhv.team11.project.ems.events.repo;

public enum EventCategory {
    SKI("Ski Courses");

    private final String name;

    EventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
