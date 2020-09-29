#ifndef GAME_H
#define GAME_H
#include<vector>

class game
{
	public:
		game();
		void play();
		
	private:
		typedef std::pair<int,int> cell;
		void printResults();
		void lookPosition();
		bool validPostion(int row, int col);
		void nextPlayer();
		bool vertical( int fixed);
		bool horizontal(int fixed);
		bool leftDiagonal();
		bool rightDiagonal();
		void rowColRandom(int &row, int &col);
		void setColRow(int &row, int &col);
		void placeinPosition(int row, int col);
		bool checkWinLoose(cell curr_cell);
		bool check(cell curr_cell);
		bool unvisitedCell(int row, int col);
		int CountSameMarkH(int row);
		int CountSameMarkV(int col);
		int CountSameMarkD(int row, int col);
		void nextCell(int &row, int &col);
		bool twoSameMark(int row, int col);
		void gameOver();
		void initializePuzzle();
		void initializePosition();
		bool diagonal(int row ,int col);
		bool computer;
		bool human;
		bool game_over;
		std::vector<cell> unvisited_pos;
		cell human_lp;
		cell computer_lp;
		char** puzzle;
		char const x = 'x';
		char const o = 'o';
		int const size = 3;
		int init_count;
		char placement;
		bool left_diagonal;
		bool right_diagonal;
};
#include "game.cpp"
#endif
