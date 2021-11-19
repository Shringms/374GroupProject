/* Author: NIco Kasz
*  Description: 374 group project coded algorithm
*/
import java.util.*;

public class AlgoProject{
    Node benton_hall = new Node("Benton Hall"),
            armstrong = new Node("Armstrong"),
            bell_tower = new Node("Bell Tower"),
            laws_hall = new Node("Laws Hall"),
            pearson_hall = new Node("Pearson Hall"),
            harrison_hall = new Node("Harrison Hall");
    Node[] nodes = new Node[]{benton_hall,armstrong,bell_tower,laws_hall,pearson_hall,harrison_hall};
    Queue<Node> courses = new PriorityQueue<>(Arrays.asList(nodes));

    Edge e1 = new Edge(benton_hall,bell_tower,10),
         e2 = new Edge(bell_tower,armstrong,15),
         e3 = new Edge(benton_hall,laws_hall,5),
         e4 = new Edge(laws_hall,armstrong,15);
    Edge[] edges = new Edge[]{e1,e2,e3,e4};

    Class intro_software = new Class("Intro to Software Engineering", benton_hall),
            evo_bio = new Class("Evolutionary Biology", pearson_hall),
            algo = new Class("Algorithms", benton_hall),
            intro_ethics = new Class("Introduction to Ethics", harrison_hall);


    Dictionary<Integer, String> timeSlots = new Hashtable<>() {{ put(1, "8:30"); put(2, "10:05"); put(3,"11:40"); put(4,"1:15"); put(5,"2:50"); put(6,"4:25"); put(7,"6:00"); }};

    List<Class> classes = new LinkedList<>(Arrays.asList(new Class[] {intro_software, evo_bio, algo, intro_ethics}));
    Node currentNode = laws_hall;
    boolean lunchBreak = false;

    public static void main(String args[]) {
        AlgoProject s = new AlgoProject();
        s.Schedule();
        while(!s.courses.isEmpty()) {
            System.out.println(s.courses.poll().name);
        }
    }

    List<Node> Schedule() {
        List<Node> route = new LinkedList<>();
        // Loops while there are still classes to go to.
        while (classes.size() > 0) {
            // Breadth first search and relax

            // Find shortest path and check if can eat lunch on it

            //
        }
        return route;
    }

    void resetNodes() {
        for (Node n : nodes) {
            n.weight = Integer.MAX_VALUE;
        }
        courses = new PriorityQueue<>(Arrays.asList(nodes));
        currentNode.setWeight(0);
    }

    class Node implements Comparable {
        String name, description;
        int weight;
        boolean lunch;

        public Node(String name, String description, int weight, boolean lunch) {
            this.name = name;
            this.description = description;
            this.weight = weight;
            this.lunch = lunch;
        }

        public Node(String name, int weight) {
            this(name,null,Integer.MAX_VALUE,false);
        }

        public Node(String name) {
            this(name,null,Integer.MAX_VALUE,false);
        }

        public void setWeight(int w) {
            weight = w;
            courses.remove(this);
            courses.add(this);
        }

        @Override
        public int compareTo(Object o) {
            Node v = ((Node)o);
            return this.weight == v.weight || this.name.equals(v.name) ? 0 : this.weight < v.weight ? -1 : 1;
        }
    }

    class Edge implements Comparable{
        Node u,v;
        int weight;

        public Edge(Node u, Node v, int weight) {
            this.u=u;
            this.v=v;
            this.weight=weight;
        }

        @Override
        public int compareTo(Object o) {
            Edge e = ((Edge)o);
            return (e.u == u && v == e.v) || (u == e.v && v == e.u) ? 0 : weight > e.weight ? 1 : -1;
        }
    }

    class Class {
        String name;
        int courseNum;
        Node hall;

        public Class(String name, int courseNum, Node hall) {
            this.name = name;
            this.courseNum = courseNum;
            this.hall = hall;
        }

        public Class(String name, Node hall) {
            this(name,0,hall);
        }
    }
}