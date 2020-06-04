/* This file is part of Gralog, Copyright (c) 2020 Anthony Kozar; (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.chipfiring.algorithm;


import gralog.chipfiring.structure.*;

import java.util.Set;

import gralog.algorithm.*;
import gralog.progresshandler.ProgressHandler;
import gralog.structure.*;

/**
 *
 */
@AlgorithmDescription(
    name = "Randomly Distribute Chips",
    text = "Assigns each vertex a random number of chips",
    url = ""
)
public class RandomlyDistributeChips extends Algorithm {

    public Object run(ChipFiringGame game, AlgorithmParameters ap, Set<Object> selection, ProgressHandler onprogress)
    		throws Exception
    {
        int	totalchips = 0, degree, chips, order;
        ChipFiringGamePosition p = null;
        
        for (Vertex v : game.getVertices()) {
            if (!(v instanceof ChipFiringGamePosition))  continue;	// TODO: should output warning?
            p = (ChipFiringGamePosition) v;
        
        	// assign each vertex between [0, 2*degree) chips
            degree = p.getDegree();
            chips = (int)(2 * degree * Math.random() * Math.random());  // weight distribution towards low values
            p.setChips(chips);
            totalchips += chips;
		}
		
		// To keep things interesting, ensure that there are not fewer chips
		// in the graph than the number of vertices. (?)
		order = game.getVertices().size();
		if (totalchips < order && p != null) {
			// add the difference to the last vertex
			int diff = order - totalchips;
			p.setChips(p.getChips() + diff);
		}
		
		return null;
	}

}
