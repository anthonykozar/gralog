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
    name = "Fire Selected Vertex",
    text = "Fires just the selected vertex",
    url = ""
)
public class FireSelectedVertex extends Algorithm {
	
    public Object run(ChipFiringGame game, AlgorithmParameters ap, Set<Object> selection, ProgressHandler onprogress)
    		throws Exception
    {
        ChipFiringGamePosition p = null;
        
        Vertex v = selectedUniqueVertex(selection);
        if (v == null)  return "Please select exactly one vertex to fire.";

        if (v instanceof ChipFiringGamePosition) {
			p = (ChipFiringGamePosition) v;
			p.fire();
		}
		else {
			// TODO: should output warning?
		}
		
		return null;
	}

}
