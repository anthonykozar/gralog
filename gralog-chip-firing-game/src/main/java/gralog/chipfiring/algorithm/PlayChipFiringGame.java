/* This file is part of Gralog, Copyright (c) 2020 Anthony Kozar; (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.chipfiring.algorithm;


import gralog.chipfiring.structure.*;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import gralog.algorithm.*;
import gralog.progresshandler.ProgressHandler;
import gralog.structure.*;

/**
 *
 */
@AlgorithmDescription(
    name = "Play Chip-Firing Game",
    text = "Randomly fires vertices until a stable state is reached (or max firings)",
    url = ""
)
public class PlayChipFiringGame extends Algorithm {
	
	public final int MAX_FIRINGS = 100;
	
    public Object run(ChipFiringGame game, AlgorithmParameters ap, Set<Object> selection, ProgressHandler onprogress)
    		throws Exception
    {
        int	numfirings = 0;
        List<ChipFiringGamePosition> fireable = new ArrayList<ChipFiringGamePosition>();
        ChipFiringGamePosition p = null;
        boolean reachedStableState = false;
        
        while (!reachedStableState && numfirings < MAX_FIRINGS) {
			// collect fireable vertices 
			for (Vertex v : game.getVertices()) {
				if (!(v instanceof ChipFiringGamePosition))  continue;	// TODO: should output warning?
				p = (ChipFiringGamePosition) v;
				if (p.getChips() >= p.getDegree()) {
					fireable.add(p);
				}
			}
			
			if (fireable.size() > 0) {
				// choose a random fireable vertex to fire
				int idxfiring = (int)(fireable.size() * Math.random());
				p = fireable.get(idxfiring);
				p.fire();
				++numfirings;
			}
			else reachedStableState = true;
		}
		
		return null;
	}

}
