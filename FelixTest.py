#!/usr/bin/python

import sys
from Lib import *
from random import randint





g = Graph(None);#type \in buechi, directed, etc. or None

# h = Graph("undirected");

# g1 = Graph("directed");



numV = 100;

vertices = [];

gregarity = [0];


g.track("my id",str(g.id));

# g.track("g vertices",vertices);



for x in range(10000):
	v1 = g.addVertex();
	v2 = g.addVertex();
	g.addEdge(v1,v2);
	g.pauseUntilSpacePressed();
	


# for x in vertices:
# 	for y in vertices:
# 		g.addEdge(x,y);
		
# 		# g.pauseUntilSpacePressed(2);
# 	# g.pauseUntilSpacePressed(1);

# g.pauseUntilSpacePressed();

# for x in vertices:
# 	for y in vertices:
# 		g.setEdgeContour((x,y),"dashed");
		
# 	# g.pauseUntilSpacePressed(5);


# g.pauseUntilSpacePressed();

# for x in vertices:
# 	g.deleteVertex(x);







