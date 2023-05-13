public class Edge implements Comparable<Edge>{
    int source;
    int destination;
    int weight;
    public Edge(int source,int destination,int weight){
        this.destination=destination;
        this.weight=weight;
        this.source=source;
    }
    @Override
    public int compareTo(Edge e1) {
        if(this.weight<e1.weight){
            return -1;
        }
        else if(this.weight>e1.weight){
            return 1;
        } 
        return 0;
    }}