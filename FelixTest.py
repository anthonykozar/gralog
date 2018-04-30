#!/usr/bin/python

import sys
from random import randint
#data = sys.stdin.readlines()
# line = sys.stdin.readline()
# while(line != "x\n"):
#     print line+"\n"
#     line = sys.stdin.readline()
# print "Hello " + sys.argv[1] + ", I'm printing."







class Vertex:
	def __init__(self,id,x = None,y=None,color = None):
		self.x = x;
		self.y = y;
		self.color = color;
		self.id=id;
	def __str__(self):
		return str(self.id);

class Graph:
	def __init__(self,format="Undirected Graph"):
		#perform analysis of graph
		self.vertices = [];
		self.edges = [];
		self.lastIndex = -1;
		self.id = -1;
		if format == None or format.lower() == "none":
			#we want a new graph

			print("useCurrentGraph");
			sys.stdout.flush();
			self.vertices = [];
			self.edges = [];
			self.lastIndex = -1;
			self.id = sys.stdin.readline();
			
	
		else:
			print(format);
			sys.stdout.flush();
			self.id = sys.stdin.readline();
			
		

	def addVertex(self,x=None,y=None,color=None):
		# newVertex = Vertex(self.lastIndex + 1,x,y,color);
		# self.vertices.append(newVertex);
		self.lastIndex += 1;
		print ("addVertex " + str(self.id).rstrip() + " " +  str(self.lastIndex));
		sys.stdout.flush();
		idFromGralog = sys.stdin.readline();
		newVertex = Vertex(idFromGralog,x,y,color);
		self.vertices.append(newVertex);
		return int(idFromGralog.rstrip());

	def deleteVertex(self,vertexId):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# if vertexId in indices:
		# self.vertices.remove(self.vertices[indices.index(vertexId)]);
		print "deleteVertex " +str(self.id).rstrip() + " " +  str(vertexId);
		sys.stdout.flush();
		sys.stdin.readline();

	def setVertexFillColor(self,vertexId,colorHex=-1,colorRGB=-1):
		indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
	
		if not (colorHex==-1):
			line = "setVertexFillColor " + str(self.id).rstrip() + " " + str(vertexId).rstrip() + " " + str(colorHex).rstrip();
			print line.rstrip();
		elif not (colorRGB == -1) and len(colorRGB) == 3:
			line = "setVertexFillColor "+str(self.id).rstrip() + " " + str(vertexId).rstrip() + " " + str(colorRGB[0]).rstrip() + " " + str(colorRGB[1]).rstrip() + " " + str(colorRGB[2]).rstrip();
			print(line.rstrip());
		else:
			return
		sys.stdout.flush();
		sys.stdin.readline();
	

	def setVertexStrokeColor(self,vertexId,colorHex=-1,colorRGB=-1):
		indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));

		if not (colorHex==-1):
			line = "setVertexStrokeColor "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip() + " " + str(colorHex).rstrip();
			print line.rstrip();
		elif not (colorRGB == -1) and len(colorRGB) == 3:
			line = "setVertexStrokeColor "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip() + " " + str(colorRGB[0]).rstrip() + " " + str(colorRGB[1]).rstrip() + " " + str(colorRGB[2]).rstrip();
			print line.rstrip();
		sys.stdout.flush();
		sys.stdin.readline();

	def setEdgeColor(self,sourceVertexId,targetVertexId,colorHex=-1,colorRGB=-1):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		line = line = "setEdgeColor "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip() + " ";
		if not (colorHex==-1):
			line = line + str(colorHex).rstrip();
			print line.rstrip();
		elif not (colorRGB == -1) and len(colorRGB) == 3:
			line = line + str(colorRGB[0]).rstrip() + " " + str(colorRGB[1]).rstrip() + " " + str(colorRGB[2]).rstrip();
			print(line.rstrip());
		sys.stdout.flush();
		sys.stdin.readline();


	def setVertexRadius(self,vertexId,newRadius):
		indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		# if vertexId in indices:
			
		line = "setVertexRadius "+str(self.id).rstrip() + " " + str(vertexId).rstrip() + " " + str(newRadius).rstrip();
		print line.rstrip();
	
		sys.stdout.flush();
		sys.stdin.readline();


	def getConnectedNeighbours(self,vertexId):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		# if vertexId in indices:
			
		line = "getConnectedNeighbours "+str(self.id).rstrip() + " " + str(vertexId).rstrip();
		print line.rstrip();
	
		sys.stdout.flush();
		connectedNeighboursList = (sys.stdin.readline()).split(" ");
		return connectedNeighboursList;

	def getOutgoingNeighbours(self,vertexId):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		# if vertexId in indices:
			
		line = "getOutgoingNeighbours "+str(self.id).rstrip() + " " + str(vertexId).rstrip();
		print line.rstrip();
	
		sys.stdout.flush();
		outGoingNeighboursList = (sys.stdin.readline()).split(" ");
		return outGoingNeighboursList;

	def getConnectedEdges(self,vertexId):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		# if vertexId in indices:
			
		line = "getConnectedEdges "+str(self.id).rstrip() + " " + str(vertexId).rstrip();
		print line.rstrip();
	
		sys.stdout.flush();
		connectedNeighboursList = (sys.stdin.readline()).split(" ");
		# print("connectedNeighboursList: " , connectedNeighboursList);
		for i in range(len(connectedNeighboursList)):
			term = connectedNeighboursList[i].rstrip();
			term = term[1:-1]
			# print("current term",term);
			endpoints = term.split(",");
			connectedNeighboursList[i] = (int(endpoints[0]),int(endpoints[1]));


		return connectedNeighboursList;

	def getOutgoingEdges(self,vertexId):
		# indices = map(lambda vertex: vertex.id, self.endpoints);
		# print("colorhex: " + str(colorHex));
		# if vertexId in indices:
			
		line = "getOutgoingEdges "+str(self.id).rstrip() + " " + str(vertexId).rstrip();
		print line.rstrip();
	
		sys.stdout.flush();
		outGoingNeighboursList = (sys.stdin.readline()).split(" ");
		for i in range(len(outGoingNeighboursList)):
			term = outGoingNeighboursList[i][1:-1]
			endpoints = term.split(",");
			outGoingNeighboursList[i] = (int(endpoints[0]),int(endpoints[1]));
		return outGoingNeighboursList;


	def addEdge(self,sourceVertexId, targetVertexId, directed = False):
		# indices = map(lambda vertex: vertex.id, self.vertices);
		# print("colorhex: " + str(colorHex));
		# if vertexIndex in indices:

			
		line = "addEdge "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip() + " " + str(directed).lower();


		print line.rstrip();
	
		sys.stdout.flush();
		sys.stdin.readline();
		# return connectedNeighboursList;

	def deleteEdge(self,sourceVertexId,targetVertexId):

		line = "deleteEdge "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip();

		print line.rstrip();

		sys.stdout.flush();
		sys.stdin.readline();

	def addEdgeLabel(self,sourceVertexId, targetVertexId, label):
		line = "addEdgeLabel "+str(self.id).rstrip() + " " + str(sourceVertexId).rstrip() + " " + str(targetVertexId).rstrip() + " " + label;
		print line.rstrip();

		sys.stdout.flush();
		sys.stdin.readline();

	def addVertexLabel(self,vertexId, label):
		line = "addVertexLabel "+str(self.id).rstrip() + " " + str(vertexId).rstrip() + " " + label;
		print line.rstrip();

		sys.stdout.flush();
		sys.stdin.readline();


	def pauseUntilSpacePressed(self):
		print("pauseUntilSpacePressed");
		sys.stdout.flush();
		sys.stdin.readline();





	def __str__(self):
		return "todo: tgf"


