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

	/* ���������˶� */
	public void lifeIsMove() {
		this.X += this.vx;
		this.Y += this.vy;
	}

	/*
	 * �������߳�10msˢ��һ�Σ�����10ms�ڣ�����˶� �������ҵ���ײ�ж��㷨���ԣ��������ᶯ Ȼ��ͻ������������Ч�� ���磬��Խ������
	 * ���磬������̫����ת������ Ҫ����߾��ȣ�����Ŀǰ�㷨���ԣ� �ͱ����ٴμ����߳�ˢ��ʱ�� ���ˣ��������ɣ�ż��Բ���˶�Ҳͦ��
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

	/* ˲���ƶ� */
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
		/* ������֪������һ���������� */
		/* ����ͬʱ��ײ�ķ������Ҳ�д�� */
		/* д����Ҳ���ƣ��ö�������+���ܶ��� */
		/* �����͸���һЩ */
		/*
		 * �Ұ�������ײ�����������һ������һ���� �ٹ�һ������˲������һ�� ������Ȼ���Ǻܺã���Ҳ�պ� ���������˼������Ȥ�Ŀ�������
		 */
	}
}