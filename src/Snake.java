import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * The Snake class maintains the information on one snake in the game APSnake
 * Snakes consist of one head at the front, followed by a trail of bits. Snakes
 * may not crash into obstacles or other snakes (including themselves).
 * 
 * @author kabaram
 *
 */

public class Snake {

	// contains all bits that constitute the snake
	private List<SnakeBit> bits = new ArrayList<SnakeBit>();

	// the x and y coordinates of the head
	private int x, y;

	// directional values
	public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// current direction of the snake
	private int direction;

	// distance traveled per frame
	private final int MOTION = Main.BIT;

	// Milliseconds between fraames
	private static int delay;

	/**
	 * Constructs a 3-bit snake with a fixed initial delay and random direction and
	 * starting location
	 * 
	 * @param obstacles maintains location data of all obstacles on the field
	 */
	public Snake(List<Obstacle> obstacles) {
		delay = 150;
		direction = (int) (Math.random() * 4);
		build(obstacles);
	}

	/**
	 * Generates the random snake location to ensure that it is not inside any
	 * obstacle
	 * 
	 * @param obstacles maintains location data of all obstacles on the field
	 */
	public void build(List<Obstacle> obstacles) {
		x = 0;
		y = 0;

		// generate location until not overlapping an obstacle
		do {
			x = (int) (Math.random() * Main.DIM - 50);
			y = (int) (Math.random() * Main.DIM - 50);
		} while (headInObstacle(x, y, obstacles));

		bits.add(new SnakeHead(x, y, direction));

		// creates two bits trailing the head based on direction
		switch (direction) {
		case UP:
			bits.add(new SnakeBit(x, y + Main.BIT));
			bits.add(new SnakeBit(x, y + 2 * Main.BIT));
			break;
		case RIGHT:
			bits.add(new SnakeBit(x - Main.BIT, y));
			bits.add(new SnakeBit(x - 2 * Main.BIT, y));
			break;
		case DOWN:
			bits.add(new SnakeBit(x, y - Main.BIT));
			bits.add(new SnakeBit(x, y - 2 * Main.BIT));
			break;
		default:
			bits.add(new SnakeBit(x + Main.BIT, y));
			bits.add(new SnakeBit(x + 2 * Main.BIT, y));
			break;
		}
	}

	/**
	 * Checks head location during snake initialization for overlap with an obstacle
	 * 
	 * @param x         the proposed x coordinate of the head
	 * @param y         the proposed y coordinate of the head.
	 * @param obstacles
	 * @return true if head is in obstacle, false otherwise
	 */
	private boolean headInObstacle(int x, int y, List<Obstacle> obstacles) {
		/**
		 * Write this method
		 * 
		 * The incoming x and y parameters are the proposed head location - not the
		 * official location. If the proposed head overlaps with any obstacle, have this
		 * method return true.
		 */
		return false;
	}

	/**
	 * Checks to see if the snakes head has collided with a snake (self of opponent)
	 * 
	 * @param s The full snake to check for a collision with this snake's head
	 * @return true if collision, false otherwise.
	 */
	private boolean headInSnake(Snake s) {

		// start with third bit if checking against itself, check all bits if checking
		// against opponent
		int start = s == this ? 2 : 0;

		int thisX = bits.get(0).getX() + Main.BIT / 2;
		int thisY = bits.get(0).getY() + Main.BIT / 2;

		for (int i = start; i < s.bits.size(); i++) {
			/*
			 * Complete this loop so that if this snake's head collides with any of the bits
			 * in s, the method returns true.
			 */
		}

		return false;
	}

	/**
	 * Checks for a snake collision between either itself or the opponent
	 * 
	 * @param other the opponent snake
	 * @return true if collision, false otherwise.
	 */
	public boolean checkHeadInSnake(Snake other) {
		return headInSnake(this) || headInSnake(other);
	}

	/**
	 * Changes the direction of travel for the snake
	 * 
	 * @param direction the new direction of travel
	 */
	public void turn(int direction) {
		bits.get(0).setDirection(direction);
	}

	/**
	 * Check if a snake has reached a pellet. If so, snake size increases by 3 bits,
	 * pellet moves, and gameplay speeds up
	 * 
	 * @param pellet    the pellet to check
	 * @param obstacles all obstacles on field to ensure pellet does not overlap an
	 *                  obstacle after moving
	 */
	public void checkHitPellet(Pellet pellet, List<Obstacle> obstacles) {
		SnakeBit head = bits.get(0);
		int snakeX = head.getX() + Main.BIT / 2;
		int snakeY = head.getY() + Main.BIT / 2;
		int pelletX = pellet.getX() + Main.BIT / 2;
		int pelletY = pellet.getY() + Main.BIT / 2;

		// check distance between head's center and pellet's center for collision
		if (Math.sqrt(Math.pow((pelletX - snakeX), 2) + Math.pow((pelletY - snakeY), 2)) <= Main.BIT) {
			/*
			 * Complete this conditional body
			 * 
			 * Move the pellet Grow the snake by adding 3 new bits, all in the tail's
			 * location.
			 *  Speed up the gameplay (method below)
			 */
		}

	}

	/**
	 * Checks if the snake head has traveled into an obstacle
	 * 
	 * @param obstacles all obstacles on the field
	 * @return true if collision, false otherwise
	 */
	public boolean checkHitObstacle(List<Obstacle> obstacles) {
		/**
		 * Write this method.
		 * 
		 * If the snake's head collides with any obstacles, return true.
		 */
		return false;
	}

	/**
	 * Increases the speed of the snake after eating a pellet. Max speed of 50fps
	 */
	private void speedUp() {
		if (delay >= 20) {
			delay -= 5;
		}
	}

	/**
	 * 
	 * @return the current direction of travel
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * 
	 * @return this snake's delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * Moves the snake one frame Accomplished by adding a new snake head in the
	 * correct direction at the front of the snake, then reverting the previous head
	 * to a regular bit, removing the last bit, and updating x and y to the
	 * coordinates of the new head.
	 */
	public void move() {
		/*
		Write this method

		Depending on the snake's direction, add a new head at index 0, adjacent to the
		current head. If the snake's head surpasses the edge of the screen, it wraps around on the opposite
		side. The coordinates for edges are:
			Left: x = 0
			Right: x = Main.DIM - 15
			Top: y = 0
			Bottom: y = Main.DIM - 38

		Set the bit at index 1 (the former head) as a regular snake bit to remove the eyes

		Remove the last bit (to counteract the bit added at the front)

		Set the x and y instance variables to the location of the new index 0
		 */

	}

	/**
	 * Draws all bits of the snake
	 * 
	 * @param g Graphics object for drawing
	 */
	public void draw(Graphics g) {
		for (SnakeBit bit : bits) {
			bit.draw(g);
		}
	}

}
