class Graph:
    def __init__(self, size):
        self.adj_matrix = [[0] * size for _ in range(size)]
        self.size = size
        self.vertex_data = [''] * size

    def add_edge(self, u, v, c):
        self.adj_matrix[u][v] = c

    def add_vertex_data(self, vertex, data):
        if 0 <= vertex < self.size:
            self.vertex_data[vertex] = data

    def dfs(self, s, t, visited):
        stack = [(s, [s])]
        while stack:
            node, path = stack.pop()
            if node == t:
                return path
            for next_node, capacity in enumerate(self.adj_matrix[node]):
                if capacity > 0 and not visited[next_node]:
                    visited[next_node] = True
                    stack.append((next_node, path + [next_node]))
        return None

    def fordFulkerson(self, source, sink):
        max_flow = 0

        while True:
            visited = [False] * self.size
            visited[source] = True
            path = self.dfs(source, sink, visited)
            if not path:
                break

            path_flow = float("Inf")
            for i in range(len(path) - 1):
                u, v = path[i], path[i + 1]
                path_flow = min(path_flow, self.adj_matrix[u][v])

            for i in range(len(path) - 1):
                u, v = path[i], path[i + 1]
                self.adj_matrix[u][v] -= path_flow
                self.adj_matrix[v][u] += path_flow

            max_flow += path_flow

            path_names = [self.vertex_data[node] for node in path]
            print("Path:", " -> ".join(path_names), ", Flow:", path_flow)

        return max_flow

# Example usage
g = Graph(8)
vertex_names = ['s', 'v1', 'v2', 'v3', 'v4', 'v5', 'v6', 't']
for i, name in enumerate(vertex_names):
    g.add_vertex_data(i, name)

# Add edges with capacities
g.add_edge(0, 1, 5)  # s -> v1
g.add_edge(0, 2, 3)  # s -> v2
g.add_edge(0, 3, 3)  # s -> v3
g.add_edge(1, 2, 5)  # v1 -> v2
g.add_edge(1, 4, 3)  # v1 -> v4
g.add_edge(2, 4, 1)  # v2 -> v4
g.add_edge(2, 5, 1)  # v2 -> v5
g.add_edge(2, 6, 3)  # v2 -> v6
g.add_edge(3, 6, 3)  # v3 -> v6
g.add_edge(4, 7, 4)  # v4 -> t
g.add_edge(5, 7, 6)  # v5 -> t
g.add_edge(6, 7, 3)  # v6 -> t

source = 0  # 's'
sink = 7    # 't'

print("The maximum possible flow is %d" % g.fordFulkerson(source, sink))
