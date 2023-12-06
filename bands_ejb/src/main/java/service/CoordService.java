package service;

import entity.Coordinates;

public interface CoordService {

    Coordinates getCoord(String id);

    String helloFromEjb();
}
