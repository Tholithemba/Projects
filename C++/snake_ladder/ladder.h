#ifndef LADDER_H
#define LADDER_H

class ladder
{
 public:
 	ladder();
 	void play();
	int random_numbers();
 
 private:
	void climb_ladder(int rand_number);
	void bite(int rand_number);
	bool check_number(int rand_number);
	bool climbUp(int rand_number);
	bool dropDown(int rand_number);
	void player1Rolldie(int roll_number);
	void player2Rolldie(int roll_number);
	void roll_dice();
	void next_player();
	void updatePlayer_pos(bool check_exist);
	void active_player();
	int temp_update();
	bool reachMaximumPos();
	void game_over();
	
	int playerOne_currPos;
    int playerTwo_currPos;
    const int Max_position = 100;
	const int size = 8;
	const int climb[8] = {4,9,20,28,40,51,63,71};
	const int drop[8] = {17,54,62,64,87,93,95,99};
	int climbOrdown;
	int player_1Active = 0;
	int player_2Active = 0;
	bool player_1;
	bool player_2;
 	int roll_number;	
};
#include "ladder.cpp"
#endif
