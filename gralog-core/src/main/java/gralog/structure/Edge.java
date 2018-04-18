/* This file is part of Gralog, Copyright (c) 2016-2017 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later. */
package gralog.structure;

import gralog.plugins.*;
import gralog.events.*;
import gralog.rendering.*;

import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 */
@XmlName(name = "edge")
public class Edge extends XmlMarshallable implements IMovable {

    public static double multiEdgeOffset = 0.25;
    private static final double SELECTION_WIDTH = 0.3;

    Set<EdgeListener> listeners = new HashSet<>();

    public String label = "";
    public double cost = 1.0d;

    public Boolean isDirected = true;

    public Arrow arrowType = Arrow.TYPE1;
    public double arrowHeadLength = 0.2d; // cm
    public double arrowHeadAngle = 40d; // degrees
    public double width = 2.54 / 96; // cm
    public GralogColor color = GralogColor.BLACK;

    public ArrayList<Edge> siblings = new ArrayList<>();
    public ArrayList<EdgeIntermediatePoint> intermediatePoints = new ArrayList<>();

    private Vertex source = null;
    private Vertex target = null;

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        if (this.source != null)
            this.source.disconnectEdge(this);
        this.source = source;
        if (source != null)
            this.source.connectEdge(this);
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        if (this.target != null)
            this.target.disconnectEdge(this);
        this.target = target;
        if (target != null)
            this.target.connectEdge(this);
    }
    public boolean isLoop(){
        return getSource() == getTarget();
    }
    public double maximumCoordinate(int dimension) {
        double result = Double.NEGATIVE_INFINITY;
        for (EdgeIntermediatePoint between : intermediatePoints)
            result = Math.max(result, between.get(dimension));
        return result;
    }

    @Override
    public void move(Vector2D offset) {
        for (EdgeIntermediatePoint between : intermediatePoints)
            between.move(offset);
    }

    public void collapse(Structure structure){
        //One edge that doesn't have the same direction as this edge
        Edge e = null;
        for(int i = 0; i < siblings.size(); i++){
            e = siblings.get(i);
            if(e != this && !e.sameOrientationAs(this)){
                break;
            }
            e = null;
        }
        for(int i = 0; i < siblings.size(); i++){
            if(siblings.get(i) != this && siblings.get(i) != e){
                structure.removeEdge(siblings.get(i), false);
            }
        }
        siblings.clear();
        siblings = new ArrayList<>();
        siblings.add(this);

        //correct siblings of edge e as well
        if(e != null){
            siblings.add(e);

            e.siblings.clear();
            e.siblings = new ArrayList<>();
            e.siblings.add(this);
            e.siblings.add(e);
        }
    }
    public void snapToGrid(double gridSize) {
        for (EdgeIntermediatePoint between : intermediatePoints)
            between.snapToGrid(gridSize);
    }

    public IMovable findObject(double x, double y) {
        for (EdgeIntermediatePoint p : intermediatePoints)
            if (p.containsCoordinate(x, y))
                return p;

        if (this.containsCoordinate(x, y))
            return this;

        return null;
    }

    protected double binomialCoefficients(int n, int k) {
        double result = 1.0;
        for (int i = 1; i <= k; i++)
            result = result * (n + 1 - i) / i;
        return result;
    }

    protected Vector2D bezierCurve(double t) {
        int n = intermediatePoints.size() + 1;

        Vector2D result = this.source.coordinates.multiply(Math.pow(1 - t, n));
        result = result.plus(this.target.coordinates.multiply(Math.pow(t, n)));

        int i = 1;
        for (EdgeIntermediatePoint between : intermediatePoints) {
            result = result.plus(between.coordinates.multiply(binomialCoefficients(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i)
            ));
            i++;
        }

        return result;
    }

    public void render(GralogGraphicsContext gc, Highlights highlights){

        GralogColor edgeColor = highlights.isSelected(this) ? GralogColor.RED : this.color;

        if(isLoop()){

            double angleStart = source.loopAnchor - source.loopAngle;
            double angleEnd = source.loopAnchor + source.loopAngle;

            double r = source.radius;

            Vector2D intersection = Vector2D.getVectorAtAngle(angleStart, r).plus(source.coordinates);
            Vector2D intersection2 = Vector2D.getVectorAtAngle(angleEnd, r).plus(source.coordinates);

            Vector2D tangentToIntersection = Vector2D.getVectorAtAngle(angleEnd, 1).multiply(-1);

            //the correction retreats the endpoint of the bezier curve orthogonally from the vertex surface
            double correction = arrowType.endPoint * arrowHeadLength;

            //only draw arrow for directed graphs
            if(isDirected){
                gc.arrow(tangentToIntersection, intersection2, arrowType, arrowHeadLength, edgeColor);
            }else{
                correction = 0;
            }

            //Loop description, endpoints and tangents.
            GralogGraphicsContext.Loop l = new GralogGraphicsContext.Loop();
            l.start = intersection;
            l.end = intersection2;
            l.tangentStart = Vector2D.getVectorAtAngle(angleStart, 1).orthogonal();
            l.tangentEnd = Vector2D.getVectorAtAngle(angleEnd, 1).orthogonal();

            gc.loop(l,1.5, correction, edgeColor, width);
            return;
        }
        double offset = getOffset();

        Vector2D diff = target.coordinates.minus(source.coordinates);
        Vector2D perpendicularToEdge = diff.orthogonal(1).normalized().multiply(offset);

        Vector2D sourceOffset = source.coordinates.plus(perpendicularToEdge);
        Vector2D targetOffset = target.coordinates.plus(perpendicularToEdge);

        double fromX = sourceOffset.getX();
        double fromY = sourceOffset.getY();
        double toX = targetOffset.getX();
        double toY = targetOffset.getY();

        if (isDirected) {
            double dist = target.radius * (1 - Math.cos(Math.asin(offset/target.radius)));
            Vector2D intersection = target.intersectionAdjusted(sourceOffset, targetOffset, dist);

            gc.line(sourceOffset, intersection.plus(diff.normalized().multiply(arrowType.endPoint * arrowHeadLength)), edgeColor, width);
            gc.arrow(diff, intersection, arrowType, arrowHeadLength, edgeColor);
        } else {
            gc.line(fromX, fromY, toX, toY, edgeColor, width);
        }
    }
    public double getOffset(){
        double offset = 0;
        int index = siblings.indexOf(this);
        //offset both edges orthogonally, offsets differently when both face same direction
        if(siblings.size() == 2){
            offset = 0.5 * multiEdgeOffset;
            if(index == 1){
                if(siblings.get(0).sameOrientationAs(this)){
                    offset *= -1;
                }
            }

        }
        if(siblings.size() == 3){
            if(index == 1){
                offset = 0;
            }else if(index == 0){
                offset = multiEdgeOffset;
            }else if(index == 2){
                offset = (siblings.get(0).sameOrientationAs(this) ? -1 : 1) * multiEdgeOffset;
            }
        }
        if(siblings.size() == 4){
            int sameOrientationCount = 0;
            double offsetMultiplier;
            for (int i = 0; i < siblings.size(); i++)
            {
                if(i == index){
                    int correctedOffsetCounter = (sameOrientationCount >= 2 ? -(i - 1) : (sameOrientationCount + 1));
                    if(Math.abs(correctedOffsetCounter) > 1){
                        offsetMultiplier = 0.75;
                    }else{
                        offsetMultiplier = 0.5;
                    }
                    offset = offsetMultiplier * correctedOffsetCounter * multiEdgeOffset;
                    break;
                }
                if(siblings.get(i).sameOrientationAs(this)){
                    sameOrientationCount++;
                }

            }
        }
        return offset;
    }
    public boolean sameOrientationAs(Edge other){
        return getSource() == other.getSource();
    }
    public boolean containsCoordinate(double x, double y) {
        Vector2D diff = target.coordinates.minus(source.coordinates);
        Vector2D perpendicularToDiff = diff.orthogonal(1).normalized().multiply(getOffset());
        Vector2D sourceOffset = source.coordinates.plus(perpendicularToDiff);
        Vector2D targetOffset = target.coordinates.plus(perpendicularToDiff);
        
        double fromX = sourceOffset.getX();
        double fromY = sourceOffset.getY();
        double nextfromX = fromX;
        double nextfromY = fromY;

        for (EdgeIntermediatePoint between : intermediatePoints) {
            fromX = nextfromX;
            fromY = nextfromY;
            nextfromX = between.getX();
            nextfromY = between.getY();
            if (Vector2D.distancePointToLine(x, y, fromX, fromY, nextfromX, nextfromY) < 0.3)
                return true;
        }

        double toX = targetOffset.getX();
        double toY = targetOffset.getY();
        return Vector2D.distancePointToLine(x, y, nextfromX, nextfromY, toX, toY) < multiEdgeOffset * 0.5;
    }

    public EdgeIntermediatePoint addIntermediatePoint(double x, double y) {
        double fromX = source.coordinates.getX();
        double fromY = source.coordinates.getY();
        double nextfromX = fromX;
        double nextfromY = fromY;

        int i = 0, insertionIndex = 0;
        double minDistance = Double.MAX_VALUE;

        for (EdgeIntermediatePoint between : intermediatePoints) {
            fromX = nextfromX;
            fromY = nextfromY;
            nextfromX = between.getX();
            nextfromY = between.getY();

            double distanceTemp = Vector2D.distancePointToLine(x, y, fromX, fromY, nextfromX, nextfromY);
            if (distanceTemp < minDistance) {
                insertionIndex = i;
                minDistance = distanceTemp;
            }
            i++;
        }

        double toX = target.coordinates.getX();
        double toY = target.coordinates.getY();

        double distanceTemp = Vector2D.distancePointToLine(x, y, nextfromX, nextfromY, toX, toY);
        if (distanceTemp < minDistance) {
            insertionIndex = intermediatePoints.size();
        }

        EdgeIntermediatePoint result = new EdgeIntermediatePoint(x, y);
        intermediatePoints.add(insertionIndex, result);
        return result;
    }

    public boolean containsVertex(Vertex v) {
        return source == v || target == v;
    }

    public double length() {
        Vector2D from = this.source.coordinates;
        Vector2D to = this.target.coordinates;

        double result = 0.0;
        for (EdgeIntermediatePoint between : this.intermediatePoints) {
            result += between.coordinates.minus(from).length();
            from = between.coordinates;
        }
        return result + to.minus(from).length();
    }

    public Element toXml(Document doc, HashMap<Vertex, String> ids) throws Exception {
        Element enode = super.toXml(doc);
        enode.setAttribute("source", ids.get(source));
        enode.setAttribute("target", ids.get(target));
        enode.setAttribute("isdirected", isDirected ? "true" : "false");
        enode.setAttribute("label", label);
        enode.setAttribute("cost", Double.toString(cost));
        enode.setAttribute("width", Double.toString(width));
        enode.setAttribute("arrowheadlength", Double.toString(arrowHeadLength));
        enode.setAttribute("arrowheadangle", Double.toString(arrowHeadAngle));
        enode.setAttribute("color", color.toHtmlString());

        for (EdgeIntermediatePoint p : intermediatePoints) {
            Element e = p.toXml(doc);
            if (e != null)
                enode.appendChild(e);
        }

        return enode;
    }

    public void fromXml(Element enode, HashMap<String, Vertex> ids) throws Exception {
        setSource(ids.get(enode.getAttribute("source")));
        setTarget(ids.get(enode.getAttribute("target")));

        if (enode.hasAttribute("isdirected"))
            isDirected = enode.getAttribute("isdirected").equals("true");
        label = enode.getAttribute("label");
        if (enode.hasAttribute("cost"))
            cost = Double.parseDouble(enode.getAttribute("cost"));

        if (enode.hasAttribute("width"))
            width = Double.parseDouble(enode.getAttribute("width"));

        if (enode.hasAttribute("arrowheadlength"))
            arrowHeadLength = Double.parseDouble(enode.getAttribute("arrowheadlength"));
        if (enode.hasAttribute("arrowheadangle"))
            arrowHeadAngle = Double.parseDouble(enode.getAttribute("arrowheadangle"));
        if (enode.hasAttribute("color"))
            color = GralogColor.parseColor(enode.getAttribute("color"));

        NodeList children = enode.getChildNodes(); // load intermediate points
        for (int i = 0; i < children.getLength(); ++i) {
            Node childNode = children.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE)
                continue;

            Element child = (Element) childNode;
            Object obj = PluginManager.instantiateClass(child.getTagName());
            if (obj instanceof EdgeIntermediatePoint) {
                EdgeIntermediatePoint p = (EdgeIntermediatePoint) obj;
                p.fromXml(child);
                this.intermediatePoints.add(p);
            }
        }
    }

    protected void notifyEdgeListeners() {
        for (EdgeListener listener : listeners)
            listener.edgeChanged(new EdgeEvent(this));
    }

    public void addEdgeListener(EdgeListener listener) {
        listeners.add(listener);
    }

    public void removeEdgeListener(EdgeListener listener) {
        listeners.remove(listener);
    }
}
