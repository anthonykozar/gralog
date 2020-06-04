/* This file is part of Gralog, Copyright (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.chipfiring.structure;

import gralog.plugins.XmlName;
import gralog.preferences.Configuration;
import gralog.structure.*;

@StructureDescription(
    name = "Chip-Firing Game",
    text = "A one-player game played on an undirected graph.",
    url = "https://en.wikipedia.org/wiki/Chip-firing_game")
@XmlName(name = "chipfiringgame")
public class ChipFiringGame extends Structure<ChipFiringGamePosition, Edge> {

    @Override
    public ChipFiringGamePosition createVertex() {
        return new ChipFiringGamePosition();
    }

    @Override
    public ChipFiringGamePosition createVertex(Configuration config) {
        return new ChipFiringGamePosition(config);
    }

    @Override
    public Edge createEdge(Configuration config) {
    	Edge e = new Edge(config);
    	e.setDirectedness(false);
        return e;
    }
}
