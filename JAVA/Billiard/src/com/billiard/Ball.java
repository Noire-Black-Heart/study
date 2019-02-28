package com.billiard;

import java.awt.Color;

public class Ball {
	double X = 0, Y = 0;
	double vx = 0, vy = 0;
	Color clr = null;
	boolean[] crlst = new boolean[10];
	boolean vsble = true;

	public Ball() {
	}

	public Ball(int x, int y, Color clr) {
		this.X = x;
		this.Y = y;
		this.clr = clr;
	}

	public void overturnx() {
		this.vx = -vx;
	}

	public void overturny() {
		this.vy = -vy;
	}

	/* 生命在于运动 */
	public void lifeIsMove() {
		this.X += this.vx;
		this.Y += this.vy;
	}

	/*
	 * 受限于线程10ms刷新一次，在这10ms内，球会运动 并不是我的碰撞判定算法不对，而是它会动 然后就会产生各种奇葩效果 比如，穿越。。。
	 * 比如，地球绕太阳旋转。。。 要想提高精度，就我目前算法而言， 就必须再次减少线程刷新时间 算了，就这样吧，偶尔圆周运动也挺好
	 */
	public boolean isCrash(Ball b) {
		double x = this.X - b.X;
		double y = this.Y - b.Y;
		double xx = x * x;
		double yy = y * y;
		
		double vx = this.vx - b.vx;
		double vy = this.vy - b.vy;
		
	
		boolean flag;
		flag = vx*x+vy*y<0;

		if (xx + yy <= 24 * 24)
			if (flag) {
				return true;
			}
		return false;
	}

	/* 瞬间移动 */
	public void mmtmove(Ball b) {
		double vx = this.vx - b.vx;
		double vy = this.vy - b.vy;
		double s = Math.hypot(this.X - b.X, this.Y - b.Y) - 24;
		double t = s / Math.hypot(vx, vy);
		this.X += this.vx * t;
		this.Y += this.vy * t;
		b.X += b.vx * t;
		b.Y += b.vy * t;
	}

	public void vslow() {
		double x, y;
		x = this.vx;
		y = this.vy;
		if (x > 0) {
			if (this.vx >= 0.01) {
				this.vx -= x / Math.sqrt(Math.hypot(x, y)) / 80;
				// mBall.vx *= 0.9;
			} else
				this.vx = 0;
		}
		if (x < 0) {
			if (this.vx <= -0.01) {
				this.vx -= x / Math.sqrt(Math.hypot(x, y)) / 80;
				// mBall.vx *= 0.9;
			} else
				this.vx = 0;
		}

		if (y > 0) {
			if (this.vy >= 0.01) {
				this.vy -= y / Math.sqrt(Math.hypot(x, y)) / 80;
				// mBall.vy *= 0.9;
			} else
				this.vy = 0;
		} else {
			if (this.vy <= -0.01) {
				this.vy -= y / Math.sqrt(Math.hypot(x, y)) / 80;
				// mBall.vy *= 0.9;
			} else
				this.vy = 0;
		}
	}

	public void crashWith(Ball b) {
		double k = (this.X - b.X) / (this.Y - b.Y);

		b.vy = (k * this.vx + this.vy) / (k * k + 1);
		b.vx = k * b.vy;

		this.vx -= b.vx;
		this.vy -= b.vy;
	}

	public void crashWith(Ball b2, Ball b3) {
		/* 众所周知，我是一个很懒的人 */
		/* 三球同时碰撞的方法，我不写了 */
		/* 写起来也类似，用动量定理+动能定理 */
		/* 不过就复杂一些 */
		/*
		 * 我把三球碰撞的情况看成了一个球碰一个球 再过一个极短瞬间碰另一个 这样虽然不是很好，但也凑合 就是这个意思，有兴趣的可以试试
		 */
	}
}