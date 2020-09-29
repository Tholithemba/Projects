#include "game.h"
#include "randomAccess.h"
#include<vector>
#include<iostream>

//constructor
game::game()
{
	/*randomAccess ra;
	int start = ra.*/
	computer = false;
	human = true;
	game_over = false;
	init_count = 0;

	initializePuzzle();
	initializePosition();
}

void game::play()
{
	int count = 0;
	while(!unvisited_pos.empty())
	{
		
		lookPosition();
		if(game_over)return;
		printResults();
		
		nextPlayer();
		count++;
	}
	
	if(!game_over and unvisited_pos.empty())
		std::cout<<"draw 🤼\n";
}

//checkk valid unvisited position
void game::lookPosition()
{
	int row; 
	int col;
	
	if(init_count < 3)
	{
		rowColRandom(row, col);
		init_count++;
	}
	else
	{
		if(human)
		{
			placement = x;
			if(check(human_lp))return;
			placement = o;
			if(checkWinLoose(computer_lp))return;
		}
		else
		{	
			placement = o;
			if(check(computer_lp))return;
			placement = x;
			if(checkWinLoose(human_lp))return;
		}
		
		rowColRandom(row, col);
	}
	
	placeinPosition(row, col);
}

bool game::check(cell curr_cell)
{
	if(checkWinLoose(curr_cell))
	{
		gameOver();
		return true;
	}
	
	return false;
}

//this method checks if the next move
//of the opponent conclude to winning path
bool game::checkWinLoose(cell curr_cell)
{
	bool found = true;//winnind path found
	int row = curr_cell.first;
	int col = curr_cell.second;
	bool exist;
	
	//check all diagonals
	if(diagonal(row, col))
	{
		if(row == 1 and col == 1)
		{
			if(leftDiagonal())return found;			
			if(rightDiagonal())return found;
		}
		else if(row == col)
		{
			if(rightDiagonal())return found;
		}
		else
		{
			if(leftDiagonal())return found;
		}
	}
	
	if(vertical(col))return found;//check vertical	
	if(horizontal(row))return found;//check horizontal
	
	return false;
}


//pick random unvisited position
void game::rowColRandom(int &row, int &col)
{
	randomAccess ra;
	int rand_num;
	rand_num = ra.randomPosition(unvisited_pos.size());
	row = unvisited_pos[rand_num].first;
	col = unvisited_pos[rand_num].second;
}

//place the player mark in the valid
//chosen position
void game::placeinPosition(int row, int col)
{
	if(human)
	{
		puzzle[row][col] = x;
		human_lp.first = row;
		human_lp.second = col;	
	}
	else
	{
		puzzle[row][col] = o;
		computer_lp.first = row;
		computer_lp.second = col;
	}
	//remove the visited position in the unvisited vector array
	for(int r = 0; r < unvisited_pos.size(); r++)
		if(unvisited_pos[r].first == row and unvisited_pos[r].second == col)
			unvisited_pos.erase(unvisited_pos.begin()+r);
}


//check if the cell/ row and column is lying on diagonal
bool game::diagonal(int row ,int col)
{
	return ((row==col) or (row-col==2) or (row-col==-2));
}



//this method checks if there two same marks vertical 
//and z or unvisited cell. if it is the case place mark
//on the unvisited cell
bool game::vertical(int col)
{
 	int count = CountSameMarkV(col);
 	
 	if(count == 2)
	 	for(int row = 0; row< size; row++)
			if(unvisitedCell(row,col))return true;

 	
 	return false;
}


//this method checks if there two same marks horizontal 
//and z or unvisited cell. if it is the case place mark
//on the unvisited cell
bool game::horizontal(int row)
{
	int count = CountSameMarkH(row);

 	if(count == 2)
	 	for(int col = 0; col< size; col++)
	 		if(unvisitedCell(row, col))return true;

 	return false;
}


