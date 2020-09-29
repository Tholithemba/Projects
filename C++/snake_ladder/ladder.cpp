#include "ladder.h"
#include<iostream>

//constructor
ladder::ladder()
{
	playerOne_currPos = 0;
	playerTwo_currPos = 0;
	climbOrdown = 0;
	player_1 = true;
	player_2 = false;
}

//game start
void ladder::play()
{
	bool maxPos = reachMaximumPos();
	
	while(!maxPos)
	{
		roll_dice();//rolling the die
		//check ox maximum position is reached
	    maxPos = reachMaximumPos();  
	}
	
	game_over();//game over
}

//check if climb up or drop down
bool ladder::check_number(int rand_number)
{	
	//check if the player climb up
 	if(climbUp(rand_number))return true;
 	//check if the player drop down 
	if(dropDown(rand_number))return true;
	
	return 	false;		
}

//return  true if climb up otherwise false
bool ladder::climbUp(int rand_number)
{
	for(int r = 0; r < size; r++)
	{
		if(rand_number == climb[r])
		{
			climb_ladder(rand_number);
			return true;
		}
	}
	return false;
}

//return  true if drop down otherwise false
bool ladder::dropDown(int rand_number)
{
	 for(int r = 0; r < size; r++)
	 {
	 	if(rand_number == drop[r])
	 	{
	 		bite(rand_number);
	 		return true;
	 	}
	 }
	return false;
}



//this method check if number rolled by the
//player correspond to move up position
void ladder::climb_ladder(int rand_number)
{
	int moveUp_to = 0;

	switch(rand_number)
	{
		case  4: moveUp_to = 14;
		break;

		case  9: moveUp_to = 31;
		break;

		case 20: moveUp_to = 38;
		break;

		case 28: moveUp_to = 84;
		break;

		case 40: moveUp_to = 59;
		break;

		case 51: moveUp_to = 67;
		break;

		case 63: moveUp_to = 81;
		break;

		case 71: moveUp_to = 91;
		break;

		default:
		   std::cout<<"Invalid position \n";	 
	}
	climbOrdown = moveUp_to;
	std::cout<<"Player moved up to position: "<< moveUp_to<<std::endl;
}

//this method check if number rolled by the
//player correspond to move down position
void ladder::bite(int rand_number)
{
	int moveDown_to = 0;

	switch(rand_number)
	{
		case 17: moveDown_to =  7;
		break;

		case 54: moveDown_to = 34;
		break;

		case 62: moveDown_to = 19;
		break;

		case 64: moveDown_to = 60;
		break;

		case 87: moveDown_to = 24;
		break;

		case 93: moveDown_to = 73;
		break;

		case 95: moveDown_to = 75;
		break;

		case 99: moveDown_to = 78;
		break;

		default:
		   std::cout<<"Invalid position \n";	 
	}
	climbOrdown = moveDown_to;
	std::cout<<"Player moved down to position: "<< moveDown_to<<std::endl;
}


//roll the die method
void ladder::roll_dice()
{
	roll_number = random_numbers();
	
	if(player_1)
		player1Rolldie(roll_number);
	else
		player2Rolldie(roll_number);

	next_player();
}


//player 1 roll the die
void ladder::player1Rolldie(int roll_number)
{
	std::string blue_color = "\033[1;34m";
	
	if(player_1Active != 0)
	{
		active_player();//only active player start playing 
	}
	else if(player_1Active == 0 and roll_number != 6)
	{
		std::cout<<blue_color<<"Player 1 inactive | roll number: "<<roll_number<<std::endl;
	}
	else if(player_1Active == 0 and roll_number == 6)
	{
		std::cout<<blue_color<<"Player 1 Active | roll number: "<<roll_number<<std::endl;
		player_1Active++;
	}
}


//player 2 roll the die
void ladder::player2Rolldie(int roll_number)
{
	if(player_2Active != 0)
	{
		active_player();	
	}
	else if(player_2Active == 0 and roll_number != 6)
	{
		std::cout<<"Player 2 inactive | roll number: "<<roll_number<<std::endl;
	}
	else if(player_2Active == 0 and roll_number == 6)
	{
		std::cout<<"Player 2 Active | roll number: "<<roll_number<<std::endl;
		player_2Active++;
	}
}


//temporary update for player current postion
int ladder::temp_update()
{
	int new_position = roll_number;
	
	if(player_1)
		new_position += playerOne_currPos;
	else
		new_position += playerTwo_currPos;
	
	return new_position;
}


//turn for another player
void ladder::next_player()
{
	//if the player previous roled 6
	//the player has to repeat
	if(player_1 and roll_number != 6)
	{
		 player_1 = false;
		 player_2 = true;
	}
	else if(player_2 and roll_number != 6)
	{
		 player_1 = true;
		 player_2 = false;	
	}
	else if(player_1 and roll_number == 6)
	{
		 player_1 = true;
		 player_2 = false;
		 std::cout<<"Player 1 reapeat: "<<"Roll number: "<<roll_number<<std::endl;
	}
	else if(player_2 and roll_number == 6)
	{
		 player_1 = false;
		 player_2 = true;
		 std::cout<<"Player 2 reapeat: "<<"Roll number: "<<roll_number<<std::endl;
	}
}


//final update of player current postion
void ladder::updatePlayer_pos(bool check_exist)
{
 	if(!check_exist)
	{
	  if(player_1)
	  {
	  	playerOne_currPos += roll_number;
	  	if(playerOne_currPos > Max_position)
	  		playerOne_currPos -= roll_number;
	  		
	  	std::cout<<"Player 1 position: "<<playerOne_currPos<<" roll number: "<<roll_number<<std::endl;
	  }
	  else
	  {
	  	playerTwo_currPos += roll_number;
	  	if(playerTwo_currPos > Max_position)
	  		playerTwo_currPos -= roll_number;
	  		
	  	std::cout<<"Player 2 position: "<< playerTwo_currPos<<" roll number: "<<roll_number<<std::endl;
	  }
	}
	else
	{
	  if(player_1)
	  {
	  	playerOne_currPos = climbOrdown;//either the snake bite or the player got ladder
	  	std::cout<<"Player 1 position: "<< playerOne_currPos<<" roll number: "<<roll_number<<std::endl;
	  }
	  else
	  {
	  	playerTwo_currPos = climbOrdown;//either the snake bite or the player got ladder
	  	std::cout<<"Player 2 position: "<< playerTwo_currPos<<" roll number: "<<roll_number<<std::endl;
	  }	
	}
}


//get random number between 1 to 6
int ladder::ladder::random_numbers()
{
	return (rand() % 6) +1;
}


//end game and display winner
void ladder::game_over()
{
	if(playerOne_currPos == Max_position)
		std::cout<<"Player 1 won !!!! \n";
	else if(playerTwo_currPos == Max_position)
		std::cout<<"Player 2 won !!!! \n";
	else
		std::cout<<"error occurred \n";
}

//if one player reach the top postion the game is over
bool ladder::reachMaximumPos()
{
	bool stop = false;
	if(playerOne_currPos == Max_position)
		stop = true;
	else if(playerTwo_currPos == Max_position)
		stop = true;
		
	return stop;
}

//player to be active 6 must be rolled first
void ladder::active_player()
{
	int new_position = temp_update();
	
	bool check_exist = check_number(new_position);
	
	updatePlayer_pos(check_exist);	
}

