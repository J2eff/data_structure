import java.util.Arrays;
import java.util.Random;

public class Box {

    /*
     * Puzzle Boxes
     */
    private int[] boxes;

    /*
     * Flag indicating whether a box is already visited or not
     * false: not visited, true: visited
     */
    private boolean[] visited;

    /**
     * Constructor for the puzzle
     *
     * @param size the size of the puzzle in terms of the number of boxes
     */
    public Box(int size) {
        getBuckets(size);
        visited = new boolean[size];
        Arrays.fill(visited, false);
    }

    /**
     * Constructor for the puzzle for testing purpose
     *
     * @param buckets array to be used as puzzle boxes
     */
    public Box(int[] boxes) {
        checkBox(boxes);
        this.boxes = boxes;
        boxes[0] = 0;
        visited = new boolean[boxes.length];
        Arrays.fill(visited, false);
    }

    private void checkBox(int[] boxes) {
        if (!Arrays.stream(boxes).allMatch(i -> i >= 0 && i < boxes.length))
            throw new IllegalStateException("box number invalid");
    }

    private void getBuckets(int size) {
        boxes = new int[size];
        Random gen = new Random(System.currentTimeMillis());

        for (int i = 0; i < size; i++) {
            int value = 0;
            while ((value = Math.abs(gen.nextInt())) % size == 0)
                ;
            boxes[i] = value % size;
        }
        boxes[0] = 0; // Final destination is always set to 0.
    }

    /**
     * Core of the algorithm.
     *
     * @param current current position
     * @param delta distance to move
     * @return whether it reaches destination or not
     */
    public boolean move(int current, int delta) {
        
        if(current == 0){
            this.visited[current] = true;
            return true; //  목적지에 도착했으면 true를 리턴
        }

        if( current < 0 || current >= this.boxes.length || visited[current]==true){
            return false; // list 가능한 index범위를 벗어나거나 현재의 위치가 방문한 곳이면 false를 리턴
        }
       
        this.visited[current] = true;  //현재 방문한 곳을 ture로 만들어줌
        
        if (current-delta >=0 ){  //왼쪽으로 갈 수 있으면 왼쪽으로 이동(우선)
            return this.move(current-delta, boxes[current-delta]);
        }
        if( current+delta < this.boxes.length){ //오른쪽으로 갈 수 있으면 오른쪽으로 이동
            return this.move(current+delta, boxes[current+delta]);
        }


        return false;
    }


    public boolean start() {
        return move(boxes.length - 1, boxes[boxes.length - 1]);
    }

    public boolean[] getVisitingStatus() {
        return visited;
    }

    public int[] getBoxes() {
        return boxes;
    }

    public static void main(String... args) {
        /* actual run */
        Box puzzle = new Box(10);
        System.out.println(Arrays.toString(puzzle.getBoxes()));
        System.out.println(puzzle.start());
        System.out.println(Arrays.toString(puzzle.getVisitingStatus()));

        /* for testing */
        int[] xs = {0, 3, 2, 1, 2};
        
        Box puzzle1 = new Box(xs);
        System.out.println(puzzle1.start());
        System.out.println(Arrays.toString(puzzle1.getVisitingStatus()));

       
    }
}

