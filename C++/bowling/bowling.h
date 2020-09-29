#ifndef BOWLING_H
#define BOWLING_H

class bowling
{
	public:
		bowling();
		void list();
		
	private:
		void initialising_arrays();
		void fillThFrame();
		void print_Results();
		void runn_testcases();
		void printFrameNumber();
		void printFrameScore();
		void printRunningTotals();
		void printSpareStrikeFrame();
		int* frame;
};

#include "bowling.cpp"
#endif