//this method checks if there two same marks leftDiagonal 
//and z or unvisited cell. if it is the case place mark
//on the unvisited cell
bool game::leftDiagonal()
{
 	int row = 0;
 	int col = 2;
	left_diagonal = true;
	right_diagonal = false;
 	
 	int count = CountSameMarkD(row, col);

 	row = 0;
 	col = 2;
 	if(count == 2)
		if(twoSameMark(row, col))
	 		return true;
 	
 	return false;
}
//end of leftDiagonal() method

//this method checks if there two same marks rightDiagonal 
//and z or unvisited cell. if it is the case place mark
//on the unvisited cell
bool game::rightDiagonal()
{
 	int row = 0;
 	int col = 0;
	left_diagonal = false;
	right_diagonal = true;
	
 	int count = CountSameMarkD(row, col);
 	
 	row = 0;
 	col = 0; 	
 	if(count == 2)
		if(twoSameMark(row, col))
	 		return true;
 	
 	return false;
}
//end of rightDiagonal() method


//count same marks vertical
int game::CountSameMarkV(int col)
{
 	int count = 0;
 	
 	for(int row = 0; row< size; row++)
 		if(puzzle[row][col] == placement)
 			count++;

 	return count;
}
//end of CountSameMarkV() method


//count same marks horizontal
int game::CountSameMarkH(int row)
{
 	int count = 0;
 	
 	for(int col = 0; col< size; col++)
 		if(puzzle[row][col] == placement)
 			count++;

 	return count;
}
//end of CountSameMarkH() method

//count same marks diagonal
int game::CountSameMarkD(int row, int col)
{
 	int count = 0;
 	
 	while(row < size)
 	{
 		if(puzzle[row][col] == placement)
 			count++;
 			
 		nextCell(row, col);
 	}
 	
 	return count;
}
//end of CountSameMarkD() method

//two corresponding same marks found 
// the method also check if the third
//cell was visited
bool game::twoSameMark(int row, int col)
{
	 	while(row < size)
	 	{
	 		if(unvisitedCell(row,col))return true;
	 		
	 		nextCell(row, col);
	 	}
 	return false;
}
//end of CountSameMarkD() method

//this method checks if the third cell is unvisited
bool game::unvisitedCell(int row, int col)
{
	if(puzzle[row][col] == 'z')
	{
		placeinPosition(row, col);
		return true;
	}
	return false;
}
//end of unvisitedCell() method

//go to the next cell
void game::nextCell(int &row, int &col)
{
	if(left_diagonal)
	{
 		col--;
 		row++;
	}
	else
	{
 		col++;
 		row++;	
	}
}//end of nextCell method

//game over method and display the winner
void game::gameOver()
{
	printResults();
	if(human)
		std::cout<<"human won 🤸🤸🤸🤸\n";
	else 
		std::cout<<"Computer won 🤸🤸🤸🤸\n";
	game_over = true;
}


//turn for the opponent
void game::nextPlayer()
{
	if(human)
	{
		human = false;
		computer = true;
		std::cout<<"****************************Human****************************\n";
		std::cout<<"\n";
	}
	else
	{
		human = true;
		computer = false;
		std::cout<<"****************************Computer****************************\n";
		std::cout<<"\n";
	}
}


//display each turn for players
void game::printResults()
{
	std::string blue_color = "\033[1;33m";
	std::cout<<blue_color;
	for(int r = 0; r < size; r++)
	{
		for(int c = 0; c < size; c++)
		{
			std::cout<<puzzle[r][c]<<" ";
		}
		std::cout<<"\n";
	}
}


//initializing puzzle to charactor z which symbolise
//unvisited cell
void game::initializePuzzle()
{
	int row;
	int col;
	
	puzzle = new char*[size];
	
	for(row = 0; row < size; row++)
		puzzle[row] = new char[size];
	
	for(row = 0; row < size; row++)
		for(col = 0; col < size; col++)
			puzzle[row][col] = 'z';	
}

//this method collects all unvisited position
//when the game starts
void game::initializePosition()
{
	for(int row = 0; row < size; row++)
		for(int col = 0; col < size; col++)
			unvisited_pos.push_back(std::make_pair(row , col));
}
//Tholithemba


