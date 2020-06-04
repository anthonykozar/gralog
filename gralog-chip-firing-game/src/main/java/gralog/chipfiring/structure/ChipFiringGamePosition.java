/* This file is part of Gralog, Copyright (c) 2020 Anthony Kozar; (c) 2016-2018 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.chipfiring.structure;

import java.util.Set;

import gralog.preferences.Configuration;
import gralog.structure.*;
import gralog.plugins.XmlName;
import gralog.rendering.GralogColor;
import gralog.rendering.GralogGraphicsContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@XmlName(name = "chipfiringgameposition")
public class ChipFiringGamePosition extends Vertex {

    protected int	numChips = 0;

    public ChipFiringGamePosition() {
        super();
    }

    public ChipFiringGamePosition(Configuration config) {
        super(config);
    }
	
	public int getChips() {
		return numChips;
	}
	
	public ChipFiringGamePosition setChips(int num) {
		if (num > 0) {
			numChips = num;
		}
		else {
			numChips = 0;
		}
		return this;
	}
	
	/** "Fires" this vertex, distributing one chip to each of its neighbors.
	    Does nothing if the number of chips on this vertex is less than its degree.
	 */
	public ChipFiringGamePosition fire() {
		if (numChips >= this.getDegree()) {
			ChipFiringGamePosition p;
			Set<Vertex> neighbors = this.getNeighbours();
			
			for (Vertex v : neighbors) {
				if (!(v instanceof ChipFiringGamePosition))  continue;	// TODO: should output warning?
				p = (ChipFiringGamePosition) v;
				p.setChips(p.getChips() + 1);
			}
			
			setChips(numChips - this.getDegree());
		}
		return this;
	}
	
    @Override
    public Element toXml(Document doc, String id) throws Exception {
        Element vnode = super.toXml(doc, id);
        vnode.setAttribute("chips", Integer.toString(numChips));
        return vnode;
    }

    @Override
    public String fromXml(Element vnode) {
        String id = super.fromXml(vnode);
        if (vnode.hasAttribute("chips")) {
        	Integer val = Integer.valueOf(vnode.getAttribute("chips"));
            setChips(val.intValue());
        }
        return id;
    }

    @Override
    public void render(GralogGraphicsContext gc, Highlights highlights) {
        super.render(gc, highlights);
        
        // draw the number of chips near the top of the vertex
        gc.putText(getCoordinates().getX(), getCoordinates().getY()-(radius*0.4), 
        		   Integer.toString(numChips), textHeight*0.5, GralogColor.BLUE);
    }

    @Override
    public boolean containsCoordinate(double x, double y) {
        return (getCoordinates().get(0) - radius <= x)
            && (x <= getCoordinates().get(0) + radius)
            && (getCoordinates().get(1) - radius <= y)
            && (y <= getCoordinates().get(1) + radius);
    }
}
