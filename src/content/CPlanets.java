package content;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.util.noise.Simplex;
import arc.util.Tmp;
import mindustry.type.Planet;
import mindustry.graphics.Shaders;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexMesher;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;

public class CPlanets {

    public static Planet Shangi, sun;

    public static void load() {
        Shangi = new CPlanet("Shangi", sun , 1, 5){{
            bloom = true;
            visible = true;
            accessible = true;
            hasAtmosphere = true;
            alwaysUnlocked = true;
        }};

    }

    public static class CPlanet extends Planet {
        public CPlanet(String name, Planet parent, float radius, int sectorSize) {
            super(name, parent, radius, sectorSize);
        }
        
    }

    public static class CMesh extends HexMesh {
        
        public CMesh(Planet planet, int divisions, double octaves, double persistence, double scl, double pow, double mag, float colorScale, Color... colors) {
            super(planet, new HexMesher() {
                @Override
                public float getHeight(Vec3 position) {
                    position = Tmp.v33.set(position).scl(4f);
                    float height = (Mathf.pow(Simplex.noise3d(123, 7, 0.5f, 1f/3f, position.x, position.y, position.z), 2.3f) + 0.05f) / (1f + 0.05f);
                    return Math.max(height, 0.05f);
                }

                @Override
                public Color getColor(Vec3 position) {
                    double height = Math.pow(Simplex.noise3d(1, octaves, persistence, scl, position.x, position.y, position.z), pow) * mag;
                    return Tmp.c1.set(colors[Mathf.clamp((int)(height * colors.length), 0, colors.length -1)]).mul(colorScale);
                }
            }, divisions, Shaders.unlit);
        }
    }

}
