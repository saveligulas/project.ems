package fhv.team11.project.ems.commons.database;

public interface IDatabaseForeignKeyMapper<FE, FID> {
    FID saveForeignEntity(FE foreignEntity);
}