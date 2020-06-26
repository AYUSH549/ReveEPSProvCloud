/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reve.antivirus.loadbalancer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.reve.antivirus.entities.ServerLoadDTO;

public class ServerLoadMinHeap {

	private static final Logger logger = LogManager.getLogger(ServerLoadMinHeap.class.getName());

	private ServerLoadDTO[] Heap = null;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	public ServerLoadMinHeap() {
	}

	public ServerLoadMinHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		Heap = new ServerLoadDTO[this.maxsize + 1];
		Heap[0] = new ServerLoadDTO();
		Heap[0].setLoad(Integer.MIN_VALUE);
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		ServerLoadDTO tmp;
		tmp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = tmp;
	}

	private void minHeapify(int pos) {

		if (!isLeaf(pos)) {
			if (Heap[pos].getLoad() > Heap[leftChild(pos)].getLoad()
					|| Heap[pos].getLoad() > Heap[rightChild(pos)].getLoad()) {

				if (Heap[leftChild(pos)].getLoad() < Heap[rightChild(pos)].getLoad()) {
					swap(pos, leftChild(pos));
					minHeapify(leftChild(pos));
				} else {
					swap(pos, rightChild(pos));
					minHeapify(rightChild(pos));
				}
			}
		}
	}

	public void insert(ServerLoadDTO element) {
		if (size >= maxsize) {
			return;
		}
		Heap[++size] = element;
		int current = size;

		while (Heap[current].getLoad() < Heap[parent(current)].getLoad()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			logger.info(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2 * i] + " RIGHT CHILD :" + Heap[2 * i + 1]);
		}
	}

	public void minHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			minHeapify(pos);
		}
	}

	public ServerLoadDTO remove() {
		ServerLoadDTO popped = Heap[FRONT];
		Heap[FRONT] = Heap[size--];
		minHeapify(FRONT);
		return popped;
	}

}