#####functional program #######
# recd = "null";
# for i in range(3):
# 	print recd; #send hello to java
# 	sys.stdout.flush()
# 	line = sys.stdin.readline(); #read read response from java
# 	recd = line;
##############################




###algorithm: add 3 nodes to tha homie graph.
# recd = "null";
# myid = g.addVertex();
# g.pauseUntilSpacePressed();
# # g.

# g.setVertexFillColor(myid,colorHex = "423097");
# g.pauseUntilSpacePressed();
# g.setVertexRadius(myid,5);

# myidConnectedVertices = g.getConnectedNeighbours(myid);

# myidConnectedVertices = myidConnectedVertices.split(" ");

# g.pauseUntilSpacePressed();

# for neighbourId in myidConnectedVertices:
# 	g.setVertexFillColor(neighbourId,colorRGB=(255,0,0));

# g.pauseUntilSpacePressed();

# g.deleteVertex(0);


g = Graph("undirected");#type \in buechi, directed, etc. or None
# g2 = Graph("undirected");



# 
# g.deleteVertex(v1);



# source = g.addVertex();
# target = g.addVertex();

# g.addEdge(source,target);
# g.setEdgeColor(source,target,colorRGB=(255,0,245));


# set1 = []
# set2 = []

# for x in range(2):
# 	set1.append(g.addVertex());


# for x in range(2):
# 	set2.append(g.addVertex());

# for node1 in set1:
# 	for node2 in set2:
# 		g.addEdge(node1,node2,True);

# g.pauseUntilSpacePressed();


# print(type(set1[0]).__name__);

# allVertices = [];

numV = 3;

# for x in range(numV):
# 	curr = g.addVertex();
# 	g.addVertexLabel(curr,str(curr));
# 	allVertices.append(curr);

# g.pauseUntilSpacePressed();

# for i in range(numV*2):
# 	source = randint(0,numV-1);
# 	target = randint(0,numV-1);
# 	g.addEdge(source,target);

# g.pauseUntilSpacePressed();

# start = allVertices[0];

# queue = [start];

# seen = [start];

# while len(queue) > 0:
# 	curr = queue.pop(0);
# 	neighbours = g.getConnectedNeighbours(curr);
# 	for neighbour in neighbours:
# 		if not (neighbour in seen):
# 			g.setEdgeColor(curr,neighbour,colorRGB=(255,0,245));
# 			queue.append(neighbour);
# 			seen.append(neighbour);

vertices = [];
for x in range(numV):
	curr = g.addVertex();
	g.addVertexLabel(curr,str(curr));
	vertices.append(curr);

g.pauseUntilSpacePressed();

for i in range(numV*2):
	source = randint(0,numV-1);
	target = randint(0,numV-1);
	g.addEdge(source,target);

g.pauseUntilSpacePressed();


for i in vertices:
	currentEdges = g.getConnectedEdges(i);
	for x in currentEdges:
		g.deleteEdge(x[0],x[1]);
		g.pauseUntilSpacePressed();






# for i in range(3):
# 	print("helloo");