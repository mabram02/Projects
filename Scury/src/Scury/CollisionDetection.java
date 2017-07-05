package Scury;

public interface CollisionDetection {
	boolean WallCollisionLeft(Object obj, Object jbo);
	boolean WallCollisionRight(Object obj, Object jbo);
	boolean WallCollisionTop(Object obj, Object jbo);
	boolean WallCollisionBottom(Object obj, Object jbo);
}
