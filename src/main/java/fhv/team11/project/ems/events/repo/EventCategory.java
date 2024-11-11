package fhv.team11.project.ems.events.repo;

public enum EventCategory {
    SKI("Ski Courses"),
    CANYONING("Canyoning"),
    CAMPING("Camping"),
    TREKKING("Trekking"),
    GUIDED_HIKES("Guided Hikes"),
    FOOTBALL_TOURNAMENTS("Football Tournaments"),
    GLACIER_COURSES("Glacier Courses"),
    AVALANCHE_COURSES("Avalanche Courses");

    private final String name;

    EventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

