#ifndef GAME_H
#define GAME_H
#include<vector>

class game
{
	public:
	 	game();
		bool puzzleWall(int row, int col);
		typedef std::vector<std::pair<int,int>> pair_intv;
		typedef std::pair<char,char> pair_char;
		pair_intv getEmptyspaces();
		int EmptySpacesSize();
		int pathSize();
		void clearPath();
		void removeUsedSpace(int pos);
		void fillFirstPos(int pos);
		bool down(int pos, pair_char piece);
		bool up(int pos, pair_char piece);
		bool left(int pos, pair_char piece);
		bool right(int pos, pair_char piece);
		bool l_downDiagonal(int pos, pair_char piece);
		bool l_upDiagonal(int pos, pair_char piece);
		bool r_downDiagonal(int pos, pair_char piece);
		bool r_upDiagonal(int pos, pair_char piece);
		void ownPath(char piece);
		int corrPosition(std::pair<int,int> temp);
		void alg1MovesAvail(bool move);
		void alg2MovesAvail(bool move);
		bool alg1MovesAvail();
		bool alg2MovesAvail();
		void start();
	
	private:
		int init_matrix();
		void centreInit(int &center_spaces);
		void empty_spots(int center_spaces);
		void newEmptySpaces(int pos, pair_intv &tem);
		void removeExistingPos(pair_intv &temp);
		void addSpaces(pair_intv temp);
		void boolVariableInitialised();
		void results();
		void play();
		void printCurrentBoard();
		bool algorthm_1;
		bool algorthm_2;
};

#include "game.cpp"
#endif
