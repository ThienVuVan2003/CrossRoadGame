package state;

import static utilz.constants.AnimalTypes.*;
import static utilz.constants.CarDirections.*;
import static utilz.constants.CarTypes.*;
import static utilz.constants.MapTypes.*;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Game;
import entities.*;
import handle.State;
import static handle.Collision.isCollision;
import utilz.stateConstants.*;

public class Playing extends State implements Statemethods  {
	
	private Map map;
	private Animal animal;
	private Score score;
	private Level level;
	ArrayList<Car> cars;
	
	
	public Playing(Game game) {
		super(game);
	}

	public void createMap() {
		switch(MapState.state) {
		case MAP1:
			map = new Map(0, 0, STREET, 0, 0);
			cars = new ArrayList<>();
			cars.add(new Car(260, 60, CAR1, 65, 40, LEFT));
			cars.add(new Car(500, 245, CAR3, 65, 40, LEFT));
			cars.add(new Car(900, 430, CAR8, 65, 40, LEFT));
			cars.add(new Car(260, 430, CAR7, 65, 40, LEFT));
			cars.add(new Car(700, 120, CAR2, 65, 40, RIGHT));
			cars.add(new Car(1000, 305, CAR5, 65, 40, RIGHT));
			cars.add(new Car(100, 495, CAR6, 65, 40, RIGHT));
			cars.add(new Car(500, 305, CAR4, 65, 40, RIGHT));
			break;
		case MAP2:
			map = new Map(0, 0, MOUNTAIN, 0, 0);
			cars = new ArrayList<>();
			cars.add(new Car(260, 60, CAR1, 65, 40, LEFT));
			cars.add(new Car(500, 245, CAR3, 65, 40, LEFT));
			cars.add(new Car(900, 430, CAR8, 65, 40, LEFT));
			cars.add(new Car(260, 430, CAR7, 65, 40, LEFT));
			cars.add(new Car(700, 130, CAR2, 65, 40, RIGHT));
			cars.add(new Car(1000, 320, CAR5, 65, 40, RIGHT));
			cars.add(new Car(100, 495, CAR6, 65, 40, RIGHT));
			cars.add(new Car(500, 320, CAR4, 65, 40, RIGHT));
			break;
		default:
			break;
		}
	}
	public void createAnimal() {
		switch(AnimalState.state) {
		case MOUSE:
			animal = new Animal(450,560,MOUSE,35,35);
			score = new Score(850, 30, null, 0, 0, animal);
			level = new Level(20, 30, null, 0, 0, this);
			break;
		case CAT:
			animal = new Animal(450,560,CAT,35,35);
			score = new Score(850, 30, null, 0, 0, animal);
			level = new Level(20, 30, null, 0, 0, this);
			break;
		case DOG:
			animal = new Animal(450,560,DOG,35,35);
			score = new Score(850, 30, null, 0, 0, animal);
			level = new Level(20, 30, null, 0, 0, this);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void update() {
		animal.update();
		for(Car e : cars) {
			e.update();
		}
		for(Car e : cars) {
			if(isCollision(animal.getHitBox(),e.getHitBox())) {
				GameState.state = GameState.OVER;
			}
		}
		score.update();
		level.update();
		
	}

	@Override
	public void draw(Graphics g) {
		map.render(g);
		animal.render(g);
		for(Car e : cars) {
			e.render(g);
		}
		score.render(g);
		level.render(g);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			animal.setUp(true);
			break;
		case KeyEvent.VK_A:
			animal.setLeft(true);
			break;
		case KeyEvent.VK_D:
			animal.setRight(true);
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			animal.setUp(false);
			break;
		case KeyEvent.VK_A:
			animal.setLeft(false);
			break;
		case KeyEvent.VK_D:
			animal.setRight(false);
			break;
		}
	}
	public Animal getAnimal() {
		return animal;
	}
	public Score getScore() {
		return score;
	}
	public Level getLevel() {
		return level;
	}
	public void windowFocusLost() {
		animal.resetDirBooleans();
	}
}
