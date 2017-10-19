//convert Plane to String
package Imports.Eng;

import BusinessObject.Plane;

public class ConvertPlane {
    public static Plane convertPlaneEnum(String plane){
        Plane p = null;
        switch(plane){
            case "AIRBUSA350": p = Plane.AIRBUSA350; break;
            case "AIRBUSA280": p = Plane.AIRBUSA280; break;
            case "BOEING737": p = Plane.BOEING737; break;
            case "BOEING747": p = Plane.BOEING747; break;
        }
        return p;
    }
}
